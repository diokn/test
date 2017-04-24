package com.ZhiHu.autotest.testCase;

import org.apache.xpath.SourceTree;
import org.apache.xpath.operations.Bool;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BrokenLinkCheck {
    List<HashMap<String,String>> result = new ArrayList<>();
    Document document = null;
    StringBuilder sb = new StringBuilder();
    Boolean flag = true;
    int statusCode;
    String statusMessage;


    public Document getConnectionDocument(String url) {
//      url = "http://www.iwjw.com";
//      url = "http://www.iwjw.com/chuzu/8U4j1HSIuQA/?from=010103&p=2";
        Connection connection = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true);
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        statusCode = connection.response().statusCode();
        statusMessage = connection.response().statusMessage();
        System.out.println(url + " " + statusCode + " " + statusMessage);

        return document;
    }

    public int getConnectionStatus(){
        return statusCode;
    }

    public String getConnectionMessage(){
        return statusMessage;
    }

    public Boolean checkHyperlink(){
        //??????a????????????
        getLinkInfoWithAttr("href");
        return flag;
    }

    public void getLinkInfoWithAttr(String attr) {
        flag = true;
        List<?> elements;
        if (document != null) {
            elements = document.getElementsByAttribute(attr);

            Iterator<?> it = elements.listIterator();
            while (it.hasNext()){
                Element element = (Element)it.next();
                String subUrl = element.attr(attr);
                if (subUrl.startsWith("http://")){
                    Connection conn = Jsoup.connect(subUrl).ignoreContentType(true).ignoreHttpErrors(true);
                    try {
                        Document doc = conn.get();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int code = conn.response().statusCode();
                    String message = conn.response().statusMessage();
                    String HyperText = element.html();
                    //Windows(?!95|98|NT|2000)
                    String fileName = subUrl.replaceAll(".*/", "");
                    String fileType = fileName.replaceAll(".*(?==jpg|js|png|css)", "");
                    System.out.println(subUrl + " " + code + " " + message + " " + HyperText + " " + fileName + " " +fileType);

                    if (subUrl.contains("www.miitbeian.gov.cn")){
                        continue;
                    }

                    if (code == 404 || code ==403){
                        flag = false;
                        sb.append(subUrl).append(" ").append(code).append(" ").append(message).append(" ").append(HyperText).append(fileName).append(fileType).append("\n");
                    }
                }

            }

        }
    }


    public Boolean checkStaticFile(){
        //??????????????????
        getLinkInfoWithAttr("src");
        return flag;
    }


    public String getBrokenLink(){
        System.out.println(sb.toString());
        return sb.toString();
    }


    @Test
    public void test(){
        getConnectionDocument("http://iwjw.com");
        checkHyperlink();
        checkStaticFile();

        System.out.println(sb.toString());

    }

}
