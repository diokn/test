package com.ZhiHu.autotest.utilities;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class XmlParse {

	private static String configPath = System.getProperty("user.dir")+"/src/com/BOS/resource/config.xml";
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
