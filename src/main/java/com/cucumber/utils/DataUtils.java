package com.cucumber.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * @author Boobal Alagesn
 *
 */
public class DataUtils {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;


    public String[][] getExcelData() throws BiffException, IOException {

        FileInputStream excel = new FileInputStream("RunData\\TestData.xlsx");
        Workbook workbook = Workbook.getWorkbook(excel);
        Sheet sheet = workbook.getSheet(0);

        int rowCount = sheet.getRows();
        int columnCount = sheet.getColumns();

        String testData[][] = new String[rowCount - 1][columnCount];
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                testData[i - 1][j] = sheet.getCell(j, i).getContents();
            }
        }
        return testData;
    }




}
