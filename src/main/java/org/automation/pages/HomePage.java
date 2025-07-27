package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {


    @FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search')]")
    private WebElement searchBox;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'search-button')]")
    private WebElement searchButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchTextIntoSearchBox(String searchItem){

        waitForElementToBeVisible(searchBox);
        sendKeys(searchBox, searchItem);
    }

    public SearchResultPage clickOnSearchButton() {
        waitForElementToBeClickable(searchButton);
        click(searchButton);
        return new SearchResultPage(driver);
    }
}
