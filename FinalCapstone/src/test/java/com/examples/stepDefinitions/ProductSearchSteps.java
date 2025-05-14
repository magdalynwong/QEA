// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.utils.TestContextSetup;
import com.examples.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductSearchSteps {
    private By closeAdButtonLocator = By.cssSelector("button[aria-label='Close']");
    private By searchInputLocator = By.id("gh-search-input");
    private By searchButtonLocator = By.className("header-search-button");
    private By screenSizeFilterLocator = By.xpath("//*/div[1]/label/div/input");
    private By onSaleFilterLocator = By.xpath("//button[contains(text(), 'On Sale')]");
    private By productListLocator = By.cssSelector("ul.plp-product-list");
    private By productListItemLocator = By.cssSelector("li.product-list-item");
    private By productTitleLocator = By.cssSelector("h2.product-title");
    TestContextSetup setup;
    SearchPage searchPage;
    private WebDriver driver;

    public ProductSearchSteps(TestContextSetup setup) {
        this.setup = setup;
        this.searchPage = new SearchPage(setup);
    }
    
    @Given("I am on the Best Buy home page")
    public void i_am_on_the_best_buy_home_page() {
        setup.driver.get("https://www.bestbuy.com");
    }

    @When("I close the ad modal")
    public void i_close_the_ad_modal() {
        try {
            WebElement closeButton = setup.wait.until(ExpectedConditions.elementToBeClickable(closeAdButtonLocator));
            closeButton.click();
        } catch (Exception ignored) {
            // No ad modal to close.
        }
    }

    @Then("I should be on the search results page for “macbook pro”")
    public void i_should_be_on_the_search_results_page_for_macbook_pro() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("I performed the search for {string}")
    public void i_performed_the_search_for(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @And("I search for {string}")
    public void i_search_for_macbook_pro(String search) {
        WebElement searchInput = setup.wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
        searchInput.sendKeys(search);
        WebElement searchButton = setup.driver.findElement(searchButtonLocator);
        searchButton.click();
    }

    @And("I check the {double}” - {double}” filter under Screen Size")
    public void i_check_the_filter_under_screen_size(Double inches1, Double inches2) {
        WebElement filterSizeElement = setup.wait.until(ExpectedConditions.elementToBeClickable(screenSizeFilterLocator));
        filterSizeElement.click();
    }

    @And("I check the On Sale filter")
    public void i_check_the_on_sale_filter() {
        WebElement onSaleButton = setup.wait.until(ExpectedConditions.elementToBeClickable(onSaleFilterLocator));
        onSaleButton.click();
    }

    @Then("a macbook pro with {double}” {int}GB Memory and {int}GB SSD should appear")
    public void a_macbook_pro_with_8gb_memory_and_256gb_ssd_should_appear(Double screenSize, Integer memory, Integer ssd) {
        WebElement productList = setup.wait.until(ExpectedConditions.presenceOfElementLocated(productListLocator));
        List<WebElement> productListings = productList.findElements(productListItemLocator);
        boolean productFound = searchPage.productMatchFound(productListings, screenSize, memory, ssd);
        Assert.assertTrue(productFound);
    }
}