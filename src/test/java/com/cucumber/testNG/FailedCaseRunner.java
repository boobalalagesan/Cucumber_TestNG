package com.cucumber.testNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( 
		features = "@src/test/resources/failedCases.txt", 
		glue = "com.cucumber.StepDefinition",
		monochrome = true,
		dryRun = false,
		plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
				"junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports/Cucumber.html",
				"rerun:src/test/resources/failedCases.txt"}
		)
public class FailedCaseRunner extends AbstractTestNGCucumberTests {

}
