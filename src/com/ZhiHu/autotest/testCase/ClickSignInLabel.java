package com.ZhiHu.autotest.testCase;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.ZhiHu.autotest.driver.WebDriverCreator;
import com.ZhiHu.autotest.pageObject.BasePageObjects;
import com.ZhiHu.autotest.utilities.PropertiesFileUtils;
import com.ZhiHu.autotest.utilities.Utils;
import com.ZhiHu.autotest.utilities.XmlParseHandler;
import com.thoughtworks.selenium.Selenium;
import com.ZhiHu.autotest.utilities.PageCheckHelper;
import jxl.Workbook;

/**
 * this.getClass(): 就是返回当前对象的运行时类(反射机制)
 * Logger.getLogger(this.getClass()):得到一个Logger对象，这个Logger将监视this.getClass()这个运行时类，
 * 这个运行时类里面你可能创建了log.info(""), log.debug("")，……等语句，
 * 那么这些语句就会根据你预先定义的Logger级别来输出你的日志。就跟你写System.out.print("")一样，
 * 不同的是Logger可以根据需要按级别进行日志输出控制,
 * Logger.getLogger(this.getClass())这样写，有什么好处？ 这样一来你只需要在基类中写一行代码就可以了，子类可以直接使用，这也是复用的原则。 
 * 如果你有一个类A,你可以在A类的开始这么写 ,Logger log=Logger.getLogger(A.class) 这就定死了这个Logger只监视A类本身如果B类继承A类，你创建B类对象时，
 * 上面创建的log对象对B是不起作用的。这就就是hard code. 而Logger.getLogger(this.getClass())就不同，你创建B对象时，this就是B对象的引用了，见下列代码：
 */

public class ClickSignInLabel extends BasePageObjects {
	//static: only belows to current class(excluding its object!),final: 只允许赋值一次
	//System.getProperty("user.dir"): 获取当前项目路径
	private static final String ZhiHuMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/ZhihuMap.properties";
	static final String PAGETEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties zhihu_map = PropertiesFileUtils.getProperties(ZhiHuMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PAGETEXT);
	//Map<String, Map<String, String>> signin =ExcelUtils.
	protected XmlParseHandler xmlConfigObj = XmlParseHandler.getConfigDocInstance();
	WebDriver driver;
	Logger logger = Logger.getLogger(this.getClass());

	
	
	@Test(priority = 3)
	public void signIn(){/*

		logger.info("assert the baidu homepage");
		WebElement signIn_link = this.driver.findElement(By.xpath(zhihu_map.getProperty("baiduSigninLink")));
		signIn_link.click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		System.out.println("window amount......." + allWindowHandles.size());
		System.out.println("window title......." + driver.getTitle());

		if(allWindowHandles.size() > 1){   //若set中元素个数 大于 1
			 switchToWindowByTitle("登录百度帐号");
		}
		WebElement username = this.driver.findElement(By.xpath(zhihu_map.getProperty("baiduUserNameInput")));
		username.sendKeys("哈哈哈哈哈哈哈");
		
		WebElement password = this.driver.findElement(By.xpath(zhihu_map.getProperty("baiduPasswordInput")));
		password.click();
		password.sendKeys("123!@abc");*/
		logger.info("pageload testing.........@!@");
		PageCheckHelper.isPageLoaded(driver);
		

		
		
	}
	
	@AfterTest
	public void teardown(){
		System.out.println("********in after test*********");
		//driver.quit();
	}
	

	
	
}
