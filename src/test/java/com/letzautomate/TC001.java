package com.letzautomate;

import com.letzautomate.pages.application.Login;
import com.letzautomate.pages.application.LoginPage;
import com.letzautomate.utilities.DriverManager;
import org.testng.annotations.Test;

public class TC001 extends DriverManager {

    @Test(groups={"regression"})
    public void tc001() {
        LoginPage loginPage = new LoginPage();
        Login login = new Login();
        loginPage.login();
        login.launchApplication();
        login.addNumbers();
    }
}
