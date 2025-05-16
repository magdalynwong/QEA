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
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginChecking {
    private By accountButtonLocator = By.cssSelector("button[data-lid='hdr_signin']");
    private By createAccountButton = By.cssSelector("a[data-testid='createAccountButton']");
    private By sidePanelHeader = By.xpath("//h2[normalize-space()='Get more with a Best Buy account']");

    private By firstNameInput = By.id("firstName");

    private By passwordInputLocator = By.id("fld-p1");
    private By passwordErrorMessage = By.cssSelector("#fld-p1-text p");

    private By emailInputLocator = By.id("email");
    private By emailErrorMessage = By.cssSelector("#email-text p");


    TestContextSetup setup;
    SearchPage searchPage;
    private WebDriver driver;
    public String createAccountURL;

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

    @Given("I am on the login page")
    public void i_am_on_best_buy_login_page() {
        setup.driver.get("https://www.bestbuy.com");
        setup.wait.until(ExpectedConditions.elementToBeClickable(accountButtonLocator)).click();
        setup.wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
        setup.wait.until(ExpectedConditions.urlContains("/identity/newAccount"));
        Assert.assertTrue(setup.driver.getCurrentUrl().contains("/identity/newAccount"));
    }

    @When("I enter my {string} into the password field")
    public void i_enter_password(String password) {
        WebElement passwordInput = setup.wait.until(ExpectedConditions.elementToBeClickable(passwordInputLocator));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);
    }

    @Then("it should report the password as {string}")
    public void it_should_report_the_password_as_valid(String expectedMessage) {
        boolean isPasswordValid = Boolean.parseBoolean(expectedMessage); // "true" = true, "false" = false
        boolean errorVisible;
        try {
            WebElement err = setup.wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));
            errorVisible = err.isDisplayed();
        } catch (TimeoutException ex) {
            errorVisible = false;
        }

        if(isPasswordValid) { // if the password is valid
            Assert.assertFalse(errorVisible);
        }
        else {
            Assert.assertTrue(errorVisible);
        }
    }

    @And("I enter the email address {string}")
    public void i_enter_email_address(String email) {
        WebElement emailInput = setup.wait.until(ExpectedConditions.elementToBeClickable(emailInputLocator));
        emailInput.clear();
        emailInput.sendKeys(email);
        emailInput.sendKeys(Keys.TAB);
    }

    @Then("it should report the email as {string}")
    public void it_should_report_the_email_as_valid(String expectedMessage) {
        boolean isPasswordValid = Boolean.parseBoolean(expectedMessage);
        boolean errorVisible;

        try {
            WebElement err = setup.wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMessage));
            errorVisible = err.isDisplayed();
        } catch (TimeoutException ex) {
            errorVisible = false;
        }

        if(isPasswordValid) {
            Assert.assertFalse(errorVisible);
        }
        else {
            Assert.assertTrue(errorVisible);
        }
    }
}