package com.qasmarts.tests;

import com.qasmarts.framework.pages.TablePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Test class for Table functionality
 * 
 * @author Abhinav Gupta
 */
public class TableTest extends BaseTest {
    private TablePage tablePage;
    
    /**
     * Setup method that runs before each test
     * Initializes page objects
     */
    @BeforeMethod
    public void setUpPageObjects() {
        tablePage = new TablePage(driver);
        tablePage.open(getBaseUrl());
    }
    
    /**
     * Test page elements
     */
    @Test(description = "Verify page elements on Tables page")
    public void testPageElements() {
        // Verify page heading
        Assert.assertEquals(tablePage.getPageHeading(), "Data Tables",
                "Page heading should match expected value");
        
        // Verify number of tables
        Assert.assertEquals(tablePage.getTableCount(), 2,
                "There should be 2 tables on the page");
    }
    
    /**
     * Test table headers
     */
    @Test(description = "Verify table headers")
    public void testTableHeaders() {
        // Get table headers
        List<String> headers = tablePage.getTable1Headers();
        
        // Verify header count
        Assert.assertEquals(headers.size(), 6,
                "Table 1 should have 6 headers");
        
        // Verify specific headers
        Assert.assertTrue(headers.contains("Last Name"),
                "Table headers should include 'Last Name'");
        Assert.assertTrue(headers.contains("First Name"),
                "Table headers should include 'First Name'");
        Assert.assertTrue(headers.contains("Email"),
                "Table headers should include 'Email'");
        Assert.assertTrue(headers.contains("Due"),
                "Table headers should include 'Due'");
        Assert.assertTrue(headers.contains("Web Site"),
                "Table headers should include 'Web Site'");
        Assert.assertTrue(headers.contains("Action"),
                "Table headers should include 'Action'");
    }
    
    /**
     * Test accessing cell data
     */
    @Test(description = "Verify accessing cell data")
    public void testAccessingCellData() {
        // Verify specific cell data
        Assert.assertEquals(tablePage.getTable1CellData(0, 0), "Smith",
                "Cell data at row 0, column 0 should be 'Smith'");
        Assert.assertEquals(tablePage.getTable1CellData(0, 1), "John",
                "Cell data at row 0, column 1 should be 'John'");
        
        // Verify row count
        Assert.assertEquals(tablePage.getTable1RowCount(), 4,
                "Table 1 should have 4 rows (excluding header)");
    }
    
    /**
     * Test getting all table data
     */
    @Test(description = "Verify getting all table data")
    public void testGettingAllTableData() {
        // Get all table data
        List<Map<String, String>> tableData = tablePage.getTable1Data();
        
        // Verify row count
        Assert.assertEquals(tableData.size(), 4,
                "Table data should contain 4 rows");
        
        // Verify data in first row
        Map<String, String> firstRow = tableData.get(0);
        Assert.assertEquals(firstRow.get("Last Name"), "Smith",
                "First row 'Last Name' should be 'Smith'");
        Assert.assertEquals(firstRow.get("First Name"), "John",
                "First row 'First Name' should be 'John'");
        
        // Verify data in last row
        Map<String, String> lastRow = tableData.get(3);
        Assert.assertEquals(lastRow.get("Last Name"), "Conway",
                "Last row 'Last Name' should be 'Conway'");
    }
    
    /**
     * Test sorting table by clicking column header
     */
    @Test(description = "Verify sorting table by clicking column header")
    public void testSortingTable() {
        // Sort by Last Name
        tablePage.sortTable1ByColumn("Last Name");
        
        // Get the Last Name column data after sorting
        List<String> lastNames = tablePage.getTable1ColumnDataByName("Last Name");
        
        // Create a copy and sort it to verify
        List<String> sortedLastNames = new ArrayList<>(lastNames);
        Collections.sort(sortedLastNames);
        
        // Verify the column is sorted ascending
        Assert.assertEquals(lastNames, sortedLastNames,
                "Last names should be sorted in ascending order");
        
        // Sort again to reverse order
        tablePage.sortTable1ByColumn("Last Name");
        
        // Get the Last Name column data after sorting in reverse
        List<String> reverseLastNames = tablePage.getTable1ColumnDataByName("Last Name");
        
        // Create a copy and sort it in reverse to verify
        List<String> reverseSortedLastNames = new ArrayList<>(lastNames);
        Collections.sort(reverseSortedLastNames, Collections.reverseOrder());
        
        // Verify the column is sorted descending
        Assert.assertEquals(reverseLastNames, reverseSortedLastNames,
                "Last names should be sorted in descending order");
    }
    
    /**
     * Test getting column data by name
     */
    @Test(description = "Verify getting column data by name")
    public void testGettingColumnDataByName() {
        // Get Email column data
        List<String> emails = tablePage.getTable1ColumnDataByName("Email");
        
        // Verify email count
        Assert.assertEquals(emails.size(), 4,
                "Email column should have 4 entries");
        
        // Verify all emails contain '@'
        for (String email : emails) {
            Assert.assertTrue(email.contains("@"),
                    "Email should contain '@' symbol: " + email);
        }
    }
    
    /**
     * Test getting column index by name
     */
    @Test(description = "Verify getting column index by name")
    public void testGettingColumnIndexByName() {
        // Get column indices
        int lastNameIndex = tablePage.getColumnIndexByName("Last Name");
        int firstNameIndex = tablePage.getColumnIndexByName("First Name");
        int emailIndex = tablePage.getColumnIndexByName("Email");
        
        // Verify indices
        Assert.assertEquals(lastNameIndex, 0,
                "'Last Name' column should be at index 0");
        Assert.assertEquals(firstNameIndex, 1,
                "'First Name' column should be at index 1");
        Assert.assertEquals(emailIndex, 2,
                "'Email' column should be at index 2");
        
        // Verify invalid column name returns -1
        int invalidIndex = tablePage.getColumnIndexByName("Invalid Column");
        Assert.assertEquals(invalidIndex, -1,
                "Invalid column name should return index -1");
    }
} 