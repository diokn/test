package com.ZhiHu.autotest.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author gliu067
 * @createDate:
 *
 */


public class ExcelReadUtils {

	private static final String excelFile = System.getProperty("user.dir") + "/src/com/ZhiHu/testdata/TestData.xls";

	public static Map<String, Map<String, String>> readExcel(String sheetName) {
		Workbook book = null; //
		Map<String, Map<String, String>> resMap = new HashMap<String, Map<String, String>>();
		try {
			book = Workbook.getWorkbook(new File(excelFile)); // 获取excel对象
			System.out.println("get sheet.......");
			Sheet sheet = book.getSheet(sheetName); // 获取指定sheet对象
			int rowNum = sheet.getRows(); // excel的下标是从0开始，和数组一样
			int colNum = sheet.getColumns();
			// 遍历sheet
			for (int i = 1; i < rowNum; i++) {
				Map<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < colNum; j++) {
					map.put(sheet.getCell(j, 0).getContents(), 
							sheet.getCell(j, i).getContents()); // 分别表示横坐标，纵坐标（第i行的第0列）
//					System.out.println("column name:" + sheet.getCell(j, 0).getContents());
				}                                 
				resMap.put(sheet.getCell(0, i).getContents(), map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally {
			if (book != null) {
				book.close();
			}
		}
		return resMap;
	}
	
	public static void main(String[] args) {
		readExcel("sign");
		for(String s:readExcel("sign").keySet()){
			System.out.println("key ="+ s + " , value =" + readExcel("sign").get(s));
		}
	}

}
