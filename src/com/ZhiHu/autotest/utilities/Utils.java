package com.ZhiHu.autotest.utilities;

import java.util.Calendar;

public class Utils {
	
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);
		return c.toString(); //....
	}

}
