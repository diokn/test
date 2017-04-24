package com.ZhiHu.autotest.testCase;




import java.util.Properties;

public class AutomationEnvSetting {

    private final String tag="Test_"+this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length-1];
    public String StorageURL;
    public String firebase_baseUrl;
    public String tokenStr;
    public String AccUid;
    public String TestDataFileSuffix;
    private Properties props=new Properties();

    public AutomationEnvSetting() throws Exception {

//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        InputStream automationConfigStream = loader.getResourceAsStream("assets/AutomationTestConfig.env");
//        props.load(automationConfigStream);

        StorageURL=props.getProperty("StorageURL");
        firebase_baseUrl=props.getProperty("firebase_baseUrl");
        tokenStr=props.getProperty("tokenStr");
        AccUid=props.getProperty("AccUid");
        TestDataFileSuffix=props.getProperty("TestDataFileSuffix");
        //Log.d(tag,"Config values read StorageURL("+StorageURL+"), firebase_baseUrl("+firebase_baseUrl+"), tokenStr("+tokenStr+"), AccUid("+AccUid+"), TestDataFileSuffix("+TestDataFileSuffix+")");
    }

}
