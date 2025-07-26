package org.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final int IMPLICIT_WAIT_TIMEOUT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 30;

    private WebDriverManager(){

    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void setDriver(String browser){
        WebDriver driver = createDriver(browser);
        configureDriver(driver);
        driverThreadLocal.set(driver);
    }

    public static void quitDriver(){
        WebDriver driver = driverThreadLocal.get();

        if(driver!=null){
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    public static WebDriver createDriver(String browser){

        if(browser.equalsIgnoreCase("Chrome")){
            return createChromeDriver();
        }

        throw new IllegalArgumentException("Unsupported Browser"+browser);
    }

    private static WebDriver createChromeDriver(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }

    public static void configureDriver(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.manage().window().maximize();

    }







}
