package mobile.core;

import commons.constants.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
        element = waitForElementVisible(element);
        try {
            element.click();
        } catch (StaleElementReferenceException stale) {
            waitForElementVisible(element).click();
        }
    }

    public void clickOnElement(By by) {
        MobileElement element = waitForElementVisible(by);
        try {
            element.click();
        } catch (StaleElementReferenceException stale) {
            waitForElementVisible(by).click();
        }
    }

    public void sendKeysToElement(MobileElement element, String keyToSend) {
        MobileElement el = waitForElementVisible(element);
        try {
            el.sendKeys(keyToSend);
        } catch (StaleElementReferenceException stale) {
            waitForElementVisible(element).sendKeys(keyToSend);
        }
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
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
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
            element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            System.err.println(
                    "================================== Element not visible===================================");
            System.err.println(ex.getMessage() + "\n");
        }
        return element;
    }

    public boolean isControlDisplayed(By by) {
        MobileElement ele = waitForElementVisible(by);
        return ele != null;
    }

    public void pressKeyboard(AndroidKey key) {
        ((AndroidDriver) driver).pressKey(new KeyEvent(key));
    }

    public void waitFor(int timeInSecond) throws InterruptedException {
        Thread.sleep(timeInSecond * 1000);
    }

    public void swipeDown(int startPercent, int endPercent) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;
        int start = height - height * startPercent / 100;
        int end = height - height * endPercent / 100;
        new TouchAction(driver).press(PointOption.point(width / 2, start))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(width / 2, end)).release().perform();
    }
}
