package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductDetailsPage extends BasePage{

    int initialCartCount1;

    @FindBy(how = How.XPATH, using = "//a[@id='atcBtn_btn_1']")
    private WebElement addToCartButton;

    @FindBy(how = How.XPATH, using = "//button[@aria-label='Close overlay']")
    private WebElement closeOverlayButton;

    @FindBy(how = How.XPATH, using = "//span[contains(@class,'cart__icon')]")
    private WebElement cartIcon;



    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void validateUserIsOnProductDetailsPage() {


            waitForElementToBeVisible(addToCartButton);
            isElementDisplayed(addToCartButton);
    }

    public int clickOnAddToCartButton() {

        String initialCartCount = cartIcon.getAttribute("aria-label");
        initialCartCount1 = Integer.parseInt(initialCartCount.replaceAll("[^0-9]", ""));
        waitForElementToBeClickable(addToCartButton);
        click(addToCartButton);

        return initialCartCount1;
    }

    public void clickOnCloseOverlayButton() {
        waitForElementToBeClickable(closeOverlayButton);
        click(closeOverlayButton);
        waitForCurrentFunctionToComplete();
    }

    public int validateProductIsAddedToCart() {

        String initialCartCount = cartIcon.getAttribute("aria-label");
        int initialCartCount2 = Integer.parseInt(initialCartCount.replaceAll("[^0-9]", ""));

        return initialCartCount2;

    }
}
