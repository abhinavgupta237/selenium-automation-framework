package com.qasmarts.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class for handling Excel data
 * 
 * @author Abhinav Gupta
 */
public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/";
    
    /**
     * Private constructor to prevent instantiation
     */
    private ExcelUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Gets test data from Excel file
     * 
     * @param excelFileName Excel file name
     * @param sheetName Sheet name
     * @return 2D array of objects with test data
     */
    public static Object[][] getTestData(String excelFileName, String sheetName) {
        Object[][] data = null;
        
        try (FileInputStream fis = new FileInputStream(TEST_DATA_PATH + excelFileName);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();
            
            // Create data array (rowCount rows, colCount columns)
            data = new Object[rowCount][colCount];
            
            // Iterate over rows and columns to get cell data
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i-1][j] = getCellValue(cell);
                }
            }
            
            logger.info("Test data loaded successfully from {}, sheet: {}", excelFileName, sheetName);
        } catch (IOException e) {
            logger.error("Failed to load test data: {}", e.getMessage());
        }
        
        return data;
    }
    
    /**
     * Gets cell value based on cell type
     * 
     * @param cell Cell object
     * @return Cell value as Object
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
} 