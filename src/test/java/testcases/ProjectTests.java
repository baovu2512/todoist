package testcases;

import mobile.core.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ProjectTests {
    DriverManager driverManager;
    @Test
    public void create_project() throws Exception {
        driverManager = new DriverManager();
        driverManager.startAppiumService();
        System.out.println(driverManager.getService().isRunning());
        driverManager.initialiseDriver("ANDROID");
    }

    @AfterTest
    public void dispose(){
        driverManager.getService().stop();
    }
}
