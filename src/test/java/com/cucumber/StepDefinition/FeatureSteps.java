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
        
        driver = setupClass.openBrowser("Chrome");
    }
 
    @Given("User is on Home page")
    public void userOnHomePage() {
 
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
 
    @When("User enters username as {string}")
    public void entersUsername(String userName) throws InterruptedException {
 
        System.out.println("Username Entered");
        driver.findElement(By.name("txtUsername")).sendKeys(userName);
 
    }
 
    @When("User enters password as {string}")
    public void entersPassword(String passWord) throws InterruptedException {
 
        System.out.println("Password Entered");
        driver.findElement(By.name("txtPassword")).sendKeys(passWord);
 
        driver.findElement(By.id("btnLogin")).submit();
    }
 
    @Then("User should be able to login sucessfully")
    public void sucessfullLogin() throws InterruptedException {

 
    }
 
    @After
    public void teardown() {

    	
        driver.quit();
    }
 
}

