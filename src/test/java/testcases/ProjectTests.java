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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testdata.TestDataProvider;

public class ProjectTests extends AbstractTest {
    DriverManager driverManager = new DriverManager();
    ProjectsApi projectsApi = new ProjectsApi();
    TasksApi tasksApi = new TasksApi();
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void before_test(Object[] args) throws Exception {
        String projectName = (String) args[3];
        projectsApi.createProjectByApi(projectName);
        driverManager.startAppiumService();
        driverManager.initialiseDriver("ANDROID");
        homePage = PageFactoryManager.getHomePage();
        loginPage = PageFactoryManager.getLoginPage();
    }

    @Test(dataProvider = "CreateProject", dataProviderClass = TestDataProvider.class)
    public void create_project(String username, String email, String password, String projectName) {
        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin()
                .navigateToHomePage()
                .verifyHomePageDisplayed(username)
                .clickExpand()
                .verifyProjectDisplayed(projectName)
                .clickOnProject(projectName);
    }

    @Test(dataProvider = "CreateTaskViaMobilePhone", dataProviderClass = TestDataProvider.class)
    public void create_project_via_mobile(String username, String email, String password,
                                          String projectName, String taskContent) throws InterruptedException {

        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin();

        homePage = loginPage.navigateToHomePage();
        homePage.clickExpand()
                .clickOnProject(projectName)
                .clickAddBtn()
                .enterTaskContent(projectName, taskContent)
                .submitTask()
                .verifyTaskOnView(taskContent);

        homePage.waitFor(3);
        projectsApi.getAllProjectApi().saveProjectsList();
        Project currentPrj = projectsApi.andFilterProjectByName(projectName);
        String projectId = String.valueOf(currentPrj.getId());
        //save task
        tasksApi.getAllActiveTask().saveTaskList();
        Task task = tasksApi.andFilterTaskByProjectIdAndContent(projectId, taskContent);
        Assert.assertEquals(projectId, task.getProject_id());
        Assert.assertEquals(taskContent, task.getContent());
    }

    @Test(dataProvider = "CreateTaskViaMobilePhone", dataProviderClass = TestDataProvider.class)
    public void reopen_task(String username, String email, String password, String projectName, String taskContent) throws InterruptedException {

        loginPage = PageFactoryManager.getLoginPage();
        loginPage.clickWelComeContinueWithEmail()
                .enterEmail(email)
                .clickContinueWithEmail()
                .enterPassword(password)
                .clickToBtnLogin();

        homePage = loginPage.navigateToHomePage()
                .verifyHomePageDisplayed(username)
                .clickExpand()
                .verifyProjectDisplayed(projectName)
                .clickOnProject(projectName)
                .clickAddBtn()
                .enterTaskContent(taskContent)
                .submitTask();

        homePage.waitFor(3);

        projectsApi.getAllProjectApi().saveProjectsList();
        Project currentPrj = projectsApi.andFilterProjectByName(projectName);
        String projectId = String.valueOf(currentPrj.getId());
        tasksApi.getAllActiveTask().saveTaskList();
        Task task = tasksApi.andFilterTaskByProjectIdAndContent(projectId, taskContent);

        homePage.clickToCompleteTask(taskContent);
        tasksApi.reOpenTaskById(String.valueOf(task.getId())).validateStatusCode(200);

        homePage.waitFor(3);

        homePage.swipeDown(20, 80);
        homePage.verifyTaskOnView(taskContent);
    }

    @AfterMethod
    public void dispose() {
        driverManager.getService().stop();
        projectsApi.deleteAllProject();
    }
}
