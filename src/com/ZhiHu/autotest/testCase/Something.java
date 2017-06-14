package com.ZhiHu.autotest.testCase;

import java.util.Properties;
import org.testng.annotations.Test;
import com.ZhiHu.autotest.utilities.PropertiesFileUtils;

public class Something extends BaseTestCaseForWeb {
	private static final String PathMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/PathMap.properties";
	static final String PageTEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties path_map = PropertiesFileUtils.getProperties(PathMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PageTEXT);
	
	

	
	@Test(priority=2)
	public void SignIn(){
		System.out.println("........testing...........");
		System.out.println("----------------------------");
	}	
	

}
