package com.examples.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestContextSetup {
    public WebDriver driver;
    public WebDriverWait wait;

    public void visit(String url) {
        driver.get(url);
    }
}
