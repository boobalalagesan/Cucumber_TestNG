package com.cucumber.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cucumber.api.Weather;

public class WeatherPage extends BasePage{
	
	public WeatherPage(WebDriver driver,ExtentTest test) {
		super(test,driver);
	}
	
	public int getUITempValue(String city) throws InterruptedException, IOException {
		
		
		 List<WebElement> understandEle=driver.findElements(By.xpath("//div[@class='banner-button policy-accept']"));
		if(understandEle.size()!=0) {
			for (WebElement webElement : understandEle) {
				webElement.click();
			}
		}
		WebElement searchBar=driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchBar.sendKeys(city+Keys.ENTER);
		TimeUnit.SECONDS.sleep(2);
		WebElement Suggest=driver.findElement(By.xpath("//div[@class='content-module']//a[1]"));
		Suggest.click();
		TimeUnit.SECONDS.sleep(2);
		List<WebElement> totalFrames = driver.findElements(By.id("google_ads_iframe_/6581/web/in/interstitial/admin/search_0"));

		if(!(totalFrames.size()==0)) {
			driver.switchTo().frame("google_ads_iframe_/6581/web/in/interstitial/admin/search_0");
			WebElement dismissButton=driver.findElement(By.xpath("//div[@id='dismiss-button']/div"));
			dismissButton.click();
			driver.switchTo().defaultContent();
		}

		WebElement tempEle=driver.findElement(By.xpath("//div[@class='temp'][1]"));
		
		StringBuffer sb= new StringBuffer(tempEle.getText().toString());  
		for(int i=1;i<=2;i++) {
			sb.deleteCharAt(sb.length()-1); 
		}
		String Stringcount=sb.toString();
		int actualcount=Integer.parseInt(Stringcount);
		test.log(Status.PASS, "Temperature from UI is "+actualcount);
		takeScreenshot();
		getAPIValue(city, actualcount);

		return actualcount;
	}

	public void getAPIValue(String city, int actualcount) throws IOException {
		Weather weather=new Weather();
		int expectedValue= weather.getTemp(city);
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
	

