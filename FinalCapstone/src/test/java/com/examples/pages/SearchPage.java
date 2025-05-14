package com.examples.pages;

import com.examples.utils.TestContextSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage {
    private final By productTitleLocator = By.cssSelector("h2.product-title");
    TestContextSetup setup;

    public SearchPage(TestContextSetup setup) {
        this.setup = setup;
    }

    public boolean productMatchFound(List<WebElement> productListings, Double screenSize, Integer memory, Integer ssd) {
        String screenSizeStr = Double.toString(screenSize);
        String memoryStr = Integer.toString(memory);
        String ssdStr = Integer.toString(ssd);

        for (WebElement product : productListings) {
            try {
                WebElement titleElement = setup.wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy
                        (product, productTitleLocator));
                if (titleElement.getText().contains(screenSizeStr) &&
                        titleElement.getText().contains(memoryStr) &&
                        titleElement.getText().contains(ssdStr)) {
                    return true;
                }
            } catch (NoSuchElementException | TimeoutException e) {
                // Skip listing if expected elements are not found
            }
        }
        return false; // Return null if no matching product is found
    }
}
