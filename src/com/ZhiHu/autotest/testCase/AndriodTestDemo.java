package com.ZhiHu.autotest.testCase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndriodTestDemo {
	private static AndroidDriver<AndroidElement> androidDriver;
	private static String deviceName = "emulator-5554";  //模拟器
	private static String platformName="Android";
	private static String platformVersion = "6.0";
	private static String apkPath = System.getProperty("user.dir") + "/apk/douban_94.apk"; //安装包路径
	File app = new File(apkPath);
	private static String appPackage ="com.douban.frodo";         //包名
			//"com.sec.android.app.popupcalculator";
			//"com.android.calculator2";
	private static String appActivity = ".MainActivity";    //要启动的activity
	private Process appiumPr;
	//private Alert alert;
	TouchAction touchAction; 
	//   /Users/gliu067/Library/Android/sdk/tools/bin/uiautomatorviewer
	
	
	@BeforeTest
	public void setUp() throws IOException  {
		//appiumPr = Runtime.getRuntime().exec("appium -a 127.0.0.1 -p 4723 --no-reset --session-override");
		//appiumPr = Runtime.getRuntime().exec("/Volumes/Appium/Appium.app/Contents/Resources/node_modules/appium/lib/main.js");
		DesiredCapabilities capabilities = new DesiredCapabilities(); //初始化capabilities
		
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);		
		capabilities.setCapability("app", app);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("newCommandTimeout", 10);
		capabilities.setCapability("sessionOverride", true);
		capabilities.setCapability("noSign", "True");   //no need sign 安装时不对apk进行重签名，否则有的apk在重签名之后无法正常使用
		capabilities.setCapability("noReset", "True");   // 避免再次安装应用
		capabilities.setCapability("unicodeKeyboard", true);  //支持中文
		capabilities.setCapability("resetKeyboard", true);   //重置输入法到原有状态
		androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		//appiumDriver.isAppInstalled(appPackage);
	}
		
	@Test(priority = 2)
	public void lauchApp() throws InterruptedException, MalformedURLException{
		
		System.out.println("appium automation testing...........");
		androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//appiumDriver.findElementById("com.douban.frodo:id/enter_text").click();
		//appiumDriver.findElementByName("我的");   //元素的text属性一般认为是name
		//Assert.assertEquals(actual, expected);
		System.out.println("Sign In ");
		androidDriver.findElementByXPath("//android.widget.TextView[contains(@text,'我的')]").click();
		androidDriver.findElementById("com.douban.frodo:id/unlongin_name").click();
		androidDriver.findElementById("com.douban.frodo:id/input_user_name").click();
		//androidDriver.pressKeyCode(67);
		androidDriver.findElementById("com.douban.frodo:id/input_user_name").sendKeys("15618110786");
		androidDriver.findElementById("com.douban.frodo:id/input_password").sendKeys("Pwcwelcome2");		
		Thread.sleep(3000);
		androidDriver.findElementById("com.douban.frodo:id/sign_in_douban").click();
		Logger.getLogger(this.getClass()).info("用户名为： " + androidDriver.findElementById("com.douban.frodo:id/name").getText());
		
		System.out.println("Sign Out");
		Thread.sleep(2000);
		AndroidElement settings = androidDriver.findElementById("com.douban.frodo:id/settings");
		settings.click();
		//appiumDriver.getPageSource();
		int width = androidDriver.manage().window().getSize().getWidth();
		int height = androidDriver.manage().window().getSize().getHeight();
		System.out.println("屏幕宽、高分别为： " + width +","+ height);
		androidDriver.swipe(width/2, height*3/4, width/2, height/4, 5000);  //滑动到页面底部。 arg1,arg2: 起始的x y坐标 
		Thread.sleep(3000);
		AndroidElement signOut_btn = androidDriver.findElementByXPath("//android.widget.TextView[contains(@text,'退出登录')]");
		signOut_btn.click();   //sign out
		Thread.sleep(5000);
		//System.out.println("clear the cache...");
		//AndroidElement clearCache = androidDriver.findElementByXPath("//android.widget.RelativeLayout[@index='4']");
		//System.out.println(clearCache.getTagName());
		//clearCache.click();
		Thread.sleep(3000);
		Set<String> allHandles = androidDriver.getContextHandles();  //原生安卓应用（native app）,混合应用（native＋webview），纯web应用
		for(String handle: allHandles){
		 System.out.println("获取当前app应用类型： "  + handle); //NATIVE_APP 
		}
		
		//AndroidElement confirm_btn = androidDriver.findElementByXPath("//android.widget.Button[contains(@text,'确定')]");
		AndroidElement confirm_btn = androidDriver.findElementById("android:id/button1");
		confirm_btn.click();
		//touchAction.moveTo(appiumDriver.findElementByXPath("//android.widget.Button[@text,'确定']"));
		Thread.sleep(5000);
		System.out.println("确认退出！");
		
		androidDriver.findElementByXPath("//android.widget.TextView[contains(@text,'我的')]").click();
		AndroidElement signIn_btn = androidDriver.findElementById("com.douban.frodo:id/unlongin_name");
		System.out.println("打印文本内容：" + signIn_btn.getText());
		Assert.assertTrue(signIn_btn.getText().contains("登录"));
		
		Thread.sleep(5000);
	}
	
	
	@AfterTest
	public void tearDown(){
		/*if (androidDriver != null){
		androidDriver.quit(); //结束driver, 但是如果注销当前用户登录状态，此处报错（'org.openqa.selenium.remote.SessionNotFoundException: A session is either terminated or not started (WARNING: The server did not provide any stacktrace information)
		}*/
		androidDriver.closeApp(); //关闭应用，其实就是按home键把应用置于后台
	}
	

	
	
	/*public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); // 初始化capabilities
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", ".Calculator");
		capabilities.setCapability("sessionOverride", true);
		// capabilities.setCapability("app", apkPath);
		AppiumDriver<WebElement> appiumDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		appiumDriver.findElementById("com.android.calculator2:id/digit_1").click();
		appiumDriver.findElementById("com.android.calculator2:id/op_add").click();
		appiumDriver.findElementById("com.android.calculator2:id/digit_9").click();
		appiumDriver.findElementById("com.android.calculator2:id/eq").click();
		System.out.println("结果为： " + appiumDriver.findElementById("com.android.calculator2:id/formula").getText());
	}*/
}
