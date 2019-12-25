package com.letzautomate.pages.application;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.java.Button;
import com.letzautomate.appmodel.ApplicationName;
import com.letzautomate.pages.common.LeanFTBasePage;

import java.io.File;

public class Login extends LeanFTBasePage {

    ApplicationName applicationName;
    private ApplicationName.CalculatorWindow calculatorWindow;
    private Button button6;
    private Button button4;
    public Login() {
        try{
            applicationName = new ApplicationName();
            calculatorWindow = applicationName.new CalculatorWindow(applicationName);
            button6 = calculatorWindow.button6();
            button4 = calculatorWindow.button4();
        }catch(GeneralLeanFtException e){
            e.printStackTrace();
        }
    }

    public boolean launchApplication() {
        initLeanFTSDK();
       ProcessBuilder processBuilder = new ProcessBuilder("E:\\LetsDoIT\\leanft-selenium-restassured-template\\leanft\\src\\test\\resources\\application\\runapplication.bat").inheritIO();
       processBuilder.directory(new File("E:\\LetsDoIT\\leanft-selenium-restassured-template\\leanft\\src\\test\\resources\\application\\"));
       try{
           processBuilder.start();
       }catch(Exception e){
           e.printStackTrace();
           return false;
        }
        return true;
    }

    public boolean addNumbers(){
        try{
            //button4.click();
            button6.click();
        }catch(GeneralLeanFtException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
