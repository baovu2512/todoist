package mobile.core;

import commons.constants.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    private AppiumDriver driver;

    public AbstractPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Config.LONG_TIMEOUT)), this);
    }

    public void clickOnElement(MobileElement element) {
        MobileElement el = waitForElementVisible(element);
        el.click();
    }

    public void clickOnElement(By by) {
        MobileElement el = waitForElementVisible(by);
        el.click();
    }

    public void sendKeysToElement(MobileElement element, String keyToSend) {
        MobileElement el = waitForElementVisible(element);
        el.sendKeys(keyToSend);
    }

    public MobileElement waitForElementVisible(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.LONG_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ex) {
            System.err.println(
                    "================================== Element not visible===================================");
            System.err.println(ex.getMessage() + "\n");
        }
        return element;
    }

    public MobileElement waitForElementTextVisible(MobileElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.LONG_TIMEOUT);
            wait.until(ExpectedConditions.textToBePresentInElement(element,text));
        } catch (Exception ex) {
            System.err.println(
                    "================================== Element not visible===================================");
            System.err.println(ex.getMessage() + "\n");
        }
        return element;
    }

    public MobileElement waitForElementVisible(By by) {
        MobileElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Config.LONG_TIMEOUT);
            element = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            System.err.println(
                    "================================== Element not visible===================================");
            System.err.println(ex.getMessage() + "\n");
        }
        return element;
    }

    public void isControlDisplayed(By by){
        waitForElementVisible(by).isDisplayed();
    }

    public void pressKeyboard(AndroidKey key) {
        ((AndroidDriver) driver).pressKey(new KeyEvent(key));
    }

}
