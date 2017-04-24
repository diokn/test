package com.ZhiHu.autotest.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	private static Properties properties = null; //继承自 hashtable, 存放键值对
	
	
	/**
	 * synchronized的范围是 类的对象/实例，防止多个线程同时访问同一个类对象/实例的synchronized代码块。
	 * static synchronized的范围是 类，防止多个线程同时访问这个类的synchronized代码块。
	 * @return 
	 */
	
	private static synchronized void readPropertiesFile(String propfilePath){
		FileInputStream inputStream=null;
		try {
			inputStream = new FileInputStream(propfilePath); //通过指定的文件路径名创建文件输入流
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(inputStream == null){
			System.out.println("The properties file is null.");
			return; //结束方法的运行
		}
		
		properties = new Properties();
		try {
			properties.load(inputStream);   //获取文件输入流中的键、值（文件内容）
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("properties loading fails exception");
			throw new RuntimeException(e);
		}finally{
			if(inputStream !=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("properties loading fails exception");
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static synchronized Properties getProperties(String propfilePath){
		readPropertiesFile(propfilePath);
		return properties;
	}
}
