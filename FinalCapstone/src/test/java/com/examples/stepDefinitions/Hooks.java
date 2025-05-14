package com.examples.stepDefinitions;

import com.examples.utils.TestContextSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {
    private TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        testContextSetup.driver = new ChromeDriver();
        testContextSetup.driver.manage().window().maximize();
        testContextSetup.wait = new WebDriverWait(testContextSetup.driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (testContextSetup.driver != null) {
            testContextSetup.driver.quit();
        }
    }
}