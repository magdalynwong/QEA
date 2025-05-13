// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Scenario1 {

    private WebDriver driver;
    private WebDriverWait wait;

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
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
            closeButton.click();
            System.out.println("Ad modal closed.");
        } catch (Exception ignored) {
            System.out.println("No ad modal to close.");
        }
    }

    @And("I search for {string}")
    public void i_search_for_macbook_pro(String search) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-search-input")));
        searchInput.sendKeys(search);
        WebElement searchButton = driver.findElement(By.className("header-search-button"));
        searchButton.click();
    }

    @When("I check the {double}” - {double}” filter under Screen Size")
    public void i_check_the_filter_under_screen_size(Double inches1, Double inches2) {
        WebElement filterSizeElement = wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*/div[1]/label/div/input")));
        filterSizeElement.click();
    }

    @Then("a macbook pro with {double}” 8GB Memory and 256GB SSD should appear")
    public void a_macbook_pro_with_8gb_memory_and_256gb_ssd_should_appear(Double double1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}