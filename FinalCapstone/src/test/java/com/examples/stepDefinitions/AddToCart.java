package com.examples.stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AddToCart {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();

        //Set the search page url
        String strURL="https://www.bestbuy.com";
        driver.manage().window().maximize();
        //Get driver
        driver.get(strURL);



        //Get the WebElement for the Search Field
        WebElement searchField =  driver.findElement(By.id("gh-search-input"));

        //Set the search keyword to "makbook pro"
        searchField.sendKeys("Macbook pro");
        //Get the WebElement for the search button (magnified icon)
        WebElement searchButton = driver.findElement(By.className("header-search-button"));
        //Invoke the search button click to perform the product search
        searchButton.click();
        // Add the first item in the product list
        String addToCartXPath ="//button[@data-test-id='add-to-cart']";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addToCartButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addToCartXPath)));
        addToCartButton.click();
        // Go to cart page
        String goToCartPath ="//a[contains(@href,'/cart')]" ;
        //String goToCartPath ="//contains[@href='/cart']";
        WebElement goToCart = driver.findElement(By.xpath(goToCartPath));
        //WebElement goToCart = driver.findElement(By.className("dot"));
        goToCart.click();

    }
}
 