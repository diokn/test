package com.ZhiHu.autotest.testCase;




public class FinalStringsLib {


    private final String tag="Test_"+this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length-1];

    public FinalStringsLib() {}

    //Following final static string for login page
    public static final String loginPageTitle="Bundle Builder";
    public static final String loginEmailLbl="Email";
    public static final String loginPasswordLbl="Password";
    public static final String loginForgetPasswordLink="Forgot your password?";
    public static final String loginLoginBtn="LOG IN";
    public static final String loginCreateAccLbl="Don't have an account? Create one";
    public static final String loginHereLink="here.";
    public static final String loginQuestionLbl="QUESTIONS?";
    public static final String loginPhoneNumLbl="Call 1-XXX-XXX-XXXX";
    public static final String loginEmailUsLbl="Email us at sky-support@businessos.net";
    public static final String optionCableQuesLbl="How many rooms need a cable box?";
    public static final String optionOftenQuesLbl="How often do you miss your favorite shows?";
    public static final String optionSpendQuesLbl="How much do you currently spend?";


    public static final String logoutBtn="Log Out";

}
