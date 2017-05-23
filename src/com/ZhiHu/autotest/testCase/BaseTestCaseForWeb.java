package com.ZhiHu.autotest.testCase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ZhiHu.autotest.driver.WebDriverCreator;
import com.ZhiHu.autotest.utilities.Utils;
import com.ZhiHu.autotest.utilities.XmlParse;
import com.ZhiHu.autotest.utilities.XmlParseHandler;


public class BaseTestCaseForWeb {
	protected XmlParseHandler xmlConfigUtils = XmlParse.getConfigDocInstance();
	Logger logger = Logger.getLogger(this.getClass());
	String screenshotPath = "/src/com/BOS/screenshot/"+Utils.getCurrentDate();
	static final String MSGFILE = System.getProperty("user.dir")
			+ "/src/com/BOS/resource/message.properties";

	protected static WebDriver driver;

	@BeforeTest
	public void setUp() throws Exception {
		logger.info("start setup driver environment ");
		
		driver = WebDriverCreator.getWebDriver(xmlConfigUtils.getNodeValue("env2"));
		
		System.out.print("current Web Driver is: " + driver.toString());
		
		if (driver != null) {
			driver.get(xmlConfigUtils.getNodeValue("coutts"));//please replace the url before executing the scripts
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			Thread.sleep(1000);
		}
	}
	
	/*public static void main(String[] args) {
		
		driver = WebDriverCreator.getWebDriver(xmlConfigUtils.getNodeValue("env2"));
		
		System.out.print("current Web Driver is: " + driver.toString());
		
		if (driver != null) {
			driver.get(xmlConfigUtils.getNodeValue("perfDevUrl"));//please replace the url before executing the scripts
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			//Thread.sleep(1000);
		}
	}*/
	

	@AfterTest
	public void teardown() {
		System.out.println("****** in after test ******");
		//driver.quit();
	}
	
	
	public static void main(String[] args) {
		//WebDriver driver = nul;
		String path = System.getProperty("usr.dir") + "/src/com/ZhiHu/drivers/geckodriver";
		System.setProperty("webdriver.gecko.driver",path);
		FirefoxDriver fd = new FirefoxDriver();		
		/*driver.manage().deleteAllCookies();
		Cookie ck = new Cookie("PHPSESSID","1b0j3d6sw3cg1g3oyk86mkdxr");
		driver.manage().addCookie(ck);*/		
		fd.get("http://localhost:8080/");
		
	}
	
	
	
	
	
	
}
