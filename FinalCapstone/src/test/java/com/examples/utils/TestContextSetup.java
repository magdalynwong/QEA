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
    public PosElementLocators locator = new PosElementLocators();

    public void visit(String url) {
        driver.get(url);
    }

    public WebElement waitForElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Used for 'Remove item from cart' testing scenario
    public void visitCartPage() {
        visit(locator.productListingSite);
        WebElement addToCartButton = waitForElementClickable(locator.addToCartButton);
        addToCartButton.click();
        WebElement laptopImg = waitForElementVisibility(locator.laptopImg);
        Assert.assertTrue(laptopImg.isDisplayed());
    }

}
