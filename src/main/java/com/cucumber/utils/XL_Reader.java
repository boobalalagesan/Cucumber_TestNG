package com.cucumber.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Boobal Alagesn
 */
public class XL_Reader {

    public String path;
    public FileInputStream inputstream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;


    public XL_Reader(String path) {
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

    public String[][] getDataWithRunMode(String SheetName) throws IOException {
        int index = 0;
        int l_row_num = sheet.getLastRowNum();
        int l_column_Num = sheet.getRow(1).getLastCellNum();

        int dataObjectArraySize = getValidRows();
        String[][] dataObjectArray = new String[dataObjectArraySize][l_column_Num];

        DataFormatter dataFormatter = new DataFormatter();
        for (int r = 0; r <= l_row_num; r++) {
            row = sheet.getRow(r);

            String runMode = row.getCell(0).toString();

            if (runMode.trim().equalsIgnoreCase("Y")) {
                //System.out.println("Current Row = "+r);
                //System.out.println("Column = "+l_column_Num);
                //System.out.println("Row = "+l_row_num);
                //System.out.println(runMode);

                for (int c = 0; c < l_column_Num; c++) {
                    cell = row.getCell(c);
                    dataObjectArray[index][c] = dataFormatter.formatCellValue(cell);
                    //System.out.println("current Index value , column = "+index+","+c);

                }
                index++;
            }

        }

        return dataObjectArray;

    }

    public int getValidRows() throws IOException {
        int rows = sheet.getLastRowNum();
        int count = 0;
        for (int row = 0; row <= rows; row++) {
            if (sheet.getRow(row).getCell(0).toString().equalsIgnoreCase("Y")) {
                count++;
            }
        }

        //System.out.println(count);
        return count;
    }

    public String[][] getData(String SheetName) throws IOException {
        int l_row_num = sheet.getLastRowNum();
        int l_column_Num = sheet.getRow(1).getLastCellNum();


        String[][] dataObjectArray = new String[l_row_num][l_column_Num];

        DataFormatter dataFormatter = new DataFormatter();
        for (int r = 0; r < l_row_num; r++) {
            row = sheet.getRow(r);
            for (int c = 0; c < l_column_Num; c++) {
                cell = row.getCell(c);
                dataObjectArray[r][c] = dataFormatter.formatCellValue(cell);
                //System.out.println(dataObjectArray[r][c]);
            }

        }
        return dataObjectArray;

    }

    public String getCellData(String sheetName, int RowCount, int ColumnCount) throws IOException {
        if ((RowCount <= 0) && (ColumnCount <= 0)) {
            return "";
        }
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(RowCount);
        cell = row.getCell(ColumnCount);
        DataFormatter dataFormatter = new DataFormatter();
        String CellValue = dataFormatter.formatCellValue(cell);
        return CellValue;

    }

    public String getCellData(String sheetName, int rowNum, String colName) throws IOException {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());

                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            DataFormatter dataFormatter = new DataFormatter();
            String cellvalue = dataFormatter.formatCellValue(cell);
            //System.out.println(cellText);
            return cellvalue;

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }


    }

    public String getCellData(String sheetName, String rowName, String colName) throws IOException {
        try {
            int rowNum = getStringRowNum(sheetName, rowName);
            // System.out.println(rowNum);
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                //System.out.println(row.getCell(i).getStringCellValue().trim());

                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum);
            if (row == null)
                return "";
            cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            DataFormatter dataFormatter = new DataFormatter();
            String cellvalue = dataFormatter.formatCellValue(cell);
            //System.out.println(cellText);
            return cellvalue;

        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowName + " or column " + colName + " does not exist in xls";
        }


    }

    public String getEncryptedCellData(String Sheet, int RowCount, int ColumnCount) throws IOException {
        inputstream = new FileInputStream(path);
        workbook = (XSSFWorkbook) WorkbookFactory.create(inputstream, "123qwe");
        sheet = workbook.getSheet(Sheet);
        row = sheet.getRow(RowCount);
        cell = row.getCell(ColumnCount);
        DataFormatter dataFormatter = new DataFormatter();
        String CellValue = dataFormatter.formatCellValue(cell);
        workbook.close();
        inputstream.close();
        return CellValue;
    }

    public int getRowCount(String sheetName) throws IOException {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    public int getColumnCount(String sheetName) throws IOException {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(1).getLastCellNum();
    }

    public int getStringRowNum(String sheetName,String rowName) throws IOException {
        int rows = sheet.getLastRowNum();
        int rowCount = 0;
        Boolean flag = false;
        for (int row = 0; row <= rows; row++) {
            if (sheet.getRow(row).getCell(0).toString().trim().equalsIgnoreCase(rowName.trim())) {
                flag = true;
                break;

            }
            rowCount++;
        }
        if (flag == true)
            return rowCount;
        else
            return -1;

    }

    public String[] getTestData(String sheetName, String testcaseName) throws Throwable {
        sheet = workbook.getSheet(sheetName);
        int columns = getColumnCount(sheetName);
        int index = 0;
        int ActualRowCount = getStringRowNum(sheetName,testcaseName);
        row = sheet.getRow(ActualRowCount);
        String[] dataObjectArray = new String[columns - 1];
        for (int col = 1; col < columns; col++) {
            cell = row.getCell(col);
            DataFormatter dataFormatter = new DataFormatter();
            dataObjectArray[index] = dataFormatter.formatCellValue(cell);
            index++;
        }
        return dataObjectArray;
    }

    public int findCellRow(String sheetName, String cellValue) throws IOException {
        int r=0 ;
        boolean flag = false;
        int row_count = getRowCount(sheetName);
        int col_count = getColumnCount(sheetName);

        for (r = 0; r <= row_count; r++) {
            if (flag) {
                break;
            } else {
                row = sheet.getRow(r);
            }
            for (int c = 0; c < col_count; c++) {
                System.out.println(row.getCell(c).toString().trim());
                if (row.getCell(c).toString().trim().equalsIgnoreCase(cellValue.trim())) {
                    flag = true;
                    //System.out.println("Match found");
                    break;
                }
            }

        }
        if (flag)
            return r;
        else
            return -1;
    }


}
