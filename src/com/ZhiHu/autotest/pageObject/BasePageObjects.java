package com.ZhiHu.autotest.pageObject;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ZhiHu.autotest.utilities.PageCheckHelper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePageObjects {
	
	Logger logger = Logger.getLogger(this.getClass());
	WebDriver driver;
	AndroidDriver<AndroidElement> androidDriver;
	
	public void switchToWindowByTitle(String windowTitle){
		
		Set<String> allWindows = driver.getWindowHandles(); //获取所有窗口，getWindowHandles()返回的是一个集合
		Iterator<String> it = allWindows.iterator();
		while(it.hasNext()){
			String handle = it.next();
			WebDriver window = driver.switchTo().window(handle);
			System.out.println("Driver title is: " + driver.getTitle());
			System.out.println("Expected windowTitle is:" + windowTitle);
			
			if(driver.getTitle().equals(windowTitle)){
				System.out.println("Target window:" + driver.getTitle() + "located!!");
				break;
			}
			System.out.println("Title, url = " + window.getTitle() + "," + window.getCurrentUrl());
		}		
	}
	
	//switch to frame/iframe
	public String switchToFrame(String fName){
		logger.info("switch to frame : "+fName);
		String strMainHandler = driver.getWindowHandle();
		driver.switchTo().frame(fName);  //切换到指定的frame
		return strMainHandler;
	}
	
	//switch to window
	public String switchToWindow(String wName){
		logger.info("switch to window : "+wName + "from main page");
		String strMainHandler = driver.getWindowHandle();
		driver.switchTo().window(wName);
		return strMainHandler;   
	}
	
	//switch to new window
	public void switchToNewWindow(String windowTitle){
		/*String parentHandle = driver.getWindowHandle(); //get the current window handle
		System.out.println(parentHandle);*/
		for(String winHandle : driver.getWindowHandles()){       //使用foreach 遍历集合
			System.out.println(winHandle);
			driver.switchTo().window(winHandle);   //切换到当前遍历到的window
			String currWinTitle = driver.switchTo().window(winHandle).getTitle();
			if(currWinTitle == windowTitle){
				break;
			}
		}
		
		
	}
	
	//对指定元素执行JS脚本
	public void executeJS(String script, WebElement ele){
		logger.info("Run the javascript from page ,the java script is:" + script);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(script, ele);
	}
	
	//执行JS脚本
	public Object executeJS(String script){
		logger.info("Run the javascript from page ,the java script is:" + script);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(script);

	}
	
	
	public WebElement findElementByCss(String cssStr){
		logger.info("Find element: "+ cssStr);
		try{
			return driver.findElement(By.cssSelector(cssStr));
		}
		catch(Exception e){
			logger.info("Find element exception: "+e.getMessage());
			return null;
		}
	}
	
	
	public void clearAndTypeString(WebElement ele, String text){
		logger.info("Type the text into :" + ele.getTagName() + ", the text content is:" + text);
		ele.clear();
		ele.sendKeys(text);
		
	}
	/**
	 * Actions类主要定义了一些模拟鼠标mouse,键盘keyboard操作，对于这些操作使用perform()方法进行执行
	 * @param 
	 * @param 
	 */
	
	public void typeStringByActions(WebElement ele,String text){
		logger.info("Type the text into :" + ele.getTagName() + ", the text content is:" + text);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele);
		actions.click();
		actions.sendKeys(text);
		actions.build().perform();//public Action build(); 当前有多于一个actions的前提下使用，build()用于生成一个包含目前已有的所有actions的组合。
		//public void perform();  perform()用于执行actions

	}
	
	
	public void typeStringAndEnter(WebElement ele, String text){
		logger.info("Type the text into :" + ele.getTagName() + ", the text content is:" + text);
		typeStringByActions(ele,text);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();//当前只有一个actions，不需要build()
	}
	
	public void clickElement(WebElement ele){
		logger.info("Click the element:"+ ele.getTagName() + "the text is: " + ele.getText());
		ele.click();
	}
	
	public void clickElementByActions(WebElement ele){
		logger.info("Click the element:"+ ele.getTagName() + "the text is: " + ele.getText());
		Actions actions = new Actions(driver);
		actions.moveToElement(ele); //先找到并定位到元素
		actions.click();
		actions.build().perform();  

	}
	
	public void clickElementByJS(WebElement ele) {
		logger.info("Click the element:"+ ele.getTagName() + "the text is: " + ele.getText());
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; //将WebDriver向下转型，转为JavascriptExecutor，
		jsExecutor.executeScript("arguments[0].click();", ele); //再调用executeScript()方法执行JavaScript 脚本,
		//arguments对象 是一个比较特别的对象，实际是当前函数的一个内置属性，   arguments[0]是指当前函数executeScript的第一个参数,
	}
	
	public void doubleClickElement(WebElement ele){
		logger.info("Double click the element:"+ ele.getTagName() + "the text is: " + ele.getText());
		Actions actions = new Actions(driver);
		actions.doubleClick(ele).perform();  
	}
	
	
	/*public void clickElementAndOpeNewWindow(WebElement ele){
		
	}*/
	
	public void mouseOver(WebElement ele){
		logger.info("Mouse over on element :" + ele.getTagName());
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).clickAndHold().perform();  //click and hold
	}
	
	public void rightClick(WebElement ele){
		logger.info("Right click on element :" + ele.getTagName());
		Actions actions = new Actions(driver);
		actions.contextClick(ele).perform();  //Right click on element
	}
	
	public String getCssValue(WebElement ele, String value){
		logger.info("Css value on element :" + ele.getTagName());
		return ele.getCssValue(value);   ////get the CSS value eg. "background-color"
	}
	
	public void refreshPage(){
		logger.info("Refresh the page");
		driver.navigate().refresh();
	}
	
	public void checkbox(WebElement ele){
		if(!(ele.isSelected())){
			logger.info("Check the checkbox :" + ele.getTagName() + ele.getText());
			ele.click();
		}else{
			logger.info("The checkbox:" + ele.getTagName() + ele.getText() +"had been selected as default...");
		}
	}
	
	public void unCheckbox(WebElement ele){
		if(ele.isSelected()){
			logger.info("Uncheck the checkbox :" + ele.getTagName() + ele.getText());
			ele.click();
		}else{
			logger.info("The checkbox :" + ele.getTagName() + ele.getText() + "has not been selected before");
		}
	}
	
	//select dropdown
	public void selectDropdownByValue(WebElement ele,String value){
		logger.info("Select the value :" + value + "in dropdown list :" + ele.getTagName());
		Select sele = new Select(ele);
		sele.selectByValue(value);
	}
	
	public void selectDropdownByIndex(WebElement ele, int index){
		//PageCheckHelper.waitForElementDisplay(driver, ele);
		logger.info("Try to select item :" + index + "from dropdown :"+ ele.getTagName());
		Select sele = new Select(ele);
		sele.selectByIndex(index);
	}
	
	
	

}
