package com.ZhiHu.autotest.testCase;

import java.util.Properties;

import com.ZhiHu.autotest.utilities.PropertiesFileUtils;

public class Something {
	private static final String PathMAP = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/uimapping/PathMap.properties";
	static final String PageTEXT = System.getProperty("user.dir")+"/src/com/ZhiHu/autotest/resource/pagetext.properties";
	Properties path_map = PropertiesFileUtils.getProperties(PathMAP);
	Properties page_text = PropertiesFileUtils.getProperties(PageTEXT);
	
	
	public static void main(String[] args) {
		System.out.println("ok,google...");
	}

}
