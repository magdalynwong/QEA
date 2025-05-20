package com.examples.utils;
import org.openqa.selenium.By;

public class NegElementLocators {

    // Password and Email validation
    public By passwordInputLocator = By.id("fld-p1");
    public By passwordErrorMessage = By.cssSelector("#fld-p1-text p");
    public By emailInputLocator = By.id("email");
    public By emailErrorMessage = By.cssSelector("#email-text p");

}
