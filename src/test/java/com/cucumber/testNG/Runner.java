package com.cucumber.testNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( 
		features = "src/test/java/Features/CompareResults.feature", 
		glue = "com.cucumber.StepDefinition",
		monochrome = true,
		dryRun = true,
		plugin = {"pretty","html:target/cucumberReport"}
		)


public class Runner extends AbstractTestNGCucumberTests {

}
