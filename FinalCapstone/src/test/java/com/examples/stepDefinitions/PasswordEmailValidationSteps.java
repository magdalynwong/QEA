// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.pages.SearchPage;
import com.examples.utils.NegElementLocators;
import com.examples.utils.PosElementLocators;
import com.examples.utils.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class PasswordEmailValidationSteps {

    TestContextSetup setup;
    SearchPage searchPage;
    NegElementLocators negLocators;

    public PasswordEmailValidationSteps(TestContextSetup setup) {
        this.setup = setup;
        this.searchPage = new SearchPage(setup);
        this.negLocators = new NegElementLocators();
    }

    @Given("I am on the login page")
    public void i_am_on_best_buy_login_page() {
        setup.visitLoginPage();
        Assert.assertTrue(setup.driver.getCurrentUrl().contains("/identity/newAccount"));
    }

    @When("I enter my {string} into the password field")
    public void i_enter_password(String password) {
        WebElement passwordInput = setup.waitForElementClickable(negLocators.passwordInputLocator);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);
    }

    @Then("it should report the password as {string}")
    public void it_should_report_the_password_as_valid(String expectedMessage) {
        boolean isPasswordValid = Boolean.parseBoolean(expectedMessage); // "true" = true, "false" = false
        boolean errorVisible;
        try {
            WebElement err = setup.waitForElementVisibility(negLocators.passwordErrorMessage);
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
        WebElement emailInput = setup.waitForElementClickable(negLocators.emailInputLocator);
        emailInput.clear();
        emailInput.sendKeys(email);
        emailInput.sendKeys(Keys.TAB);
    }

    // test email and password validation separately
    @Then("it should report the email as {string}")
    public void it_should_report_the_email_as_valid(String expectedMessage) {
        boolean isPasswordValid = Boolean.parseBoolean(expectedMessage);
        boolean errorVisible;

        try {
            WebElement err = setup.waitForElementVisibility(negLocators.emailErrorMessage);
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
