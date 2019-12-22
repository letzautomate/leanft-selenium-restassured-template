package com.letzautomate.utilities;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.ConcurrentHashMap;

public class InstanceManager {

    public static ConcurrentHashMap<Long, WebDriver> driverInstance = new ConcurrentHashMap<Long, WebDriver>();
}
