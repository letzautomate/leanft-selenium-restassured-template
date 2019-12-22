package com.letzautomate.pages.application;


import com.letzautomate.pages.generic.SeleniumBasePage;
import org.openqa.selenium.By;

public class LoginPage extends SeleniumBasePage {
    private final By searchTextBox = By.name("q");
    public boolean login() {
        try {
            launchApp();
            enterText(searchTextBox, "Ram");
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
