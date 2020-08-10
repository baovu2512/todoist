package testcases;

import api.models.Project;
import api.models.Task;
import api.request.ProjectsApi;
import api.request.TasksApi;
import core.AbstractTest;
import mobile.core.DriverManager;
import mobile.core.PageFactoryManager;
import mobile.pageobjects.HomePage;
import mobile.pageobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testdata.TestDataProvider;

public class ProjectTests extends AbstractTest {
    DriverManager driverManager = new DriverManager();
    ;
    ProjectsApi projectsApi = new ProjectsApi();
    TasksApi tasksApi = new TasksApi();

    @BeforeMethod
    public void before_test(Object[] args) throws Exception {
        String projectName = (String) args[3];
        projectsApi.createProjectByApi(projectName);
        driverManager.startAppiumService();
        driverManager.initialiseDriver("ANDROID");

    }

    @Test(dataProvider = "CreateProject", dataProviderClass = TestDataProvider.class)
    public void create_project(String username, String email, String password, String projectName) {
        LoginPage loginPage = PageFactoryManager.getLoginPage();
        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin()
                .navigateToHomePage()
                .verifyHomePageDisplayed(username)
                .clickExpand()
                .verifyProjectDisplayed(projectName)
                .clickOnProject(projectName)
                .verifyCurrentView(projectName);

    }

    @Test(dataProvider = "CreateTaskViaMobilePhone", dataProviderClass = TestDataProvider.class)
    public void create_project_via_mobile(String username, String email, String password,
                                          String projectName, String taskContent) {
        LoginPage loginPage = PageFactoryManager.getLoginPage();
        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin();
        HomePage homePage = loginPage.navigateToHomePage();
        homePage.clickAddBtn()
                .enterTaskContent(projectName, taskContent)
                .submitTask().verifyTaskCreated(projectName, taskContent);

        projectsApi.getAllProjectApi().saveProjectsList();
        Project currentPrj = projectsApi.andFilterProjectByName(projectName);
        String projectId = String.valueOf(currentPrj.getId());
        Task task = tasksApi.getActiveTaskById(projectId).saveTask();
        Assert.assertEquals(projectId, task.getProjectId());
        Assert.assertEquals(taskContent, task.getContent());
    }

    @Test(dataProvider = "CreateTaskViaMobilePhone", dataProviderClass = TestDataProvider.class)
    public void reopen_task(String username, String email, String password, String projectName, String taskContent) {

        LoginPage loginPage = PageFactoryManager.getLoginPage();
        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin()
                .navigateToHomePage()
                .verifyHomePageDisplayed(username)
                .clickExpand()
                .verifyProjectDisplayed(projectName)
                .clickOnProject(projectName)
                .verifyCurrentView(projectName)
                .clickAddBtn()
                .enterTaskContent(taskContent);

        projectsApi.getAllProjectApi().saveProjectsList();
        Project currentPrj = projectsApi.andFilterProjectByName(projectName);
        String projectId = String.valueOf(currentPrj.getId());
        Task task = tasksApi.getActiveTaskById(projectId).saveTask();

    }

    @BeforeMethod
    public void dispose() {
        driverManager.getService().stop();
        projectsApi.deleteAllProject();
    }
}
