package com.qasmarts.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for Drag and Drop page
 * 
 * @author Abhinav Gupta
 */
public class DragAndDropPage extends BasePage {
    
    @FindBy(css = "div.example h3")
    private WebElement pageHeading;
    
    @FindBy(id = "column-a")
    private WebElement columnA;
    
    @FindBy(id = "column-b")
    private WebElement columnB;
    
    @FindBy(css = "#column-a header")
    private WebElement columnAHeader;
    
    @FindBy(css = "#column-b header")
    private WebElement columnBHeader;
    
    /**
     * Constructor
     * 
     * @param driver WebDriver instance
     */
    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Opens the drag and drop page
     * 
     * @param baseUrl Base URL of the application
     */
    public void open(String baseUrl) {
        driver.get(baseUrl + "/drag_and_drop");
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
     * Gets the header text of column A
     * 
     * @return Header text of column A
     */
    public String getColumnAHeaderText() {
        return columnAHeader.getText();
    }
    
    /**
     * Gets the header text of column B
     * 
     * @return Header text of column B
     */
    public String getColumnBHeaderText() {
        return columnBHeader.getText();
    }
    
    /**
     * Drags and drops column A to column B using Actions API
     * Note: This method might not work in all browsers due to HTML5 drag and drop limitations
     */
    public void dragAToB() {
        logger.info("Dragging column A to column B");
        Actions actions = new Actions(driver);
        actions.dragAndDrop(columnA, columnB).perform();
        waitForPageToLoad();
    }
    
    /**
     * Drags and drops column B to column A using Actions API
     * Note: This method might not work in all browsers due to HTML5 drag and drop limitations
     */
    public void dragBToA() {
        logger.info("Dragging column B to column A");
        Actions actions = new Actions(driver);
        actions.dragAndDrop(columnB, columnA).perform();
        waitForPageToLoad();
    }
    
    /**
     * Performs drag and drop using JavaScript (more reliable across browsers)
     * 
     * @param source Source element ID
     * @param target Target element ID
     */
    public void dragAndDropJS(String source, String target) {
        logger.info("Performing JavaScript drag and drop from {} to {}", source, target);
        
        String javaScript = "function createEvent(typeOfEvent) {\n" +
                "  var event = document.createEvent(\"CustomEvent\");\n" +
                "  event.initCustomEvent(typeOfEvent, true, true, null);\n" +
                "  event.dataTransfer = {\n" +
                "    data: {},\n" +
                "    setData: function(key, value) {\n" +
                "      this.data[key] = value;\n" +
                "    },\n" +
                "    getData: function(key) {\n" +
                "      return this.data[key];\n" +
                "    }\n" +
                "  };\n" +
                "  return event;\n" +
                "}\n" +
                "\n" +
                "function dispatchEvent(element, event, transferData) {\n" +
                "  if (transferData !== undefined) {\n" +
                "    event.dataTransfer = transferData;\n" +
                "  }\n" +
                "  if (element.dispatchEvent) {\n" +
                "    element.dispatchEvent(event);\n" +
                "  } else if (element.fireEvent) {\n" +
                "    element.fireEvent(\"on\" + event.type, event);\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "function simulateHTML5DragAndDrop(element, target) {\n" +
                "  var dragStartEvent = createEvent('dragstart');\n" +
                "  dispatchEvent(element, dragStartEvent);\n" +
                "  var dropEvent = createEvent('drop');\n" +
                "  dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n" +
                "  var dragEndEvent = createEvent('dragend');\n" +
                "  dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n" +
                "}\n" +
                "\n" +
                "var source = document.getElementById('" + source + "');\n" +
                "var target = document.getElementById('" + target + "');\n" +
                "simulateHTML5DragAndDrop(source, target);";
                
        ((JavascriptExecutor) driver).executeScript(javaScript);
        waitForPageToLoad();
    }
    
    /**
     * Performs drag and drop from A to B using JavaScript
     */
    public void dragAToBWithJS() {
        dragAndDropJS("column-a", "column-b");
    }
    
    /**
     * Performs drag and drop from B to A using JavaScript
     */
    public void dragBToAWithJS() {
        dragAndDropJS("column-b", "column-a");
    }
} 