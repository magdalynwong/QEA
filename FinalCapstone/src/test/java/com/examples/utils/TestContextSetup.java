package com.examples.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestContextSetup {
    public WebDriver driver;
    public WebDriverWait wait;
    public PosElementLocators posLocators = new PosElementLocators();

    public void visit(String url) {
        driver.get(url);
    }

    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Used for 'Remove item from cart' testing scenario
    public void visitCartPage() {
        visit(posLocators.productListingPage);
        WebElement addToCartButton = waitForElementClickable(posLocators.addToCartButton);
        addToCartButton.click();
        WebElement laptopImg = waitForElementVisibility(posLocators.laptopImg);
        Assert.assertTrue(laptopImg.isDisplayed());
    }

    // Used for 'Password and email validation' testing scenario
    public void visitLoginPage() {
        visit("https://www.bestbuy.com");
        waitForElementClickable(posLocators.accountButtonLocator).click();
        waitForElementClickable(posLocators.createAccountButton).click();
        wait.until(ExpectedConditions.urlContains("/identity/newAccount"));
    }

}
