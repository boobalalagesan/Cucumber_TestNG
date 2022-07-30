package com.cucumber.utils;

import com.cucumber.pages.BasePage;

import java.io.IOException;

public class ExcelValidatetor extends BasePage {

    public static void main(String args[]) throws Throwable {
        XL_Reader xl_reader = new XL_Reader(RunConfig.DATA_PATH);
       /* String cellval = xl_reader.getCellData("TestCaseData", 1, 1);
        System.out.println(xl_reader.getRowCount("TestCaseData"));
        System.out.println(xl_reader.getCoumnCount("TestCaseData"));

        String[][] a1= xl_reader.getData("TestCaseData");
        System.out.println(a1[2][1]);*/

        //int res= xl_reader.getStringRowNum("C1R5");

         /*int  res= xl_reader.getStringRowNum("RunSheet", "Fetch UI temperature value");
        System.out.println(res);*/

       /*String q= xl_reader.getCellData(RunConfig.RunSheet,"Fetch UI temperature value","Browser");
       System.out.println(q);*/

       /* XL_Writer xl_writer=new XL_Writer(RunConfig.DATA_PATH);
        xl_writer.setCellData("RunSheet",2,3,"Boobal-VAl");*/

       int a=xl_reader.findCellRow("Sheet1","Validate weather with UI and API");
        System.out.println(a);



    }
}
