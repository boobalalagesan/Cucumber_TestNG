package com.cucumber.pages;

import java.io.IOException;

import com.cucumber.utils.XL_Reader;
import com.cucumber.utils.XL_Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.cucumber.api.WeatherAPI;
import com.cucumber.utils.ExtentReportManager;
import com.cucumber.utils.RunConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Boobal Alagesn
 *
 */
public class BasePage {
	public WebDriver driver;
	public ExtentReports extent=ExtentReportManager.setExtentReport();
	public ExtentTest test;
	Logger logger = LogManager.getLogger();
	public HomePage homePage;
	public ResultPage resultsPage;
	public WeatherAPI weatherAPI;
	public static int row1;
	public static int expectedValue;
	public static int actualValue;
	public static String TestCity;
	public static String ScenarioName;
	public static String BrowserName;


	public static XL_Reader xl_reader= new XL_Reader(RunConfig.DATA_PATH);
	public static XL_Writer xl_writer=new XL_Writer(RunConfig.DATA_PATH);
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
		if(Browser.equalsIgnoreCase("Chrome"))	{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if (Browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if (Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else {
			System.out.println();
		}
		driver.manage().window().maximize();
		logger.info(Browser +" browser launched");
		return driver;
	}
	
	public WebDriver openApplication(String Browser) {
		driver=openBrowser(Browser);
		driver.get(RunConfig.Test_ApplicationURL);
		return driver;
	}
	
	public void waitUntil(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
}
