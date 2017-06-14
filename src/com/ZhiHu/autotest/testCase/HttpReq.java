package com.ZhiHu.autotest.testCase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.google.gson.Gson;

public class HttpReq {
	public String url;
	public String jsonString;
	public HashMap<String, String> paramHashMap = new HashMap<>();
	public Connection conn;  //fetch the content from the web page, and parse them into Documents
	public Gson gson; //Gson 是 Google 提供的用来在 Java对象和 JSON数据之间进行映射的 Java 类库。可以将一个 JSON 字符串转成一个 Java 对象，或者反过来
	
	
	public void addParameter(String key, String value){  //add parameters to hashmap
		paramHashMap.put(key, value);		
	}
	
	
	
	public void doGet(){
		String host;
		String uri;
		String temp="";
		
		host = paramHashMap.get("host");
		uri = paramHashMap.get("uri");
		
		for(Object key : paramHashMap.keySet()){   // 
			if(!key.toString().equals("host") && !key.toString().equals("uri")){  //
				
				String value = paramHashMap.get(key);
				try {
					value = java.net.URLEncoder.encode(value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}		
	}
}
