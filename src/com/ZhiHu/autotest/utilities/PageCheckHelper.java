package com.ZhiHu.autotest.utilities;

import java.util.concurrent.TimeUnit;

import org.junit.internal.runners.statements.ExpectException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.jna.Function;

public class PageCheckHelper {
	
	 
	/**
	 * 1. 显式等待: 就是明确的要等到某个元素的出现或者是某个元素的可点击等条件,等不到,就一直等,如果在规定的时间之内都没找到,那么就抛出Exception.
	 *    WebDriverWait wait = new WebDriverWait(Webdriver driver, seconds)，一般用显式等待;
	 * 2. 隐式等待: 就是在创建driver时，为浏览器对象driver创建一个等待时间，隐式等待的作用是全局的，这个方法是得不到某个元素就等待一段时间，直到拿到某个元素,如果在规定的时间之内都没找到,那么就抛出Exception
	 *    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 * 3. 线程休眠: Thread.sleep(millis); 
	 */
	
	
	/**
	 * 判断某个元素是否成功显示
	 * @param driver
	 * @param ele
	 * @return
	 */
	public static boolean waitForElementDisplay(WebDriver driver, WebElement ele){    //static修饰方法只能访问静态的成员变量、方法，无非就是方便调用，一般只用于调用频繁的工具类，因为消耗内存
		boolean  isDisplay = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {   //??????
				// TODO Auto-generated method stub
				return (ele.isDisplayed());
			}
		});
		
		
		isDisplay = true;
		{
		    Assert.fail("Cannot find this web element in the page:" + ele.getTagName()); //在不检查任何条件的情况下使断言失败
		}
		
		return isDisplay;
	}
	
	
	
	
	
	
	/**
	 * 判断页面是否加载完成
	 */
	public static void isPageLoaded(WebDriver driver){
		//Boolean isLoaded = false;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try{
		wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				executor.executeScript("return document.readyState").equals("complete");
			    System.out.println("pageload successfully********");
				return true;
			}});
		//isLoaded = true;
	   }catch(TimeoutException t){
		 Assert.fail("Sorry, Pageload timeout...");
		}
	
	}
		/*protected Function isPageLoaded() {
	        return new Function() {
	            @Override
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    }
	 
	    public void waitForPageLoad() {
	        WebDriverWait wait = new WebDriverWait(webDriver, 30);
	        wait.until(isPageLoaded());
	    }*/
	
	
	


}