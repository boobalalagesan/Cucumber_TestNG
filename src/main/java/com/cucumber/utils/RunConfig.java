package com.cucumber.utils;

import java.io.File;

public class RunConfig {
	public static final String MOZILLA_DRIVER_EXE = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"
			+File.separator+"resources"+File.separator+"driver"+File.separator+"geckodriver.exe";
	public static final String CHROME_DRIVER_EXE = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"
			+File.separator+"resources"+File.separator+"driver"+File.separator+"chromedriver.exe";
	public static final String EDGE_DRIVER_EXE = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"
			+File.separator+"resources"+File.separator+"driver"+File.separator+"msedgedriver.exe";
	
	public static final String REPORT_PATH=System.getProperty("user.dir")+ File.separator+"ExtentReport";
	public static final String Extent_Config_Path=System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"
			+File.separator+"resources"+File.separator+"extentConfig.xml";

	public static String Test_ApplicationURL="https://www.accuweather.com/";
	public static String Write_Sheet="TestData";
}
