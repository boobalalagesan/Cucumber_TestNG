package com.cucumber.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class HomePage extends BasePage{
	Logger logger = LogManager.getLogger();
	public HomePage(WebDriver driver,ExtentTest test) {
		super(test,driver);
		PageFactory.initElements(driver, this);

	}
	@FindBy (xpath = "//input[@placeholder='Search']") WebElement searchBar;
	@FindBy(xpath = "//div[@class='banner-button policy-accept']")List<WebElement> understandEle;
	public void serachCity(String city){
		try {
			if(understandEle.size()!=0) {
				for (WebElement webElement : understandEle) {
					webElement.click();
				}
			}
			waitUntil(searchBar);
			searchBar.sendKeys(city+Keys.ENTER);

			test.log(Status.PASS, "Searched successfilly");
			takeScreenshot();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@FindBy(xpath = "//div[@class='content-module']//a[1]") WebElement Suggest;
	@FindBy(how = How.ID, using = "google_ads_iframe_/6581/web/in/interstitial/admin/search_0")List<WebElement> totalFrames;
	@FindBy(xpath = "//div[@id='dismiss-button']/div") WebElement dismissButton;

	public void selectCityfromSuggestion() {
		try {
			Suggest.click();
			test.log(Status.PASS, "Clicked suggestion successfilly");
			takeScreenshot();
			TimeUnit.SECONDS.sleep(2);
			if(!(totalFrames.size()==0)) {
				driver.switchTo().frame("google_ads_iframe_/6581/web/in/interstitial/admin/search_0");
				dismissButton.click();
				driver.switchTo().defaultContent();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifyPageTitle() {
		try {
			Assert.assertEquals(driver.getTitle().toString(), "Local, National, & Global Daily Weather Forecast | AccuWeather");
			logger.info("PASS: Page title is matching");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FAIL: Page title is not matching");
			Assert.fail();
		}
		
	}
}


