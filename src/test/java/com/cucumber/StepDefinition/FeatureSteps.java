package com.cucumber.StepDefinition;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.cucumber.pages.BasePage;
import com.cucumber.pages.HomePage;
import com.cucumber.pages.ResultPage;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class FeatureSteps extends BasePage{
	
		
	Logger logger = LogManager.getLogger();
	@Before
	public void setUp(Scenario s) {
		test=extent.createTest(s.getName());
		logger.info("Executing-> "+s.getName());
	}

	@Given("Launch Accuweather application")
	public void launch_accuweather_application() {
		try {
			driver = openApplication("Chrome");
			logger.info("PASS: Launch Accuweather application");
		} catch (Exception e) {
			logger.error("FAIL: Launch Accuweather application");
			Assert.fail();
		}
		
	}
	@Given("User search with City name as {string}")
	public void user_search_with_city_name_as(String city) {
		try {
			TestCity=city;
			homePage=new HomePage(driver, test);
			homePage.serachCity(TestCity);
			logger.info("PASS: User search with City name");
		} 
		catch (Exception e) {
			logger.error("FAIL: User search with City name");
			Assert.fail();
		}
	}

	@When("Verify Page title")
	public void verify_page_title() {
	   try {
		   homePage=new HomePage(driver, test);
		   homePage.verifyPageTitle();
		   logger.info("PASS: Verify Page title");
	} catch (Exception e) {
		logger.error("FAIL: Verify Page title");
		Assert.fail();
	} 
	}
	@When("User selects proper city in suggestion")
	public void user_selects_proper_city_in_suggestion() {
		try {
			homePage.selectCityfromSuggestion();
			logger.info("PASS: User selects proper city in suggestion");
		} catch (Exception e) {
			logger.error("FAIL: User selects proper city in suggestion");
			Assert.fail();
		}

	}
	
	@Then("Get temperature value from UI")
	public void get_temperature_value_from_ui() {
		try {
			resultsPage=new ResultPage(test, driver);
			resultsPage.getUITemperature();;
			logger.info("PASS: Get temperature value from UI");
		} catch (Exception e) {
			logger.error("FAIL: Get temperature value from UI");
			Assert.fail();
		}
	}
	@Then("Get temperature value from API")
	public void get_temperature_value_from_api() {
		try {
			resultsPage=new ResultPage(test, driver);
			resultsPage.getAPITemperature();
			logger.info("PASS: Get temperature value from API");
		} catch (Exception e) {
			logger.error("FAIL: Get temperature value from API");
			Assert.fail();
		}
	}
	@Then("Compare temperature from API with UI")
	public void compare_temperature_from_api_with_ui() {
		try {
			resultsPage=new ResultPage(test, driver);
			resultsPage.CompareTemp();
			logger.info("PASS: Compare temperature from API with UI");
		} catch (Exception e) {
			logger.error("FAIL: Compare temperature from API with UI");
			Assert.fail();
		}
	}


	@After
	public void teardown() {
		String Status=test.getStatus().toString();
		logger.info("--------------------------Test status is "+Status+"----------------------------");
		extent.flush();
		driver.quit();

	}

}

