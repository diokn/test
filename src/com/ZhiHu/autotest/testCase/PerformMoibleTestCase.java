package com.ZhiHu.autotest.testCase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class PerformMoibleTestCase {
	
	private static AndroidDriver<AndroidElement> androidDriver;
	private static String deviceName = "emulator-5554";
	private static String platformName = "Android";
	private static String platformVersion = "6.0";
	private static String apkPath = System.getProperty("user.dir") + "/apk/Chrome_298713201.apk";
	File app = new File(apkPath);
	private static String appPackage = "com.android.chrome";
	private static String appActivity = "com.google.android.apps.chrome.help.FeedbackCategoryChooserActivity";
	// private static String appWaitActivity = "";   //solve a common Appium error: "A new session could not be created. ???
	
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("app", app);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("sessionOverride", true);
		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);
		androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}
	
	
	public void SignIn(){
		System.out.println("hello");
	}
	
	
	@AfterTest
	public void tearDown(){
		androidDriver.quit();
		
	}
	

}
