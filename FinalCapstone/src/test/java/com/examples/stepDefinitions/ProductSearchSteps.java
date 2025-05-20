// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.utils.TestContextSetup;
import com.examples.utils.PosElementLocators;
import com.examples.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductSearchSteps {
    TestContextSetup setup;
    SearchPage searchPage;
    PosElementLocators locators;

    public ProductSearchSteps(TestContextSetup setup) {
        this.setup = setup;
        this.locators = new PosElementLocators();
        this.searchPage = new SearchPage(setup);
    }

    // Product search flow
    @Given("I am on the Best Buy home page")
    public void i_am_on_the_best_buy_home_page() {
        setup.visit("https://www.bestbuy.com");
    }

    @When("I close the ad modal")
    public void i_close_the_ad_modal() {
        try {
            WebElement closeButton = setup.waitForElementClickable(locators.closeAdButton);
            closeButton.click();
        } catch (Exception ignored) {
            // No ad modal to close.
        }
    }

    @And("I search for {string}")
    public void i_search_for_macbook_pro(String search) {
        WebElement searchInput = setup.waitForElementVisibility(locators.searchInput);
        searchInput.sendKeys(search);
        WebElement searchButton = setup.driver.findElement(locators.searchButton);
        searchButton.click();
    }

    @Then("I should be on the search results page for {string}")
    public void i_should_be_on_the_search_results_page_for(String expectedStr) {
        Assert.assertTrue("Failed to land on macbook pro search results page",
                setup.wait.until(ExpectedConditions.titleContains(expectedStr)));
    }

    @Given("I performed the search for {string}")
    public void i_performed_the_search_for(String expectedVal) {
        setup.visit(locators.searchResultsPage);
    }

    @When("I check the {double}” - {double}” filter under Screen Size")
    public void i_check_the_filter_under_screen_size(Double inches1, Double inches2) {
        WebElement filterSizeElement = setup.waitForElementClickable(locators.screenSizeFilter);
        filterSizeElement.click();
    }

    @And("I click the On Sale filter")
    public void i_click_the_on_sale_filter() {
        WebElement onSaleButton = setup.waitForElementClickable(locators.onSaleFilter);
        onSaleButton.click();
    }

    @Then("a macbook pro with {double}” {int}GB Memory and {int}GB SSD should appear")
    public void a_macbook_pro_with_8gb_memory_and_256gb_ssd_should_appear(Double screenSize, Integer memory, Integer ssd) {
        WebElement productList = setup.waitForElementPresence(locators.productList);
        List<WebElement> productListings = productList.findElements(locators.productListItem);
        boolean productFound = searchPage.productMatchFound(productListings, screenSize, memory, ssd);
        Assert.assertTrue("Product with specified specs not found", productFound);
    }
}