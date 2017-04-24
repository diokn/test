package com.ZhiHu.autotest.testCase;

import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ZhiHu.autotest.pageObject.BasePageObjects;
import com.ZhiHu.autotest.pageObject.SigninPageObject;
import com.ZhiHu.autotest.utilities.BeforeAndAfterTest;
import com.ZhiHu.autotest.utilities.ExcelReadUtils;
import com.ZhiHu.autotest.utilities.PropertiesFileUtils;
import com.ZhiHu.autotest.utilities.Utils;

public class PeggyTestDemo extends BeforeAndAfterTest {
//全都继承，但是有访问限制。protected和public声明的不管子类在哪里都能访问到，而private声明的在哪里都无法访问，没有权限修饰符的就只有在同一个包中的子类能够访问到。
	private static final String PathMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/PathMap.properties";
	static final String PageTEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties path_map = PropertiesFileUtils.getProperties(PathMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PageTEXT);
	Map<String, Map<String, String>> signin = ExcelReadUtils.readExcel("sign");

	
	
	@Test (priority = 3)
	public void signIn(){
		
		logger.info("sign in.........");
		SigninPageObject signObj = new SigninPageObject(driver);
		signObj.signinWithGoogle(signin.get("U-02").get("username"), signin.get("U-02").get("passwd"));
		BasePageObjects baseObj = new BasePageObjects();
		baseObj.clickElement(driver.findElement(By.xpath(path_map.getProperty("SortDropDown"))));
		//baseObj.selectDropdownByIndex(driver.findElement(By.xpath(path_map.getProperty("MostRecent"))), 1);
		baseObj.selectDropdownByValue(driver.findElement(By.xpath(path_map.getProperty("SortDropDown"))), "Most Recent");
	}

}
