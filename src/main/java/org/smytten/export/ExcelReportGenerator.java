package org.smytten.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.smytten.util.Utility;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExcelReportGenerator {

    private final String FOLDER_PATH = "/Users/Vignesh/Desktop/Automation/report/";
    Map<String, String> testResults = new HashMap<>();
    public ExcelReportGenerator( Map<String, String> testResults ){
        this.testResults = testResults;
    }
    public void generateExcelReport() {
        if (testResults == null || testResults.isEmpty()) {
            System.err.println("No test results found. Unable to generate Excel report.");
            return;
        }

        String fileName = "TestReport_" + Utility.getCurrentDateTime() + ".xlsx";
        String filePath = FOLDER_PATH + fileName;

        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {

            Sheet sheet = workbook.createSheet("Test Results");
            createHeaderRow(sheet);
            populateDataRows(sheet, testResults);

            workbook.write(outputStream);
            System.out.println("Excel report generated successfully at: " + filePath);

        } catch (IOException e) {
            System.err.println("Error occurred while generating Excel report: " + e.getMessage());
        }
    }


    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("TestName");
        headerRow.createCell(1).setCellValue("Result");
    }

    private static void populateDataRows(Sheet sheet, Map<String, String> testResults) {
        int rowNum = 0;
        for (Map.Entry<String, String> entry : testResults.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }
    }
}
