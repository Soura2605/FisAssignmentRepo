package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void waitForElementToBeClickable(WebElement ele){

        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    protected void waitForElementToBeVisible(WebElement ele){

        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    protected void click(WebElement ele){

        waitForElementToBeVisible(ele);
        ele.click();
    }

    protected void sendKeys(WebElement ele, String text){

        waitForElementToBeVisible(ele);
        ele.sendKeys(text);
    }

    protected String getText(WebElement ele){

        waitForElementToBeVisible(ele);
        return ele.getText();

    }







}
