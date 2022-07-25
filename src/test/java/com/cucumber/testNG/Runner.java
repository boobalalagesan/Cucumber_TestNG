package com.cucumber.testNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( 
		features = "src/test/java/Features/CompareResults.feature", 
		glue = "com.cucumber.StepDefinition",
		monochrome = true,
		dryRun = false,
		plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
				"junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports/Cucumber.html"}
		)


public class Runner extends AbstractTestNGCucumberTests {

}
