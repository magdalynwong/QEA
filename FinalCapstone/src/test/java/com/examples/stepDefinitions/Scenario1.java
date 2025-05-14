// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

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

public class Scenario1 {
    private WebDriver driver;
    private WebDriverWait wait;
    private By closeAdButtonLocator = By.cssSelector("button[aria-label='Close']");
    private By searchInputLocator = By.id("gh-search-input");
    private By searchButtonLocator = By.className("header-search-button");
    private By screenSizeFilterLocator = By.xpath("//*/div[1]/label/div/input");
    private By onSaleFilterLocator = By.xpath("//button[contains(text(), 'On Sale')]");
    private By productListLocator = By.cssSelector("ul.plp-product-list");
    private By productListItemLocator = By.cssSelector("li.product-list-item");
    private By productTitleLocator = By.cssSelector("h2.product-title");

    @Given("I am on the Best Buy home page")
    public void i_am_on_the_best_buy_home_page() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.bestbuy.com");
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    @When("I close the ad modal")
    public void i_close_the_ad_modal() {
        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeAdButtonLocator));
            closeButton.click();
            System.out.println("Ad modal closed.");
        } catch (Exception ignored) {
            System.out.println("No ad modal to close.");
        }
    }

    @And("I search for {string}")
    public void i_search_for_macbook_pro(String search) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
        searchInput.sendKeys(search);
        WebElement searchButton = driver.findElement(searchButtonLocator);
        searchButton.click();
    }

    @And("I check the {double}” - {double}” filter under Screen Size")
    public void i_check_the_filter_under_screen_size(Double inches1, Double inches2) {
        WebElement filterSizeElement = wait.until(ExpectedConditions.elementToBeClickable(screenSizeFilterLocator));
        filterSizeElement.click();
    }

    @And("I check the On Sale filter")
    public void i_check_the_on_sale_filter() {
        WebElement onSaleButton = wait.until(ExpectedConditions.elementToBeClickable(onSaleFilterLocator));
        onSaleButton.click();
    }

    @Then("a macbook pro with {double}” {int}GB Memory and {int}GB SSD should appear")
    public void a_macbook_pro_with_8gb_memory_and_256gb_ssd_should_appear(Double screenSize, Integer memory, Integer ssd) {
        WebElement productList = wait.until(ExpectedConditions.presenceOfElementLocated(productListLocator));
        List<WebElement> productListings = productList.findElements(productListItemLocator);
        boolean productFound = false;

        for (WebElement product : productListings) {
            try {
                WebElement titleElement = wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy
                        (product, productTitleLocator));

                // Relevant laptop details checklist
                String title = titleElement.getText();
                String screenSizeStr = Double.toString(screenSize);
                String memoryStr = Integer.toString(memory);
                String ssdStr = Integer.toString(ssd);

                if (title.contains(screenSizeStr) && title.contains(memoryStr) && title.contains(ssdStr)) {
                    productFound = true;
                    titleElement.click();
                    break;
                }
            } catch (NoSuchElementException | TimeoutException e) {
                // Skip listing since expected elements are not found
            }
        }
        Assert.assertTrue(productFound);
    }
}