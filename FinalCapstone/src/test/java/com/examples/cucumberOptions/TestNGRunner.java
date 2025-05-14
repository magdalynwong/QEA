package com.examples.cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java", // <-- **Corrected path for features**
        glue="com.examples.stepDefinitions",    // <-- This glue covers both steps and hooks
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestNGRunner  {
}
