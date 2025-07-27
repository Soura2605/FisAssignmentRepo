package org.automation.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait explicitWait;
    protected FluentWait<WebDriver> fluentWait;

    public BasePage(WebDriver driver){

        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    protected void waitForElementToBeClickable(WebElement ele){

        explicitWait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    protected void waitForElementToBeVisible(WebElement ele){

        explicitWait.until(ExpectedConditions.visibilityOf(ele));
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


    protected void switchToTab(int tabIndex) {
        int i = 0;

      Set<String> windowsOpened = driver.getWindowHandles();

      if(windowsOpened.size()>1){

          for (String handle : driver.getWindowHandles()) {
              if (i == tabIndex) {
                  driver.switchTo().window(handle);
                  break;
              }
              i++;
          }

      }


    }

    protected boolean isElementDisplayed(WebElement ele) {
        try {
            waitForElementToBeVisible(ele);
            return ele.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void fluentWaitForElementToBeClickable(WebElement ele, WebDriver driver){

        fluentWait = new FluentWait(driver);
        fluentWait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(ele));
    }

    protected void waitForCurrentFunctionToComplete() {
        try {
            Thread.sleep(5000); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

}

