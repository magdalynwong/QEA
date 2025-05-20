// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.pages.SearchPage;
import com.examples.utils.PosElementLocators;
import com.examples.utils.TestContextSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginCheckingSteps {

    TestContextSetup setup;
    PosElementLocators locators;

    public LoginCheckingSteps(TestContextSetup setup) {
        this.setup = setup;
        this.locators = new PosElementLocators();
    }

    @Given("I am on the home page bestbuy.com")
    public void i_am_on_best_buy_home_page() {
        setup.visit("https://www.bestbuy.com");
    }

    @When("I click on the Account button")
    public void i_click_account_button() {
        WebElement accountButton = setup.waitForElementClickable(locators.accountButtonLocator);
        accountButton.click();
    }

    @Then("I should see a side panel with account options")
    public void i_see_side_panel() {
        Assert.assertTrue(setup.waitForElementVisibility(locators.sidePanelHeader).isDisplayed());
    }

    @When("I click on the Create Account button")
    public void i_click_create_account_button() {
        WebElement createBtn = setup.waitForElementClickable(locators.createAccountButton);
        createBtn.click();
    }

    @Then("I should be redirected to the login page")
    public void i_redirect_to_login_page() {
        setup.wait.until(ExpectedConditions.urlContains("/identity/newAccount"));
        Assert.assertTrue(setup.driver.getCurrentUrl().contains("/identity/newAccount"));

    }

    @And("I can enter all of my account information")
    public void i_can_enter_all_of_my_account_information() {
        setup.waitForElementVisibility(locators.firstNameInput);
    }

}