package com.flipcart.qa.testdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sun.media.sound.InvalidFormatException;

public class TestUtil {
	
	static Workbook book;
	static Sheet sheet;
	public static String datasheetpath = "C:\\Users\\sthorat2\\eclipse-workspace\\FlipCartOnlineSite\\src\\main\\java"
			+ "\\com\\flipcart\\qa\\testdata\\FlipCartData.xlsx";
	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream file = null;
		try
		{
			file= new FileInputStream(datasheetpath);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			book=WorkbookFactory.create(file);
		}
		catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j] = (sheet).getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}

}
