package com.letzautomate.pages.application;


import com.letzautomate.pages.generic.SeleniumBasePage;
import org.openqa.selenium.By;

public class LoginPage extends SeleniumBasePage {
    private final By searchTextBox = By.name("q");
    public void login() {
        launchApp();
        enterText(searchTextBox, "Ram");
    }
}
