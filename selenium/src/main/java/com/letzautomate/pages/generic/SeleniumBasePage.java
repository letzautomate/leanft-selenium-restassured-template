package com.letzautomate.pages.generic;

import com.letzautomate.utilities.InstanceManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumBasePage {

    public static final Logger LOGGER = Logger.getLogger(SeleniumBasePage.class);

    public WebDriver getDriverInstance() {
        return InstanceManager.driverInstance.get(Thread.currentThread().getId());
    }

    public void launchApp() {
        LOGGER.info("Before Enter URL");
        getDriverInstance().get("http://google.com");
        LOGGER.info("After Enter URL");
    }

    public void enterText(By locator, String textToEnter) {
        LOGGER.info("Before Enter TExt");
        getDriverInstance().findElement(locator).sendKeys(textToEnter);
        LOGGER.info("After Enter TExt");
    }
}
