package com.cucumber.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.cucumber.utils.ExtentReportManager;
import com.cucumber.utils.RunConfig;


public class BasePage {
	public WebDriver driver;
	public ExtentReports extent=ExtentReportManager.setExtentReport();
	public ExtentTest test;
	public SetupClass setupClass;
	public Logger logger;
	public BasePage() {

	}
	public BasePage(ExtentTest test, WebDriver driver) {
		this.driver=driver;
		this.test=test;
	}
	public void takeScreenshot() {
		try {
			String path=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

			test.log(Status.INFO, "ScreenShot",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}

	public WebDriver openBrowser(String Browser) {

		if(Browser.contentEquals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", RunConfig.CHROME_DRIVER_EXE);
			driver= new ChromeDriver();
		}
		else if (Browser.contentEquals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",RunConfig.MOZILLA_DRIVER_EXE);
			driver = new FirefoxDriver();
		}
		else if(Browser.contentEquals("Edge")) {
			System.setProperty("webdriver.edge.driver", RunConfig.EDGE_DRIVER_EXE);
		}
		driver.manage().window().maximize();
		driver.get(RunConfig.Test_ApplicationURL);
		return driver;
	}
}
