package org.smytten.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExcelReportGenerator {
    private XSSFWorkbook workbook;
    private final String workingDirectory = "/Users/Vignesh/Desktop/Automation/src/main/java/org/smytten/report";

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    String timestamp = now.format(formatter);
    public ExcelReportGenerator() {
        workbook = new XSSFWorkbook();
    }

    public void createTestSheet(String testName, boolean testPassed) {
        Sheet sheet = workbook.createSheet(testName);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Test Name");
        row.createCell(1).setCellValue(testName);
        row = sheet.createRow(1);
        row.createCell(0).setCellValue("Test Status");
        row.createCell(1).setCellValue(testPassed ? "Passed" : "Failed");
    }

    public void generateWorkbook(String className) throws IOException {
        String filePath = workingDirectory + "/TestResults_" +className+ timestamp + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }
}