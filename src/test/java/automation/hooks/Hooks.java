package automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automation.utils.ReportManager;
import org.automation.utils.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Setting up WebDriver");
        String browser = System.getProperty("browser", "chrome");
        WebDriverManager.setDriver("chrome");
        logger.info("WebDriver setup complete for browser: " + browser);
        ReportManager.initReport();
        ReportManager.createTest(scenario.getName());
        ReportManager.logInfo("Scenario started: " + scenario.getName());
        logger.info("Before hook executed");
    }


    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = WebDriverManager.getDriver();
        try {
            // Wait for the page to load after every step
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));

        } catch (Exception e) {
            logger.error("Error while waiting for page to load after step", e);
            ReportManager.logFail("Error after step: " + e.getMessage());
        }
    }


    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = WebDriverManager.getDriver();
        if(driver != null) {

            if(scenario.isFailed()) {
                logger.error("Scenario failed, taking screenshot...");

                try{

                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                    ReportManager.logFail("Scenario failed. Screenshot attached.");

                } catch (Exception e) {

                    logger.error("Failed to take screenshot: " + e.getMessage());
                    ReportManager.logFail("Failed to take screenshot: " + e.getMessage());
                }

            } else {
                ReportManager.logPass("Scenario passed.");
            }
            logger.info("Cleaning up WebDriver");
            WebDriverManager.quitDriver();
        }
        ReportManager.flushReport();
    }
}
