package com.qasmarts.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page object for Tables page
 * 
 * @author Abhinav Gupta
 */
public class TablePage extends BasePage {
    
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(id = "table1")
    private WebElement table1;
    
    @FindBy(id = "table2")
    private WebElement table2;
    
    @FindBy(css = "#table1 th")
    private List<WebElement> table1Headers;
    
    @FindBy(css = "#table1 tbody tr")
    private List<WebElement> table1Rows;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public TablePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the tables page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/tables");
        waitForPageToLoad();
    }
    
    /**
     * Gets the page heading
     * 
     * @return Page heading text
     */
    public String getPageHeading() {
        return pageHeading.getText();
    }
    
    /**
     * Gets the number of tables on the page
     * 
     * @return Number of tables
     */
    public int getTableCount() {
        List<WebElement> tables = driver.findElements(By.tagName("table"));
        return tables.size();
    }
    
    /**
     * Gets the header texts from table 1
     * 
     * @return List of header texts
     */
    public List<String> getTable1Headers() {
        List<String> headers = new ArrayList<>();
        for (WebElement header : table1Headers) {
            headers.add(header.getText());
        }
        return headers;
    }
    
    /**
     * Gets the number of rows in table 1 (excluding header)
     * 
     * @return Number of rows
     */
    public int getTable1RowCount() {
        return table1Rows.size();
    }
    
    /**
     * Gets cell data from table 1
     * 
     * @param rowIndex Row index (0-based, excluding header)
     * @param columnIndex Column index (0-based)
     * @return Cell text
     */
    public String getTable1CellData(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= table1Rows.size()) {
            throw new IndexOutOfBoundsException("Invalid row index: " + rowIndex);
        }
        
        List<WebElement> cells = table1Rows.get(rowIndex).findElements(By.tagName("td"));
        
        if (columnIndex < 0 || columnIndex >= cells.size()) {
            throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
        }
        
        return cells.get(columnIndex).getText();
    }
    
    /**
     * Gets all data from table 1 as a list of maps
     * Each map represents a row with column headers as keys
     * 
     * @return List of maps containing table data
     */
    public List<Map<String, String>> getTable1Data() {
        List<Map<String, String>> tableData = new ArrayList<>();
        List<String> headers = getTable1Headers();
        
        for (int i = 0; i < table1Rows.size(); i++) {
            Map<String, String> rowData = new HashMap<>();
            List<WebElement> cells = table1Rows.get(i).findElements(By.tagName("td"));
            
            for (int j = 0; j < headers.size() && j < cells.size(); j++) {
                rowData.put(headers.get(j), cells.get(j).getText());
            }
            
            tableData.add(rowData);
        }
        
        return tableData;
    }
    
    /**
     * Sorts table 1 by clicking on the specified column header
     * 
     * @param columnName Name of the column to sort by
     */
    public void sortTable1ByColumn(String columnName) {
        logger.info("Sorting table by column: {}", columnName);
        
        for (WebElement header : table1Headers) {
            if (header.getText().equals(columnName)) {
                header.click();
                waitForPageToLoad();
                return;
            }
        }
        
        throw new IllegalArgumentException("Column not found: " + columnName);
    }
    
    /**
     * Gets the data from a specific column in table 1
     * 
     * @param columnIndex Column index (0-based)
     * @return List of cell values in the column
     */
    public List<String> getTable1ColumnData(int columnIndex) {
        List<String> columnData = new ArrayList<>();
        
        for (WebElement row : table1Rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            
            if (columnIndex < 0 || columnIndex >= cells.size()) {
                throw new IndexOutOfBoundsException("Invalid column index: " + columnIndex);
            }
            
            columnData.add(cells.get(columnIndex).getText());
        }
        
        return columnData;
    }
    
    /**
     * Gets the column index by name
     * 
     * @param columnName Column name
     * @return Column index (0-based), or -1 if not found
     */
    public int getColumnIndexByName(String columnName) {
        List<String> headers = getTable1Headers();
        
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).equals(columnName)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Gets data from a specific column in table 1 by column name
     * 
     * @param columnName Column name
     * @return List of cell values in the column
     */
    public List<String> getTable1ColumnDataByName(String columnName) {
        int columnIndex = getColumnIndexByName(columnName);
        
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column not found: " + columnName);
        }
        
        return getTable1ColumnData(columnIndex);
    }
} 