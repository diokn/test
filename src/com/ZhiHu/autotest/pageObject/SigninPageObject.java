package com.ZhiHu.autotest.pageObject;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ZhiHu.autotest.utilities.PropertiesFileUtils;
import com.ZhiHu.autotest.utilities.Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SigninPageObject extends BasePageObjects {
	private static final String PathMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/PathMap.properties";
	private static final String PageTEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties path_map = PropertiesFileUtils.getProperties(PathMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PageTEXT);
	
	 
	
	public SigninPageObject(WebDriver driver){
		this.driver = driver;
		Utils.sleep(3000);
		/*if(!(driver.getTitle().equals("BusinessOS FSO Dispatcher")||driver.getTitle().equals("Log in"))){
			logger.info("Peggy Login Page is not loaded");
			throw new IllegalStateException("Peggy Login page is not loaded");			
		}*/
	}
	
	public SigninPageObject(AndroidDriver<AndroidElement> androidDriver ){
		this.androidDriver = androidDriver;
	}
	
	
	
	
	
	/**
	 * sign in with google
	 * 
	 */
	public void signinWithGoogle(String username, String password){
		WebElement SignInWithGoogleBtn = driver.findElement(By.xpath(path_map.getProperty("SignInWithGoogleBtn")));
		SignInWithGoogleBtn.click();
		Set<String> allWindowHandles = driver.getWindowHandles();		
		
		if(allWindowHandles.size()>1){
			switchToWindowByTitle("Sign in - Google Accounts");
			clearAndTypeString(driver.findElement(By.xpath(path_map.getProperty("emailInput"))), username);
			clickElement(driver.findElement(By.xpath(path_map.getProperty("nextBtn"))));
			Utils.sleep(3000);
			clearAndTypeString(driver.findElement(By.xpath(path_map.getProperty("passwordInput"))), password);
			clickElement(driver.findElement(By.xpath(path_map.getProperty("singinBtn"))));
			Utils.sleep(3000);
			switchToWindowByTitle("BusinessOS FSO Dispatcher");

		}
	}
	
	/**
	 * sign in with keyboard(android)
	 * @throws InterruptedException 
	 */
	
	public void signIntoDouban() throws InterruptedException{
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		androidDriver.findElementByXPath("//android.widget.TextView[contains(@text,'我的')]").click();
		androidDriver.findElementById("com.douban.frodo:id/unlongin_name").click();
		androidDriver.findElementById("com.douban.frodo:id/input_user_name").click();
		androidDriver.findElementById("com.douban.frodo:id/input_user_name").sendKeys("15618110786");
		androidDriver.findElementById("com.douban.frodo:id/input_password").sendKeys("Pwcwelcome2");		
		Thread.sleep(3000);
		androidDriver.findElementById("com.douban.frodo:id/sign_in_douban").click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
