package com.letzautomate.pages.common;

import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.java.Button;
import com.hp.lft.sdk.java.Editor;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import java.net.URI;


public class LeanFTBasePage {

    public final static Logger LOGGER = Logger.getLogger(LeanFTBasePage.class);

    @BeforeMethod(alwaysRun=true)
    public void initLeanFTSDK() {
        /*boolean processFound = isProcessRunning("LFTRuntime.exe");
        if (!processFound) {
            logger.info("Start LFTRuntime.exe Engine process.");
            Runtime.getRuntime().exec("LFTRuntime.exe");
            // Sleep for 10s since it takes some time for init
            Thread.sleep(10 * 1000);
            if (!isProcessRunning("LFTRuntime.exe")) {
                throw new Exception("LFTRuntime could not be started or is not installed");
            }
        }
        else{
            logger.info("LFTRuntime.exe Engine process already started.");
        }*/
        ModifiableSDKConfiguration modifiableSDKConfiguration = null;
        try{
            modifiableSDKConfiguration = new ModifiableSDKConfiguration();
            modifiableSDKConfiguration.setServerAddress(new URI("ws://localhost:5095"));
            LOGGER.info("Initialize Lean FT SDK with   default configuration.");
            SDK.init(modifiableSDKConfiguration);
            Reporter.init();
            LOGGER.info("Initialization complete.");
        }catch(Exception e){
            LOGGER.info("There was an error in initializing SDK");
            e.printStackTrace();
        }

    }

    public void enterTextEditor(Editor editor, String textToEnter) {

        try {
            LOGGER.info("Befor Enter Text in :: " + editor.getAttachedText());
            editor.setText(textToEnter);
            LOGGER.info("after Enter Text in :: "+ editor.getAttachedText());
        }catch(Exception e) {
            LOGGER.info("There was an exception in entering text");
            e.printStackTrace();
        }
    }

    public void buttonClick(Button button) {
        try {
            LOGGER.info("Before click the button :: " + button.getLabel());
            button.click();
            LOGGER.info("After click the button :: "+  button.getLabel());
        }catch(Exception e) {
            LOGGER.info("There was an exception in clicking the button");
            e.printStackTrace();
        }
    }
}
