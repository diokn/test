package com.ZhiHu.autotest.utilities;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ZhiHu.autotest.driver.WebDriverCreator;
import com.ZhiHu.autotest.utilities.XmlParseHandler;

public class BeforeAndAfterTest {
	
	protected XmlParseHandler xmlConfigObj = XmlParse.getConfigDocInstance();
	protected Logger logger = Logger.getLogger(this.getClass()); //包与包之间进行访问，被访问的包中的类及其成员，需要被public修饰，而不同包中的子类还可以直接访问父类中被protected权限修饰的成员。包与包之间只有这两种权限
	protected WebDriver driver;  
	
	@BeforeTest
	public void setUp() throws Exception{
		logger.info("set up driver environment........");
		driver = WebDriverCreator.getWebDriver(xmlConfigObj.getNodeValue("env2"));
		if(driver != null){
			driver.get(xmlConfigObj.getNodeValue("url")); //Load a new web page in the current browser window.
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //隐式等待.作用全局的driver
			driver.manage().window().maximize();  //manage():获取Options接口，该接口用于管理浏览器的信息, maximize():Maximizes the current window
			Thread.sleep(3000);	
	}		
}		
		
	
	@AfterTest
	public void teardown(){
		System.out.println("********teardown*********");
		//driver.quit();
	}

}
