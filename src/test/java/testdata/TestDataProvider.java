package testdata;

import api.models.User;
import commons.TestConfig;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {
    @DataProvider(name = "CreateProject")
    public static Object[][] createProject() throws IOException {
        User user = TestConfig.getUserConfig();
        return new Object[][]{
                {user.getUsername(), user.getEmail(), user.getPassword(), "Test Project"}};
    }

    @DataProvider(name = "CreateTaskViaMobilePhone")
    public static Object[][] createTask() throws IOException {
        User user = TestConfig.getUserConfig();
        return new Object[][]{
                {user.getUsername(), user.getEmail(), user.getPassword(), "Test Project", "New creation task"}};
    }
}
