package com.ZhiHu.autotest.testCase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ZhiHu.autotest.pageObject.SigninPageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class DoubanAutoTest {
	
	private static AndroidDriver<AndroidElement> androidDriver;
	private static String deviceName = "emulator-5554";  //模拟器
	private static String platformName="Android";
	private static String platformVersion = "6.0";
	private static String apkPath = System.getProperty("user.dir") + "/apk/douban_94.apk"; //安装包路径
	File app = new File(apkPath);
	private static String appPackage ="com.douban.frodo";         //包名
	private static String appActivity = ".MainActivity";    //要启动的activity
	
	@BeforeTest
	public void setUp() throws IOException  {
		//appiumPr = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p 4723 --no-reset --session-override");
		//appiumPr = Runtime.getRuntime().exec("/Volumes/Appium/Appium.app/Contents/Resources/node_modules/appium/lib/main.js");
		DesiredCapabilities capabilities = new DesiredCapabilities(); //初始化capabilities
		
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);		
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("app", app);
		//capabilities.setCapability("newCommandTimeout", 10);
		capabilities.setCapability("sessionOverride", true);
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("noReset", "True");  
		capabilities.setCapability("unicodeKeyboard", true);  
		capabilities.setCapability("resetKeyboard", true);
		androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}
	
	@Test(priority = 2)
	public void createNotes() throws InterruptedException{
		System.out.println("1.登录");
		SigninPageObject signObj = new SigninPageObject(androidDriver);
		signObj.signIntoDouban();
		System.out.println("2.写日记");
		AndroidElement notes = androidDriver.findElementById("com.douban.frodo:id/icon_notes");
		notes.click();
		AndroidElement writenotes = androidDriver.findElementById("com.douban.frodo:id/menu_item");
		writenotes.click();
		AndroidElement title = androidDriver.findElementByXPath("//android.widget.EditText[contains(@text,'添加标题')]");
		title.sendKeys("第一篇日记");
		//AndroidElement content = androidDriver.findElementByName("//android.widget.EditText[contains(@text,'添加内容')]");
		AndroidElement content = androidDriver.findElementById("com.douban.frodo:id/rd__text");
		content.sendKeys("hello, this is my first notes...");
		AndroidElement next = androidDriver.findElementById("com.douban.frodo:id/menu_item");
		next.click();
		System.out.println("3.发表日记");
		AndroidElement deliver = androidDriver.findElementById("com.douban.frodo:id/menu_item");
		AndroidElement privacy  = androidDriver.findElementById("com.douban.frodo:id/private_entry");
		privacy.click();
		AndroidElement visiable  = androidDriver.findElementById("com.douban.frodo:id/album_private");		
		visiable.click();		
		deliver.click();
		Thread.sleep(2000);
		androidDriver.pressKeyCode(4);
		
	}
	
	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		AppiumDriver<AndroidElement> app = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}
	
	
	public void tearDown(){
		androidDriver.quit();
	}
	
	
	
	
	
	
	
	

}
