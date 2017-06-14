package com.ZhiHu.autotest.testCase;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequest {
    public String url;
    public String jsonString;
    public HashMap<String,String> parametersHashMap = new HashMap<>();
    public Connection conn;
    public Gson gson;

    //Add Parameters to ParametersHashMap
    public void addParameter(String key,String value){
        parametersHashMap.put(key,value);
    }

    //doGet
    public void doGet(){
        String host;  //
        String uri;
        String temp="";

        host = parametersHashMap.get("host");  //返回key对应的value
        uri = parametersHashMap.get("uri");

        for (Object key : parametersHashMap.keySet()) {
            if (!key.toString().equals("host") && !key.toString().equals("uri")) {  //判断当key的值为'host'时

                String value = parametersHashMap.get(key);
                try {
                    value = java.net.URLEncoder.encode(value,"UTF-8");  //对内容按照指定进行编码
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                temp += "&"+key+"="+value;
                System.out.println(key + " : " + value);
            }
        }
        temp =temp.replaceFirst("&","?");

        url = host+uri+temp;

        if(!url.startsWith("http://")){
            url = "http://"+url;
        }

        System.out.println(url);

        conn = Jsoup.connect(url).ignoreContentType(true);

        try {
            Document doc = conn.get();
            jsonString = doc.body().html();
            System.out.println(jsonString);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        parametersHashMap.clear();
    }


    //doPost
    public void doPost(){
        String host;
        String uri;

        host = parametersHashMap.get("host");
        uri = parametersHashMap.get("uri");

        if (uri!=null){
            url = host+uri;
        }else{
            url = host;
        }

        if(!url.startsWith("http://")){
            url = "http://"+url;
        }

        conn = Jsoup.connect(url).ignoreContentType(true);
        System.out.println(url);


        for (Object key : parametersHashMap.keySet()) {
            if (!key.toString().equals("host") && !key.toString().equals("uri")) {
                String value = parametersHashMap.get(key);
                conn.data((String)key,value);
                System.out.println(key+" : "+value);
            }
        }

        try {
            Document doc = conn.get();
            jsonString = doc.body().html();
            System.out.println(jsonString);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        parametersHashMap.clear();

    }


    public String getJsonValue(String param){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonString);
//        System.out.println(key+": "+jsonObject.get(param).getAsString());

        String[] params = param.split(",");
        String returnStr=jsonString;

        for (int i = 0; i < params.length ; i++) {
            returnStr = getJsonValue(returnStr, params[i]);
        }

        return returnStr;
    }

    //判断是否是数字index
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    

    public String getJsonValue(String jsonStr, String param){
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonarray;
        JsonObject jsonObject;
        String result;
        //如果是index 则走数组的流程
        try {
            if (isNumeric(param)) {
                jsonarray = (JsonArray) jsonParser.parse(jsonStr);
                result = jsonarray.get(Integer.parseInt(param)).toString();
            } else {
                jsonObject = (JsonObject) jsonParser.parse(jsonStr);
                result = jsonObject.get(param).toString();
            }
        } catch (Exception e) {
            System.out.println("jsonParse error jsonStr=" + jsonStr + "---e.getMessage" + e.getMessage());
            return "";
        }

        return result;
    }



    public String parseMaptoJsonString(Map<String, String> map){
        if(map.containsKey("host")){
            map.remove("host");
        }
        if(map.containsKey("uri")){
            map.remove("uri");
        }
        gson = new Gson();
        return gson.toJson(map);
    }


    @Test
    public void testGet(){
//        addParameter("host","192.168.1.76:8138");
//        addParameter("uri","/IWAgentSOA/rent/rent/addAppointment.rest");
//        addParameter("userId","1973511");
//        addParameter("agentId","38804");
//        addParameter("memo","abcdefg");
//        addParameter("appointmentTime","2015-11-06 18:07:30");
//        addParameter("seehouseNumber","1");
//        addParameter("houseId","4435229");

        addParameter("host","https://pwc-fso-larson-phase2-uat.firebaseapp.com/viewAppointments/");
        addParameter("uri","/apply/getApplyLog");
        addParameter("applyId","65");
        addParameter("page","1");
        addParameter("rows","50");

        this.doGet();
    }

    @Test
    public void testPost(){
        addParameter("host","121.41.34.206:8138");
        addParameter("uri","/IWAgentSOA/rent/rent/addAppointment.rest");
        addParameter("userId","1973511");
        addParameter("agentId","38804");
        addParameter("memo","abcdefg");
        addParameter("appointmentTime","2015-11-06 18:07:30");
        addParameter("seehouseNumber","1");
        addParameter("houseId","4435229");


//        addParameter("host","192.168.1.76:8160");
//        addParameter("uri","/apply/getApplyLog");
//        addParameter("applyId","65");
//        addParameter("page","1");
//        addParameter("rows","50");

        this.doPost();

        System.out.println(url);
    }


}

