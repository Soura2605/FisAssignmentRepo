package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.Set;

public class SearchResultPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//span[@class='BOLD' and text()='book']")
    private WebElement searchResultHeader;




    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage selectDesiredProduct(int productPosition){

//        WebElement desiredProduct = driver.findElement(By.xpath("(//ul[contains(@class,'srp-results')]//div[contains(@class,'image-wrapper')]/img)[" + productPosition + "]"));
//
//        fluentWaitForElementToBeClickable(desiredProduct, driver);
//        click(desiredProduct);
//

        List<WebElement> allDisplayedProductsLinks = driver.findElements(By.xpath("//ul[contains(@class,'srp-results')]//div//div//following-sibling::div//a"));

        WebElement desiredProduct = allDisplayedProductsLinks.get(productPosition - 1);
        waitForElementToBeClickable(desiredProduct);
        click(desiredProduct);
        switchToTab(1);

        return new ProductDetailsPage(driver);
    }

    public void validateUserIsOnDesiredProductSearchResultPage(String product){

        WebElement ele = driver.findElement(By.xpath("//span[@class='BOLD' and text()='" + product + "']"));
        waitForElementToBeVisible(ele);
    }
}
