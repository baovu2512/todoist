package core;

import mobile.core.DriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class AbstractTest {

    public DriverManager driverManager = new DriverManager();

    @BeforeSuite
    public void beforeSuite() throws FileNotFoundException, IOException {
        // setup logger
        Properties prop = new Properties();
        prop.load(new FileInputStream(Paths.get(Paths.get("").toAbsolutePath().toString(), "src",
                "test", "resources", "configs", "log4j.properties").toString()));
        PropertyConfigurator.configure(prop);
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        driverManager.startAppiumService();
        driverManager.initialiseDriver("ANDROID");
    }

    @AfterMethod
    public void resetService() {
        driverManager.getService().stop();
    }
}
