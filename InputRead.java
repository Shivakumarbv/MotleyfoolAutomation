package com.fool.input;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class InputRead {

    public static String Keyword;
    public static String Object;
    public static String Val1;
    public static WebDriver wd;
    public String xlpath = "InputFiles\\FoolInput.xlsx";
    public String orPath = "InputFiles\\Fool.properties";

	
	public String readData(String sheet, int rNo, int clNo) throws Throwable {
        String retValue = null;
        FileInputStream fis = new FileInputStream(xlpath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet st = wb.getSheet(sheet);
        Row rw = st.getRow(rNo);
        Cell cell = rw.getCell(clNo);
        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
        //if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            int str = (int) cell.getNumericCellValue();
            retValue = String.valueOf(str);
        } else {
            retValue = cell.getStringCellValue();
        }
        return retValue;
    } 
    

    public int getRowCount(String sheetName) throws Throwable {
        int retnum = 0;
        FileInputStream fis = new FileInputStream(xlpath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet st = wb.getSheet(sheetName);
        retnum = st.getLastRowNum();
        return retnum;
    }

    public String getObject(String logicalName) throws Throwable {
        String retValue = null;
        FileReader reader = new FileReader(orPath);
        Properties prop = new Properties();
        prop.load(reader);
        retValue = prop.getProperty(logicalName);
        return retValue;
    }

}
