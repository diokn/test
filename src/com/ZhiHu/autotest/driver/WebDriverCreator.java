package com.ZhiHu.autotest.driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * 
 * @author gliu067
 * 根据配置环境获取指定的webdriver类型； 
 *
 */
public class WebDriverCreator {
	
	public static WebDriver getWebDriver(String driverName){
		if(driverName.equalsIgnoreCase("MacChrome")){
			return getMacChromeDriver();
		}else
			System.out.println("get chrome driver failed!!!");
			return null;
	}
	
	
	/**
	 * 创建MacChromedriver进程
	 * ！！！！！需在eclipse中将MacChromedriver文件的execute属性全部勾选上****************
	 * @return
	 */	
	public static WebDriver getMacChromeDriver(){
		//指定chromeDriver插件所在的位置
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/com/ZhiHu/drivers/MacChromedriver");
		/*
		 * ChromeDriver仅是在创建时启动，调用quit时关闭浏览器，ChromeDriver是轻量级的服务若在一个比较大的 测试套件中频繁的启动关闭，
		 * 会增加一个比较明显的延时导致浏览器进程不被关闭的情况发生，为了避免这一状况我们可以通过ChromeDriverService来控制ChromeDriver进程的生死，
		 * 达到用完就关闭的效果避免进程占用情况出现（Running the  server in a child process）,实现如下：
		 */
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(System.getProperty("user.dir")+"/src/com/ZhiHu/drivers/MacChromedriver")).usingAnyFreePort().build();
		try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChromeOptions options = new ChromeOptions(); //设置WebDriver启动chrome为默认用户的配置信息（包括书签，扩展程序等）
		options.addArguments("--test-"); //The arguments to use when starting Chrome
		return new ChromeDriver(options);		
	}
	
	
	private static WebDriver getMacFirefoxDriver(){
		System.setProperty("webdriver.firefox.bin","/Applications/Firefox.app/Contents/MacOS/firefox-bin");	
		FirefoxProfile profile = new FirefoxProfile();    
	    profile.setEnableNativeEvents(true);  //启用被禁用的功能
	    System.out.println("******macFirefox Driver start******");
		return new FirefoxDriver(profile);
	}
	
	
	

}
