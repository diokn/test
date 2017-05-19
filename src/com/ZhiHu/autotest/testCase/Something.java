package com.ZhiHu.autotest.testCase;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ZhiHu.autotest.utilities.PropertiesFileUtils;

public class Something extends BaseTestCaseForWeb{
	private static final String PathMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/PathMap.properties";
	static final String PageTEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties path_map = PropertiesFileUtils.getProperties(PathMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PageTEXT);
	
	

	@AfterTest
	public void teardown() {
		System.out.println("****** in after test ******");
		//driver.quit();
	}
	
	

}
