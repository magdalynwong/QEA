package com.examples.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features="src/test/java",
   glue="com.examples.stepDefinitions",
   monochrome = true,
   plugin = {"pretty", "html:target/cucumber-reports"},
   tags = "@Positive or @Negative"
)

public class TestNGRunner extends AbstractTestNGCucumberTests {

   @Override
   @DataProvider(parallel = true)
   public Object[][] scenarios() {
      return super.scenarios();
   }
}