package com.examples.utils;

import org.openqa.selenium.By;

public class PosElementLocators {
    // Product search relevant locators
    public By closeAdButton = By.cssSelector("button[aria-label='Close']");
    public By searchInput = By.id("gh-search-input");
    public By searchButton = By.className("header-search-button");
    public By screenSizeFilter = By.xpath("//*/div[1]/label/div/input");
    public By onSaleFilter = By.xpath("//button[contains(text(), 'On Sale')]");
    public By productList = By.cssSelector("ul.plp-product-list");
    public By productListItem = By.cssSelector("li.product-list-item");
    public By productTitleLocator = By.cssSelector("h2.product-title");

    // Add to cart relevant locators
    public String productListingSite = "https://www.bestbuy.com/site/geek-squad-certified-refurbished-macbook-pro-13-3-" +
            "laptop-apple-m1-chip-8gb-memory-256gb-ssd-space-gray/6489696.p?skuId=6489696";
    public By addToCartButton = By.xpath("//button[@data-test-id='add-to-cart']");
    public By orderSummary = By.id("cart-order-summary");
    public By inLinePriceBlock = By.cssSelector(".price-block__inline");
    public By orderSummaryPrice = By.xpath("//th[span='Total']/following-sibling::td");
    public By laptopImg = By.xpath("//img[@alt='Geek Squad Certified Refurbished MacBook Pro 13.3\" " +
            "Laptop - Apple M1 chip - 8GB Memory - 256GB SSD - Space Gray']");
}

