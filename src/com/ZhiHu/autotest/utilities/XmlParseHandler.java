package com.ZhiHu.autotest.utilities;


import org.w3c.dom.Document;

public class XmlParseHandler {
	private Document configDocObj;
	
	public XmlParseHandler(Document doc){
			this.configDocObj = doc;
	}
	

	/*
	private static Document buildConfigDocObj(){
		*//**
		 * DocumentBuilderFactory用于创建DOM模式的解析器对象 ， DocumentBuilderFactory是一个抽象工厂类，它不能直接实例化，
		 * 但该类提供了一个newInstance方法 ，这个方法会根据本地平台默认安装的解析器，自动创建一个工厂的对象并返回
		 *//*
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //创建DOM模式的解析器对象
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			configDocObj = builder.parse(configPath); //解析xml文档，并返回一个Document对象
			configDocObj.normalize();  //移除空的文本节点，并连接相邻的文本节点
			return configDocObj;		 //获取DOM解析器对象
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
			
			
	}*/
	
	
	public String getNodeValue(String str){
		//String nodeValue = null;		
		String nodeValue = configDocObj.getElementsByTagName(str).item(0).getTextContent();//通过tag name获取节点的值
		return nodeValue;
		
	}
	
	public static void main(String[] args) {
		//XmlParseHandler xph = new XmlParseHandler(doc)
		//System.out.println(configDocObj);
	}

}
