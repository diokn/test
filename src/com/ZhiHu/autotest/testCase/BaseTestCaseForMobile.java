package com.ZhiHu.autotest.testCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTestCaseForMobile {
	
	protected static AndroidDriver<AndroidElement> androidDriver;
	private static String deviceName = "emulator-5554";  
	private static String platformName="Android";
	private static String platformVersion = "6.0";
	private static String apkPath = System.getProperty("user.dir") + "/apk/douban_94.apk"; 
	File app = new File(apkPath);
	private static String appPackage ="com.douban.frodo";         
	private static String appActivity = ".MainActivity";    
	protected Process appiumPr;
	
	
	@BeforeTest
	public void setUp() throws IOException  {
		
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);		
		capabilities.setCapability("newCommandTimeout", 10);
		capabilities.setCapability("sessionOverride", true);
		capabilities.setCapability("noSign", "True");   
		capabilities.setCapability("noReset", "True");  
		capabilities.setCapability("unicodeKeyboard", true);  
		capabilities.setCapability("resetKeyboard", true); 
		capabilities.setCapability("app", app);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	
	@AfterTest
	public void tearDown(){
		androidDriver.quit();
	}

}
