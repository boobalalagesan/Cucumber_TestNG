package com.cucumber.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cucumber.utils.RunConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetupClass {

	WebDriver driver;
	
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
		return driver;
	}
	
	public WebDriver openApplication(String Browser) {
		driver=openBrowser(Browser);
		driver.get(RunConfig.Test_ApplicationURL);
		return driver;
	}
}
