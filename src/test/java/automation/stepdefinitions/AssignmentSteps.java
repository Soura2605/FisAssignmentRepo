package automation.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.pages.HomePage;
import org.automation.pages.ProductDetailsPage;
import org.automation.pages.SearchResultPage;
import org.automation.utils.ReportManager;
import org.automation.utils.TestDataReader;
import org.automation.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AssignmentSteps {

    private static final Logger logger = LogManager.getLogger(AssignmentSteps.class);
    HomePage homepage;
    SearchResultPage searchResultPage;
    ProductDetailsPage productDetailsPage;

    @Given("User navigates to Ebay Url")
    public void user_navigates_to_ebay_url() {
        ReportManager.logInfo("Step: User navigates to Ebay Url");
        logger.info("Navigating to Ebay URL");
        WebDriver driver = WebDriverManager.getDriver();
        driver.get(TestDataReader.getBaseUrl());
        homepage = new HomePage(driver);
        logger.info("Successfully navigated to Ebay URL");

    }

    @When("User searches for {string}")
    public void userSearchesFor(String searchItem) {
        ReportManager.logInfo("Step: User searches for '" + searchItem + "'");
        logger.info("Searching for item on Ebay");
        homepage.enterSearchTextIntoSearchBox(searchItem);
        searchResultPage = homepage.clickOnSearchButton();
        searchResultPage.validateUserIsOnDesiredProductSearchResultPage(searchItem);
        logger.info("Search completed for item: " + searchItem);
    }


    @And("User clicks on the desired product according to position {string}")
    public void userClicksOnTheDesiredProductAccordingToPosition(String arg0) {
        ReportManager.logInfo("Step: User clicks on the desired product according to position '" + arg0 + "'");
        int productPosition = Integer.parseInt(arg0);
        logger.info("Selecting product at position: " + productPosition);
        productDetailsPage = searchResultPage.selectDesiredProduct(productPosition);
        logger.info("Product selected at position: " + productPosition);
        productDetailsPage.validateUserIsOnProductDetailsPage();
        logger.info("User is on Product Details Page");
    }


    @Then("User adds the product to the cart and verifies the cart count is updated")
    public void userAddsTheProductToTheCartAndVerifiesTheCartCountIsUpdated() {
        ReportManager.logInfo("Step: User adds the product to the cart and verifies the cart count is updated");
        logger.info("Adding product to the cart");
        int initialCartCount = productDetailsPage.clickOnAddToCartButton();
        productDetailsPage.clickOnCloseOverlayButton();
        int modifiedCartCount = productDetailsPage.validateProductIsAddedToCart();

        if (modifiedCartCount > initialCartCount) {
            logger.info("Product successfully added to cart. Cart count updated from " + initialCartCount + " to " + modifiedCartCount);
        } else {
            logger.error("Product not added to cart. Cart count remains at " + initialCartCount);
            throw new AssertionError("Product not added to cart. Cart count remains at " + initialCartCount);
        }
    }
}
