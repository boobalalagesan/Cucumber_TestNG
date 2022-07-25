package com.cucumber.pages;

import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.*;
import com.aventstack.extentreports.*;
import com.cucumber.api.WeatherAPI;

public class ResultPage extends BasePage{


	public ResultPage(ExtentTest test, WebDriver driver) {
		super(test,driver);
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//div[@class='temp'][1]") WebElement tempEle;
	
	public void CompareTemp() throws IOException {

		StringBuffer sb= new StringBuffer(tempEle.getText().toString());  
		for(int i=1;i<=2;i++) {
			sb.deleteCharAt(sb.length()-1); 
		}
		String Stringcount=sb.toString();
		int actualcount=Integer.parseInt(Stringcount);
		test.log(Status.PASS, "Temperature from UI is "+actualcount);
		takeScreenshot();
		////////API comparison///////////////
		weatherAPI=new WeatherAPI();
		int expectedValue= weatherAPI.getTemp(TestCity);
		test.log(Status.PASS, "Temperature from API is "+expectedValue);
		int lowVar=expectedValue-1;
		int highvar=expectedValue+1;

		if((actualcount>=lowVar)&&(actualcount<=highvar)) {
			test.log(Status.PASS, "Temperatures are equal");
		}
		else {
			test.log(Status.FAIL, "Temperatures are not equal");
			Assert.fail("Temperatures are not equal");
		}

	}

}
