package mobile.core;

import api.core.BaseApi;
import commons.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AbstractPage extends BaseApi{

    public AppiumDriver driver;

    public AbstractPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(Constants.LONG_TIMEOUT)), this);
    }

}
