package core;

import mobile.core.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractTest {
    public DriverManager driverManager = new DriverManager();

    @BeforeMethod
    public void beforeMethod() throws Exception {
        driverManager.startAppiumService();
        driverManager.initialiseDriver("ANDROID");
    }

    @AfterMethod
    public void resetService(){
        driverManager.getService().stop();
    }
}
