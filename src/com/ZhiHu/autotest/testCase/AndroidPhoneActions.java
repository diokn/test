package com.ZhiHu.autotest.testCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AndroidPhoneActions {
	private static AndroidDriver<AndroidElement> androidDriver;
	private static String deviceName = "emulator-5554";  
	private static String platformName="Android";
	private static String platformVersion = "6.0";
	private static String apkPath = System.getProperty("user.dir") + "/apk/douban_94.apk"; 
	File app = new File(apkPath);
	private static String appPackage ="com.douban.frodo";         
	private static String appActivity = ".MainActivity";    
	private Process appiumPr;
	
	
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
	
	
	@Test(priority = 2)
	public void androidActions() throws InterruptedException{
		Thread.sleep(3000);
		androidDriver.pressKeyCode(5);
		Thread.sleep(3000);
		System.out.println("增加音量／");
		
			androidDriver.pressKeyCode(24);
			androidDriver.pressKeyCode(24);
		System.out.println("按电源键");
		androidDriver.pressKeyCode(26);
		Thread.sleep(3000);
		androidDriver.pressKeyCode(26);
		Thread.sleep(3000);
	}
	

	
}
