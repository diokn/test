package com.ZhiHu.autotest.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class XmlParse {

	private static String configPath = System.getProperty("user.dir")+"/src/config.xml"; //获取包含运行环境参数的xml配置文件路径
    private static Document configDoc;
    
    private XmlParse(){

    }
    
    public static XmlParseHandler getConfigDocInstance(){
    	if (configDoc == null){
    		return new XmlParseHandler(buildConfigDoc());
    	} 
    	return new XmlParseHandler(configDoc);
    }
	
	private static Document buildConfigDoc() {	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			configDoc = builder.parse(configPath);
			configDoc.normalize();
			return configDoc;
		} catch (Exception e) {
			e.getMessage();
			return null;
		} 
	}
	
}
