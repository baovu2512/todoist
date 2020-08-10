package mobile.pageobjects;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import mobile.core.AbstractPage;
import mobile.core.DriverManager;

public class HomePage extends AbstractPage {

    @AndroidFindBy(id = "com.todoist:id/profile_view_name")
    private MobileElement profileName;

    @AndroidFindBy(xpath = "//*[@resource-id='com.todoist:id/name' and @text='Projects']/following-sibling::android.widget.ImageView")
    private MobileElement expand;

    private String curView = "//android.widget.ImageButton[@content-desc=\"Change the current view\"]//following-sibling::android.widget.TextView[@text='%s']";
    private String navProject =  "//android.widget.RelativeLayout[.//*[@resource-id='com.todoist:id/name' and @text='Projects']]/following-sibling::*[./*[@text='%s']]";
    private String taskInfo = "//*[@resource-id='com.todoist:id/text' and @text='%s']/following-sibling::  android.widget.TextView[@resource-id='com.todoist:id/project' and @text='%s']";
    @AndroidFindBy(id = "com.todoist:id/fab")
    private MobileElement addBtn;

    @AndroidFindBy(id = "android:id/message")
    private MobileElement taskContent;

    private String completeTask = "//*[@resource-id='com.todoist:id/text' and @text='%s']/preceding-sibling::android.widget.CheckBox";
    public HomePage verifyHomePageDisplayed(String username) {
        waitForElementTextVisible(profileName,username);
        return this;
    }

    public HomePage verifyProjectDisplayed(String projectName){
        waitForElementVisible(MobileBy.xpath(String.format(navProject,projectName)));
        return this;
    }

    public HomePage clickOnProject(String projectName){
        clickOnElement(MobileBy.xpath(String.format(navProject,projectName)));
        return this;
    }

    public HomePage clickExpand(){
        clickOnElement(expand);
        return this;
    }

    public HomePage verifyCurrentView(String projectName){
        isControlDisplayed(MobileBy.xpath(String.format(curView,projectName)));
        return this;
    }

    public HomePage clickAddBtn(){
        clickOnElement(addBtn);
        return this;
    }

    public HomePage enterTaskContent(String projectName, String taskName){
        sendKeysToElement(taskContent,"#" + projectName + " " + taskName);
        return this;
    }
    public HomePage enterTaskContent(String taskName){
        sendKeysToElement(taskContent,taskName);
        return this;
    }

    public HomePage submitTask(){
        pressKeyboard(AndroidKey.ENTER);
        return this;
    }

    public HomePage verifyTaskCreated(String projectName , String taskContent){
        isControlDisplayed(MobileBy.xpath(String.format(taskInfo,taskContent,projectName)));
        return this;
    }

}
