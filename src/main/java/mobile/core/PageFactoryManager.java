package mobile.core;

import mobile.pageobjects.LoginPage;

public class PageFactoryManager {
    public LoginPage getLoginPage(){
        return new LoginPage();
    }
}
