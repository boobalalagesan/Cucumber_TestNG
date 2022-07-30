package com.cucumber.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XL_Writer {

    public String path;
    public FileInputStream inputstream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    public FileOutputStream fileOutputStream;


    public XL_Writer(String path) {
        this.path = path;
        try {
            inputstream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputstream);
            sheet = workbook.getSheetAt(0);
            inputstream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setCellData(String sheetName, int rowNum, int colNum, String value) {

        try {
            inputstream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputstream);

            if (rowNum <= 0)
                return false;
            int index = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(index);
            if (sheet.getRow(rowNum-1) == null) {
                cell = sheet.createRow(rowNum-1).createCell(colNum);
            } else {
                cell = sheet.getRow(rowNum-1).createCell(colNum);
            }

            if (null == cell) {
                cell = sheet.getRow(rowNum-1).createCell(colNum);
            }
            CellStyle cs = workbook.createCellStyle();
            cs.setWrapText(true);
            cell.setCellStyle(cs);
            String strVal = String.valueOf(value);
            cell.setCellValue(strVal);
            //System.out.println(value);
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.println("Exception Occurred while writing to XL Sheet = " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
