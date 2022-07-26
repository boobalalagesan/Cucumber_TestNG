package com.cucumber.testNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( 
		features = "src/test/java/Features", 
		glue = "com.cucumber.StepDefinition",
		tags = "@Regression",
		monochrome = true,
		dryRun = false,
		plugin = { "pretty",
				
				"json:target/cucumber-reports/Cucumber.json",
				"junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports/Cucumber.html",
				"rerun:src/test/resources/failedCases.txt"
				
				}
		)


public class Runner extends AbstractTestNGCucumberTests {

}
