package com.cucumber.utils;

import java.io.IOException;

public class ExcelValidatetor {

    public static void main(String args[]) throws Throwable {
        XL_Reader xl_reader = new XL_Reader("RunData\\TestData.xlsx");
       /* String cellval = xl_reader.getCellData("TestCaseData", 1, 1);
        System.out.println(xl_reader.getRowCount("TestCaseData"));
        System.out.println(xl_reader.getCoumnCount("TestCaseData"));

        String[][] a1= xl_reader.getData("TestCaseData");
        System.out.println(a1[2][1]);*/

        //int res= xl_reader.getStringRowNum("C1R5");

         String[] res= xl_reader.getTestData("TestCaseData", "C1R4");
        for (String a:res) {
            System.out.println(a);
        }


    }
}
