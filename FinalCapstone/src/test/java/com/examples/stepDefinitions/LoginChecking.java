// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.pages.SearchPage;
import com.examples.utils.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginChecking {
    private By accountButtonLocator = By.cssSelector("button[data-lid='hdr_signin']");
    private By createAccountButton = By.cssSelector("a[data-testid='createAccountButton']");
    private By sidePanelHeader = By.xpath("//h2[normalize-space()='Get more with a Best Buy account']");

    private By firstNameInput = By.id("firstName");

    TestContextSetup setup;
    SearchPage searchPage;
    private WebDriver driver;

    public LoginChecking(TestContextSetup setup) {
        this.setup = setup;
        this.searchPage = new SearchPage(setup);
    }


    @Given("I am on the home page bestbuy.com")
    public void i_am_on_best_buy_home_page() {
        setup.driver.get("https://www.bestbuy.com");
    }

    @When("I click on the Account button")
    public void i_click_account_button() {
        WebElement accountButton = setup.wait.until(ExpectedConditions.elementToBeClickable(accountButtonLocator));
        accountButton.click();
    }

    @Then("I should see a side panel with account options")
    public void i_see_side_panel() {
        Assert.assertTrue(setup.wait.until(ExpectedConditions.visibilityOfElementLocated(sidePanelHeader)).isDisplayed());
    }

    @When("I click on the Create Account button")
    public void i_click_create_account_button() {
        WebElement createBtn = setup.wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createBtn.click();
    }

    @Then("I should be redirected to the login page")
    public void i_redirect_to_login_page() {
        setup.wait.until(ExpectedConditions.urlContains("/identity/newAccount"));
        Assert.assertTrue(setup.driver.getCurrentUrl().contains("/identity/newAccount"));
    }

    @And("I can enter all of my account information")
    public void i_can_enter_all_of_my_account_information() {
        setup.wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
    }
}