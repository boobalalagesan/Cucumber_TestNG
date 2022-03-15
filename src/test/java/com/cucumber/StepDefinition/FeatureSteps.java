package com.cucumber.StepDefinition;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.cucumber.pages.BasePage;
import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class FeatureSteps extends BasePage{
    
    
    @Before
    public void setUp() {
       logger=Logger.getLogger("Cucumber_TestNG");
       test=extent.createTest("Compare test results");
        driver = driverSetupClass.openApplication("Chrome");
    }


 
    @After
    public void teardown() {
    	String Status=test.getStatus().toString();
    	System.out.println("Test status is "+Status);
    	extent.flush();
        driver.quit();
        
    }
 
}

