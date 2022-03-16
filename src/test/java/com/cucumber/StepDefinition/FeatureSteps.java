package com.cucumber.StepDefinition;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.cucumber.pages.BasePage;
import com.cucumber.pages.HomePage;
import com.cucumber.pages.ResultPage;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class FeatureSteps extends BasePage{


	@Before
	public void setUp() {
		logger=Logger.getLogger("Cucumber_TestNG");
		test=extent.createTest("Compare test results");
		driver = openApplication("Chrome");
	}
	@Given("User launch Accuweather appication and search with City")
	public void user_launch_accuweather_appication_and_search_with_city() {

		try {
			homePage=new HomePage(driver, test);
			homePage.serachCity(TestCity);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	@When("User selects proper city in suggestion")
	public void user_selects_proper_city_in_suggestion() {
		try {
			homePage.selectCityfromSuggestion();
		} catch (Exception e) {
			Assert.fail();
		}

	}
	@Then("Compare temperature from API with UI")
	public void compare_temperature_from_api_with_ui() {
		try {
			resultsPage=new ResultPage(driver, test);
			resultsPage.CompareTemp();
		} catch (Exception e) {
			Assert.fail();
		}
	}


	@After
	public void teardown() {
		String Status=test.getStatus().toString();
		System.out.println("--------------------------Test status is "+Status+"----------------------------");
		extent.flush();
		driver.quit();

	}

}

