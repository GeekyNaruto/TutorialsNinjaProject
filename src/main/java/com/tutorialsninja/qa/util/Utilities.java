package com.tutorialsninja.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {


	public static final int IMPLICIT_WAIT_TIME = 10;
	
	public static String generateemailtimestamp()
	{
		Date date = new Date();
		String time=date.toString();
		return "arvind"+time.replace(" ", "_").replace(":", "_")+"@gmail.com";
		
	}
	
	public static Object[][] gettestdata(String sheetName)
	{
		XSSFWorkbook workbook = null;
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\data1.xlsx");
		try {
		FileInputStream fisExcel = new FileInputStream(file);
		workbook = new XSSFWorkbook(fisExcel);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		for(int i=0; i<rows;i++) 
		{
			 XSSFRow row = sheet.getRow(i+1);	
			 
		  for(int j=0;j<cols;j++)
		  {
			  XSSFCell cell = row.getCell(j);
			  CellType celltype = cell.getCellType();
			  
			  switch(celltype)
			  {
			  case STRING: data[i][j] = cell.getStringCellValue();break;
			  case NUMERIC: data[i][j] = Integer.toString((int)cell.getNumericCellValue());break;
			  case BOOLEAN: data[i][j]= cell.getBooleanCellValue();
			  }
		  }
	    }
		return data;
		
	}
}

