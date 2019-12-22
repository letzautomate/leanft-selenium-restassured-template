package com.letzautomate.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class DriverManager {

    @BeforeMethod(alwaysRun = true)
    public void createDriverInstance() {
        WebDriver driver = null;
        if(System.getProperty("browser").equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\selenium\\src\\main\\resources\\drivers\\ChromeDriver.exe");
            driver = new ChromeDriver();
        }
        Long threadID = Thread.currentThread().getId();
        InstanceManager.driverInstance.put(threadID, driver);

    }

    public WebDriver getDriverInstance() {
        return InstanceManager.driverInstance.get(Thread.currentThread().getId());
    }
}
