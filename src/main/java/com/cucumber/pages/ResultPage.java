package com.cucumber.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.*;
import com.aventstack.extentreports.*;
import com.cucumber.api.WeatherAPI;

public class ResultPage extends BasePage{
	Logger logger = LogManager.getLogger();
	
	public ResultPage(ExtentTest test, WebDriver driver) {
		super(test,driver);
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//div[@class='temp'][1]") WebElement tempEle;
	
	public void CompareTemp() throws IOException {
		
		try {
			int lowVar=expectedValue-1;
			int highvar=expectedValue+1;
			if((actualValue>=lowVar)&&(actualValue<=highvar)) {
				test.log(Status.PASS, "Temperatures are equal");
			}
			else {
				test.log(Status.FAIL, "Temperatures are not equal");
				Assert.fail();
			}
		} catch (Exception e) {
			logger.error("FAIL: Temperature is not equal");
			Assert.fail();
		}
		}
	public void getUITemperature() {
		try {StringBuffer sb= new StringBuffer(tempEle.getText().toString());  
		for(int i=1;i<=2;i++) {
			sb.deleteCharAt(sb.length()-1); 
		}
		String Stringcount=sb.toString();
	    actualValue=Integer.parseInt(Stringcount);
		takeScreenshot();
		test.log(Status.PASS, "Temperature from UI is "+actualValue);
		
			
		} catch (Exception e) {
			logger.error("FAIL: Unable to fetch temperature from UI");
			Assert.fail();
		}
	}
	public void getAPITemperature() {
		try {
			
			weatherAPI=new WeatherAPI();
			expectedValue= weatherAPI.getTemp(TestCity);
			test.log(Status.PASS, "Temperature from API is "+expectedValue);
			
		} catch (Exception e) {
			logger.error("FAIL: Unable to fetch temperature from API");
			Assert.fail();
		}
	}
		


}
