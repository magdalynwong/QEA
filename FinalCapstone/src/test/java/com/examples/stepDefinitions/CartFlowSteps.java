// ... (contents are the same as before) ...
package com.examples.stepDefinitions;

import com.examples.utils.PosElementLocators;
import com.examples.utils.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class CartFlowSteps {
    TestContextSetup setup;
    PosElementLocators posLocators;

    public CartFlowSteps(TestContextSetup setup) {
        this.setup = setup;
        this.posLocators = new PosElementLocators();
    }

    // Add to cart flow
    @Given("I am on the product's listing page")
    public void i_am_on_the_product_s_listing_page() {
        setup.visit(posLocators.productListingPage);
    }

    @When("I click the “Add to Cart” button next to the laptop")
    public void i_click_the_add_to_cart_button_next_to_the_laptop() {
        WebElement addToCartButton = setup.waitForElementClickable(posLocators.addToCartButton);
        addToCartButton.click();
        WebElement orderSummary = setup.waitForElementVisibility(posLocators.orderSummary);
        Assert.assertTrue("Order details not found", orderSummary.isDisplayed());
    }

    @Then("I should see the item in my cart with the total price")
    public void i_should_see_the_item_in_my_cart_with_the_total_price() {
        WebElement laptopImg = setup.waitForElementVisibility(posLocators.laptopImg);
        WebElement totalPrice = setup.waitForElementVisibility(posLocators.orderSummaryPrice);
        Assert.assertTrue("Product item not found on page", laptopImg.isDisplayed());
        Assert.assertTrue("Total price not found on page", totalPrice.isDisplayed());
    }

    // Remove item from cart flow
    @Given("I am on the above page")
    public void i_am_on_the_above_page() {
        setup.visitCartPage();
    }
    @When("I click the {string} link under the item number drop down")
    public void i_click_the_link_under_the_item_number_drop_down(String string) {
        WebElement removeItemButton = setup.waitForElementClickable(posLocators.removeItemButton);
        removeItemButton.click();
    }

    @Then("I should receive the message “We’ve removed this item from your cart.”")
    public void i_should_receive_the_message_we_ve_removed_this_item_from_your_cart() {
        String actualMsg = setup.waitForElementVisibility(posLocators.removedItemMsg).getText();
        String expectedMsg = "We’ve removed this item from your cart.";
        Assert.assertEquals(expectedMsg, actualMsg);
    }
}