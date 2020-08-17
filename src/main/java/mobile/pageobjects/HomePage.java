package mobile.pageobjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.core.AbstractPage;
import utils.Log;

public class HomePage extends AbstractPage {

    @AndroidFindBy(id = "com.todoist:id/profile_view_name")
    private MobileElement profileName;

    @AndroidFindBy(xpath = "//*[@resource-id='com.todoist:id/name' and @text='Projects']/following-sibling::android.widget.ImageView")
    private MobileElement expand;

    @AndroidFindBy(accessibility = "Change the current view")
    private MobileElement navigatorView;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Change the current view\"]//following-sibling::android.widget.TextView[@text='Today']")
    private MobileElement curView;
    private String navProject = "//android.widget.RelativeLayout[.//*[@resource-id='com.todoist:id/name' and @text='Projects']]/following-sibling::*[./*[@text='%s']]";
    private String taskInfo = "//*[@resource-id='com.todoist:id/text' and @text='%s']/following-sibling::  android.widget.TextView[@resource-id='com.todoist:id/project' and @text='%s']";
    private String taskInfoInProject = "//*[@resource-id='com.todoist:id/text' and @text='%s']";

    @AndroidFindBy(id = "com.todoist:id/fab")
    private MobileElement addBtn;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement taskContent;

    private String completeTask = "//*[@resource-id='com.todoist:id/text' and @text='%s']/preceding-sibling::android.widget.CheckBox";

    public HomePage clickToOpenNavigator() {
        Log.reportLog("Click to Menu side bar navigator");
        clickOnElement(navigatorView);
        return this;
    }

    public HomePage verifyHomePageDisplayed() {
        Log.reportLog("Verify home page displayed");
        waitForElementVisible(curView);
        return this;
    }

    public HomePage verifyProjectDisplayed(String projectName) {
        Log.reportLog("Verify Project name:" + projectName + " is displayed");
        waitForElementVisible(MobileBy.xpath(String.format(navProject, projectName)));
        return this;
    }

    public HomePage clickOnProject(String projectName) {
        Log.reportLog("Click to project:" + projectName);
        clickOnElement(MobileBy.xpath(String.format(navProject, projectName)));
        return this;
    }

    public HomePage clickExpand() {
        Log.reportLog("Click to expand project");
        clickOnElement(expand);
        return this;
    }

    public HomePage clickAddBtn() throws InterruptedException {
        Log.reportLog("Click to Add Button");
        waitFor(3);
        clickOnElement(addBtn);
        return this;
    }

    public HomePage enterTaskContent(String projectName, String taskName) {
        Log.reportLog("Enter task with project name: "+projectName + " and task name:" +taskName);
        sendKeysToElement(taskContent, "#" + projectName + " " + taskName);
        return this;
    }

    public HomePage enterTaskContent(String taskName) {
        Log.reportLog("Enter task with task name:" +taskName);
        sendKeysToElement(taskContent, taskName);
        return this;
    }

    public HomePage submitTask() {
        Log.reportLog("Submit task");
        pressKeyboard(AndroidKey.ENTER);
        return this;
    }

    public HomePage verifyTaskOnView(String projectName, String taskContent) {
        isControlDisplayed(MobileBy.xpath(String.format(taskInfo, taskContent, projectName)));
        return this;
    }

    public boolean verifyTaskOnView(String taskContent) {
        Log.reportLog("Verify task content is:" + taskContent);
        return isControlDisplayed(MobileBy.xpath(String.format(taskInfoInProject, taskContent)));
    }

    public HomePage clickToCompleteTask(String taskName) {
        Log.reportLog("Click to complete task by task name:" + taskName);
        clickOnElement(MobileBy.xpath(String.format(completeTask, taskName)));
        return this;
    }

}
