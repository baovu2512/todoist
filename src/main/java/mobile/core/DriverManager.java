package mobile.core;

import commons.TestConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.nio.file.Paths;

public class DriverManager {
    private final static String APP_PATH =
            Paths.get(Paths.get("").toAbsolutePath().toString(), "src", "test", "resources", "build",
                    "android", "com.todoist_15.0.4-6030_minAPI21(nodpi)_apkmirror.com.apk").toString();
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final String workingDir = System.getProperty("user.dir");

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    private static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }

    public void startAppiumService() {
        try {
            // Build the Appium service
            AppiumServiceBuilder builder = new AppiumServiceBuilder().withIPAddress("0.0.0.0")
                    .usingAnyFreePort().withArgument(GeneralServerFlag.SESSION_OVERRIDE);

            //set appium service
            setService(AppiumDriverLocalService.buildService(builder));
            getService().clearOutPutStreams();

            //start service
            getService().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AppiumDriverLocalService getService() {
        return service;
    }

    public void setService(AppiumDriverLocalService service) {
        this.service = service;
    }

    private AppiumDriverLocalService service;

    public void initialiseDriver(String platform) throws Exception {
        switch (platform) {
            case "ANDROID":
                startAndroidDriver(getService());
                break;
            case "IOS":
                startIosDriver(getService());
                break;
        }
    }

    private void startIosDriver(AppiumDriverLocalService appiumService) {
    }

    public void startAndroidDriver(AppiumDriverLocalService appiumService)
            throws IOException {
        AppiumDriver<MobileElement> driver = null;
        AndroidDevice device = TestConfig.getAndroidDevice();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getPlatformVersion());
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, device.getUdid());
        cap.setCapability(MobileCapabilityType.APP, APP_PATH);
        cap.setCapability("avd", device.getUdid());
        cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        cap.setCapability("systemPort", device.getSystemPort());
        cap.setCapability("appPackage", "com.todoist");
        cap.setCapability("appWaitActivity",
                "com.todoist.activity.HomeActivity,com.todoist.activity.WelcomeActivity");
        cap.setCapability("appWaitDuration", 60000);
        cap.setCapability(MobileCapabilityType.NO_RESET, "false");

        driver = new AndroidDriver<MobileElement>(appiumService, cap);
        setDriver(driver);
    }

}
