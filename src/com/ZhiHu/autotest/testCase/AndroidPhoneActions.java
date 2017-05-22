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

public class AndroidPhoneActions extends BaseTestCaseForMobile {
	
	
	
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
		//androidDriver.pressKeyCode(32);
		//androidDriver.scrollTo("");
		Thread.sleep(3000);
	}
	

	
}
