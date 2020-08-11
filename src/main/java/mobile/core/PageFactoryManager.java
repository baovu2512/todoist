package mobile.core;

import mobile.pageobjects.HomePage;
import mobile.pageobjects.LoginPage;

public class PageFactoryManager {

    public static LoginPage getLoginPage() {
        return new LoginPage();
    }

    public static HomePage getHomePage() {
        return new HomePage();
    }
}
