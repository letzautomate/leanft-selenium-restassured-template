package utilities;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.SimpleDate;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ExtentReportUtility {
    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static String extentReportPropertiesFilePath = "src\\main\\resources\\";
    private static final Logger LOGGER = Logger.getLogger(ExtentReportUtility.class);


    public static ExtentReports createInstance (String propName) {

        Properties properties = new Properties();
        InputStream inputStream = null;

        try{
            inputStream = new FileInputStream(extentReportPropertiesFilePath+propName+".properties");
            properties.load(inputStream);

            String reportPath = properties.getProperty("reportPath");
            String reportTitle = properties.getProperty("reportTitle");
            String reportName = properties.getProperty("reportName");
            String environment = properties.getProperty("environment");
            String reportHeading = properties.getProperty("reportHeading");

            String htmlReportName = "<img src='logos/orglogo.png' alt='orglogo' style='height:100%;width=auto;float:left;padding-right:40px;'/> <span class='label blue darken-3' hspace='35' style='font-size: 130%'>"+reportName+"</span> <span class='label blue darken-3' style='font-size: 100%'>ENV : "+environment+"</span> <img src='logos/applicationLogo.png' alt='applicationlogo' style='height:100%:width=auto;float:right;'/>";
            String cssToHideText = "a.brand-logo {display : none;}\n" +
                    "#nav-mobile > li:nth-child(2) > a > span {display : none;}";

            extentHtmlReporter = new ExtentHtmlReporter(reportPath);
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentHtmlReporter);
            extentReports.setAnalysisStrategy(AnalysisStrategy.CLASS);

            extentHtmlReporter.config().setDocumentTitle(reportTitle);
            extentHtmlReporter.config().setReportName(reportName);
            extentHtmlReporter.config().setTheme(Theme.DARK);
            extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
            extentHtmlReporter.config().setChartVisibilityOnOpen(true);
            extentHtmlReporter.config().setEncoding("UTF-8");
            extentHtmlReporter.config().setProtocol(Protocol.HTTPS);
            extentHtmlReporter.config().setCSS(cssToHideText);
            extentReports.setSystemInfo("Environment", environment);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) {
                try{
                    inputStream.close();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return extentReports;
    }

    public String takeScreenshot(WebDriver driver, String screenshotName) {
        String destination = null;
        String imgPath = null;
        int maxRetryCount = 5;
        int retryCounter = 0;

        while (driver instanceof TakesScreenshot) {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmssSS").format(new Date());
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try{
                imgPath = "TestScreenshots\\" + screenshotName + dateName + ".png";
                destination = System.getProperty("user.dir") + "\\build\\extent-reports\\" + imgPath;
                File finalDestination = new File(destination);
                FileUtils.copyFile(source, finalDestination);
                LOGGER.info("Screenshot Destination : " + destination);
                return imgPath;
            } catch(Exception e) {
                LOGGER.error("takescreenshot error: " + e.getMessage());
            }
        }
        LOGGER.info("Destination after Exception: " + destination);
        return imgPath;

    }

    public static void copyLogoDirectory() {
        String destinationDir = "";
        try {
            String sourceDir = System.getProperty("user.dir") + "/config/logos";
            destinationDir = System.getProperty("user.dir") + "/build/extent-reports/logos";
            File destDir = new File(destinationDir);
            File srcDir = new File(sourceDir);
            FileUtils.copyDirectory(srcDir, destDir);
        }catch(Exception e){
            LOGGER.info("There was an error while moving logos to driver : " + destinationDir);
            e.printStackTrace();
        }
    }

}
