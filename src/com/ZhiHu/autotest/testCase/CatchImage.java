package com.ZhiHu.autotest.testCase;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CatchImage {

	private static final String url = "https://www.baidu.com/";
	private static final String imgUrl_regex = "<img.*src=(.*?)[^>]*?>";
	private static final String imgSrc_regex = "http:\"?(.*?)(\"|>|\\s+)"; 
	

	public static String getPageContent(String pageURL) {
		String pageSource = "";

		try {
			URL url = new URL(pageURL);

			/**
			 * URLConnection: 代表应用程序和 URL 之间的通信链接
			 */
			URLConnection urlconnection = url.openConnection(); // 返回一个URLConnection对象，它表示到 URL所引用的远程对象的连接															
			HttpURLConnection httpUrlConnection = (HttpURLConnection) urlconnection; // 将URLConnection对象转成HttpURLConnection（用于向网站发送get或post网络请求））;
																						 
			int responseCode = httpUrlConnection.getResponseCode(); // 获取页面响应状态码
			if (responseCode == httpUrlConnection.HTTP_OK) {
				System.out.println("页面请求成功");
				InputStream is = httpUrlConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader buff = new BufferedReader(isr);

				while ((pageSource = buff.readLine()) != null) {
					System.out.println(pageSource);
				}
				buff.close();

				// System.out.println(this.driver.getPageSource());
			} else {
				System.out.println("页面请求失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageSource;
	}

	public static List<String> getImageUrl(String html) {
		Matcher matcher = Pattern.compile(imgUrl_regex).matcher(html);//指定为字符串的正则表达式必须首先被编译为Pattern的实例。然后，可将得到的模式用于创建 Matcher匹配器对象，依照正则表达式，该对象可以与任意字符序列匹配
		List<String> urllist= new ArrayList<String>();
		while(matcher.find()){
			urllist.add(matcher.group());
		}
		System.out.println(urllist.toString());
		return urllist;
		
	}
	
	public static void getImageSize(){
		BufferedImage img;
		try {
			img = ImageIO.read(new URL(url).openStream());
			img.getHeight();
			img.getWidth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
	}
	
	
	public static void getHtmlText() throws IOException{
		//File input = new File("");
		Document doc = Jsoup.connect("https://pwc-fso-larson-phase2-uat.firebaseapp.com/viewAppointments/").get();
		Elements ele = doc.getAllElements();   //获取页面源码内容
		for(Element result: ele){   //result为迭代出来的标签
			//System.out.println("标签类型：" + result.toString());
			Elements ele2 = result.getAllElements(); //获取当前元素下的所有元素 (including self, and children of children).
			System.out.println("这是什么内容 ：" + ele2.toString());

			for(Element result2: ele2){
				ele2.text();
				ele2.attr("href");
				//System.out.println(result2.toString());
			}
		}
		//String text = doc.title();
		//return text;
		
	}
	
	public static void getText() throws IOException{
		Document doc = Jsoup.connect("http://wenku.baidu.com/view/20d58edd10661ed9ac51f3e2.html").get();
		Elements ele =  doc.getElementsByClass("article").get(0).getElementsByTag("div");  //获取指定class下的某类标签
		for(int i = 0; i < ele.size(); i++){
			System.out.println(i + "." + ele.get(i).text());
		}
		
		//System.out.println(doc.body().text());
	}
	
	public static void getText_2() throws IOException{
		Document doc = Jsoup.connect("https://www.douban.com/note/609986391/").get();
		String content = doc.toString();
		Document doc2 = Jsoup.parse(content);
		Elements eles = doc2.getElementsByTag("span");
		String divContent = eles.toString();
		System.out.println(divContent);
		
	}
		
	public static void main(String[] args) throws IOException {
		
		/*Document doc = Jsoup.connect("https://read.douban.com/reader/ebook/10570957/").get();
		Elements ele =  doc.getAllElements();*/
		
		/*for(int i = 0; i < ele.size(); i++){
			System.out.println(i + "." + ele.get(i).text());
		}*/
		//System.out.println(ele.text());
		getText();
		
	}

}
