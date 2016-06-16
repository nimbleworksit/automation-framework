package com.ensono.app.page;

import static org.testng.AssertJUnit.assertFalse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.logging.Logger;

public class BasePage {
    
    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    
    protected final WebDriver driver;
    protected WebDriverWait wait;
   
    protected final By elementToBePresentAfterPageLoad;
    protected final JavascriptExecutor javascriptExecutor;   

    protected BasePage(final WebDriver driver, final By elementToBePresentAfterPageLoad) {
        this.driver = driver;        
        this.elementToBePresentAfterPageLoad = elementToBePresentAfterPageLoad;
        javascriptExecutor = (JavascriptExecutor) driver;

        this.initialiseWaits();
        try {
            assertisPageLoaded();           
        } catch (final Exception e) {
            LOGGER.info("Initializing the Wait Time for Page Load" + e.getMessage());            
        }
    }

    protected BasePage(final WebDriver driver) {
        this.driver = driver;       
        elementToBePresentAfterPageLoad = null;
        javascriptExecutor = (JavascriptExecutor) driver;
        this.initialiseWaits();   
    }
    
    private void assertisPageLoaded() {
        final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver driver) {
    
            	return javascriptExecutor.executeScript("return  document.readyState;").toString()
                       .equals("complete");                
            }
        };
        
        final ExpectedCondition<Boolean> expectation1 = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver d) {
            	return javascriptExecutor.executeScript("return  document.readyState;").toString()
                        .equals("complete");  
            }
        };

        try {
            wait.until(expectation);
            if (elementToBePresentAfterPageLoad != null) {
                waitUntilBusyDivDisaapers();
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementToBePresentAfterPageLoad));
            }
        } catch (final Exception e) {            
            assertFalse("Timeout waiting for Page Load Request to complete.", true);
        }
        try {
            wait.until(expectation1);
        } catch (final Exception e) {
            assertFalse(driver.getCurrentUrl(), true);
        }
    }

    protected void assertPageTitle(final String title) {
        if (!driver.getTitle().contains(title)) {
            throw new IllegalStateException("Title does not match , maybe this is not expected page");
        }
    }

    /**
     * verify whether Alert to be present or not
     * 
     * @param by
     *            Locator to find Element
     */
    protected void actionMoveToElement(final By by) {
        try {
            final Actions builder = new Actions(driver);
            final WebElement element = driver.findElement(by);
            builder.moveToElement(element).build().perform();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }


    // Action Move and click
    protected void actionMoveAndClick(final By by) {
        try {
            final Actions builder = new Actions(driver);
            final WebElement element = driver.findElement(by);
            builder.moveToElement(element).click().build().perform();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }

    
    protected void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        LOGGER.info("Alert is accepted");
    }
    
    public void assertEqual(final String actual,final String excepted) {
    	Assert.assertEquals(actual,excepted);
   }
    
    private void initialiseWaits() {
        wait = new WebDriverWait(driver, 100);
        wait.withTimeout(100, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.ignoring(StaleElementReferenceException.class);
    }


    /**
     * Action Method to click on the locator passed through parameter
     * 
     * @param by
     *            Web Element Locator
     */
    protected void click(final WebElement locator) {        
        wait.until(ExpectedConditions.visibilityOf(locator)).click();
    }

//	public void click(WebElement locator) {
//		locator.click();
//	}

    /**
     * To clear the text in web element locator otherwise it will generate logger for error
     * 
     * @param by
     *            Locator to find Element
     */
    protected void clearText(final By by) {
        try {
            driver.findElement(by).clear();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }
    
    /**
     * Action method to click if the element is present
     * 
     * @param by
     *            Locator to find Element
     */
    protected void clickByPresence(final By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by)).click();
    }

    
    /**
     * Action Method to click By JS
     * 
     * @param by
     *            Web Element Locator
     */
    protected void clickByJS(final By by) {
            final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    /**
     * Click Event By JQuery
     */
    protected void clickByJquery() {
        javascriptExecutor.executeScript("$('input[value=OK]').click(function(){});");
    }

    
    /**
     * Enter or fill the Text in Locator otherwise it will generate logger for error
     * 
     * @param by
     *            Locator to find Element
     * @param text
     *            To be filled In locator
     */
    protected void enterText(final By by, final String text) {
        try {
            driver.findElement(by).sendKeys(text);            
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }

    /**
     * Get Text From the Locator
     * 
     * @param by
     *            Locator to find Element
     */
    protected void findElementGetText(final By by) {
        try {
            driver.findElement(by).getText();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }
    
    
    /**
     * Action Method to clear the text filled on the Locator and fill the Text as you passed as parameter
     * 
     * @param by
     *            Web Element Locator
     * @param text
     *            Value to be filled
     */
    protected void fillText(final WebElement locator, final String text) {
        final WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
        element.clear();        
        element.sendKeys(text);
    }

    /**
     * Click on the element otherwise it will generate logger for error
     * 
     * @param by
     *            Locator to find Element
     */
    protected void findElementClick(final By by) {
        try {
            driver.findElement(by).click();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }

    /**
     * Fill text If element is Present
     * 
     * @param by
     *            Locator to find Element
     * @param text
     *            To Be filled
     */
    protected void fillTextByPresence(final By by, final String text) {
        try {
            final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            element.clear();
            element.sendKeys(text);
        } catch (final TimeoutException e) {
        }
    }
    
    
    /**
     * Action Method to get the value of option which is selected
     * 
     * @param by
     *            Locator to find Element
     * @return Option Value
     */
    protected String getSelectedDropdownOption(final By by) {
        return javascriptExecutor.executeScript("return $('option:selected', $(arguments[0])).text()",
                driver.findElement(by)).toString();
    }

    /**
     * Action Method to get the Text from the Element
     * 
     * @param by
     *            Locator to find Element
     * @return Text
     */
    protected String getText(final By by) {
        if (isDisplayed(by)) {
            final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return element.getText();
        } else
            return null;
    }

    /**
     * Method to get the Attribute value by the Attribute name, if attribute is present in the UI
     * 
     * @param by
     *            Locator to find the element
     * @param attributeName
     *            Whose value want to get
     * @return String Value
     */
    protected String getAttributeValueByAttributeName(final By by, final String attributeName) {
        final WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getAttribute(attributeName);
    }

    /**
     * Method to get the Attribute value by the Attribute name, if attribute is present in the DOM
     * 
     * @param by
     *            Locator to find the element
     * @param attributeName
     *            Whose value want to get
     * @return String Value
     */
    protected String getAttributeValueByAttributeNameByPresence(final By by, final String attributeName) {
        final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getAttribute(attributeName);
    }

    /**
     * To get the Attribute Value from the Locator
     * 
     * @param by
     *            Locator to find Element
     * @param attribute
     */
    protected String getAttribute(final By by, final String attribute) {
            String attributeValue = driver.findElement(by).getAttribute(attribute);
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
            return attributeValue;
    }

    /**
     * Get object by Id otherwise it will generate logger for error.
     * 
     * @param id
     * @return
     */
    protected By getObjectById(final String id) {
        By by = null;
        try {
            by = By.id(id);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by Xpath otherwise it will generate logger for error.
     * 
     * @param xpath
     * @return
     */
    protected By getObjectByXpath(final String xpath) {
        By by = null;
        try {
            by = By.xpath(xpath);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by ClassName otherwise it will generate logger for error.
     * 
     * @param className
     * @return
     */
    protected By getObjectByClassName(final String className) {
        By by = null;
        try {
            by = By.className(className);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by CssSelector otherwise it will generate logger for error.
     * 
     * @return
     */
    protected By getObjectByCssSelector(final String cssSelector) {
        By by = null;
        try {
            by = By.cssSelector(cssSelector);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by LinkText otherwise it will generate logger for error.
     * 
     * @param linkText
     * @return
     */
    protected By getObjectByLinkText(final String linkText) {
        By by = null;
        try {
            by = By.linkText(linkText);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by Name otherwise it will generate logger for error.
     * 
     * @param name
     * @return
     */
    protected By getObjectByName(final String name) {
        By by = null;
        try {
            by = By.name(name);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by TagName otherwise it will generate logger for error.
     * 
     * @param tagName
     * @return
     */
    protected By getObjectByTagName(final String tagName) {
        By by = null;
        try {
            by = By.tagName(tagName);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Get object by PartialLinkText otherwise it will generate logger for error.
     * 
     * @param partialLinkText
     * @return
     */
    protected By getObjectByPartialLinkText(final String partialLinkText) {
        By by = null;
        try {
            by = By.partialLinkText(partialLinkText);
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return by;
    }

    /**
     * Action Method to get the Text from the Element
     * 
     * @param by
     *            Locator to find Element
     * @return Text
     */
    protected String getTextValue(final By by) {
            final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return element.getText();
        }
    
    
    /**
     * Action Method to get the Selected text From the Dropdown
     * 
     * @param by
     *            Locator to find Element
     * @return Text which is selected
     */
    protected String getSelectedDropDownText(final By by) {
        final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        final Select selectText = new Select(element);
        return selectText.getFirstSelectedOption().toString();
    }

    /**
     * Action Method to format the date by passing date, month and Year in the format "yyyy-MM-dd"
     * 
     * @param date
     * @param month
     * @param year
     * @return formatted date
     */
    protected String getFormattedDate(final String date, final String month, final String year) {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(date).append(" ").append(month).append(" ").append(year);
            DateFormat readFormat = null;
            readFormat = new SimpleDateFormat("dd MMM yyyy");
            final Date dateFormat = readFormat.parse(sb.toString().trim());
            final DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            return formatDate.format(dateFormat);
        } catch (final Exception e) {
            LOGGER.info("Error in getFormattedDate method : " + e.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Get Alert Text
     * 
     * @param by
     *            Locator to find Element
     */
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
    
    public static WebElement getParent(WebElement element) {    	
        return element.findElement(By.xpath(".."));
    }
    
    public static List<WebElement> getDropDownOptions(WebElement webElement) {
        Select select = new Select(webElement);
        return select.getOptions();
    }
    
    public static WebElement getDropDownOption(WebElement webElement, String value) {
    	WebElement option = null;
        List<WebElement> options = getDropDownOptions(webElement);
        for (WebElement element : options) {
            if (element.getAttribute("value").equalsIgnoreCase(value)) {
                option = element;
                break;
            }
        }
        return option;
    }
    
    /**
     * Method to check if element is present in the DOM
     * 
     * @param by
     *            Locator to find the element
     * @return True if the element is present in the DOM,otherwise false
     */
    protected boolean isDisplayed(final By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (final NoSuchElementException e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
            return false;
        }
    }

	public Boolean isDisplayed(WebElement locator) {
		try {
			return locator.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException exception) {
			return false;
		}
	}
	
    /**
     * Method To check Whether element is present or not on UI
     * 
     * @param by
     *            Locator to find the element
     * @return Boolean True if it is present otherwise False
     */
    protected boolean isPresent(final By by) {
        if (driver.findElements(by).size() != 0)
            return true;
        else
            return false;
    }

    /**
     * Method To check Whether Text is present or not.
     * 
     * @param by
     *            Locator to find the element
     * @return Boolean True if it is present otherwise False
     */
    protected boolean isTextPresence(final By by) {
        try {
            final WebDriverWait waitTextPresence = new WebDriverWait(driver, 10);
            waitTextPresence.pollingEvery(1000, TimeUnit.MILLISECONDS);
            waitTextPresence.withTimeout(10000, TimeUnit.MILLISECONDS);
            waitTextPresence.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * Check if element is enabled
     * 
     * @param by
     *            Locator to find Element
     * @return Boolean True, if Yes otherwise No
     */
    protected Boolean isEnabled(final By by) {
        Boolean isEnabled = true;
        try {
            isEnabled = driver.findElement(by).isEnabled();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
        return isEnabled;
    }

    /**
     * Action Method to wait for atleast 10 seconds or until element is found whichever is occur first and it checks
     * after every 1000 ms
     * 
     * @param by
     * @return
     */
    protected boolean isVisible(final By by) {
        try {
            final WebDriverWait waitVisible = new WebDriverWait(driver, 10);
            waitVisible.pollingEvery(1000, TimeUnit.MILLISECONDS);
            waitVisible.withTimeout(10000, TimeUnit.MILLISECONDS);
            waitVisible.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * Move to particular element
     * 
     * @param by
     *            Locator to find Element
     */
    protected boolean isAlertPresent() {
        try {
            final WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            LOGGER.info("Alert is not displayed");
            return false;
        }
    }

 

      
    protected boolean isModelBoxPresent() {
        try {
            driver.switchTo().activeElement();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

	public void loadPage(String url){		
        LOGGER.info("Directing browser to:" + url);
        LOGGER.info("try to loadPage [" + url + "]");        
        driver.get(url);        
	}	

    
    /**
     * Action Method to wait until the page is completed loaded
     */
    public void pageLoad() {
        final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                final String str = ((JavascriptExecutor) driver).executeScript("return document.readyState;")
                        .toString();
                return str.equals("complete");
            }
        };
        wait.until(expectation);
    }

    public void pageLoadAInteractive() {
        final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                final String str = ((JavascriptExecutor) driver).executeScript("return document.readyState;")
                        .toString();
                return str.equals("interactive");
            }
        };
        wait.until(expectation);
    }
  
    
    /**
     * Action method to select the Visible Text from the DropDown
     * 
     * @param by
     *            Select Element Locator
     * @param selectByVisibleText
     */
    protected void selectFromDropdownByXpath(final WebElement locator, final String selectByVisibleText) {
     //   if (!getSelectedDropdownOption(locator).equals(selectByVisibleText)) {    	 
            final WebElement dropDownElement = wait.until(ExpectedConditions.visibilityOf(locator));
            final WebElement optionElement = dropDownElement.findElement(By.xpath("//option[contains(text(),'"
                    + selectByVisibleText + "')]"));
            selectDropDown(optionElement);
        }
   // }

    // Delay in selection , it might iterate over all options
    protected void selectFromDropdownByID(final WebElement locator, final String selectByVisibleText) {
 //       if (!getSelectedDropdownOption(by).equals(selectByVisibleText)) {
            final WebElement dropDownElement = wait.until(ExpectedConditions.visibilityOf(locator));

            final List<WebElement> options = dropDownElement.findElements(By.tagName("option"));
            for (final WebElement option : options) {
                if (option.getText().equals(selectByVisibleText)) {
                    selectDropDown(option);
                    break;
                }
            }
 //       }
    }

    /**
     * Select from the Dropdown from the Visible Value
     * 
     * @param by
     *            Locator to find Element
     * @param selectByVisibleValue
     *            Text to be search For
     */
    protected void selectFromDropdownByValue(final By by, final String selectByVisibleValue) {
        final WebElement dropDownElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (!getSelectedDropdownOption(by).equals(selectByVisibleValue)) {
            final WebElement optionElement = dropDownElement.findElement(By.xpath("//option[@value='"
                    + selectByVisibleValue + "']"));
            selectDropDown(optionElement);
        }
    }

    private void selectDropDown(final WebElement optionElement) {
        javascriptExecutor.executeScript("$(arguments[0]).attr('selected','selected').trigger('change');",
                optionElement);
    }

	
    protected void scrollToElement(final By by) {
        try {
            final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            final int elementPosition = element.getLocation().getY();
            Thread.sleep(3000);
            final String js = String.format("window.scroll(0, %s)", elementPosition);
            ((JavascriptExecutor) driver).executeScript(js);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
	    
  
    /**
     * Action Method to select from the Dropdown by text if Web Element is present
     * 
     * @param by
     * @param text
     *            To search for
     */
    protected void selectDropDownByText(final By by, final String text) {
        final WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        final Select selectText = new Select(element);
        selectText.selectByVisibleText(text.trim());
    }


    protected void selectFromDropdownByPresence(final By by, final String selectByVisibleText) {
        if (!getSelectedDropdownOption(by).equals(selectByVisibleText)) {
            final WebElement dropDownElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));

            final List<WebElement> options = dropDownElement.findElements(By.tagName("option"));
            for (final WebElement option : options) {
                if (option.getText().equals(selectByVisibleText)) {
                    selectDropDown(option);
                    break;
                }
            }
        }
    }


    /**
     * Click on the Submit Button
     * 
     * @param by
     *            Locator to find Element
     */
    protected void submitButton(final By by) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by)).submit();
        } catch (final Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }

    
	public void submit(WebElement locator) {
		locator.submit();		
	}
	

    /**
     * Switch to current window
     * 
     * @param by
     *            Locator to find Element
     */
    public void switchWindow() {
        Set<String> knownHandles = driver.getWindowHandles();
        Iterator<String> windows = knownHandles.iterator();
        while (windows.hasNext()) {
            // If windows exits
            String nextWindow = windows.next();
            // Move to next window
            driver.switchTo().window(nextWindow);
        }
    }

    /**
     * Switch out of frame
     * 
     * @param by
     *            Locator to find Element
     */
    public void switchOutOfFrame() {
         driver.switchTo().defaultContent();
    }
    
    /**
     * Switch to frame
     * 
     * @param by
     *            Locator to find Element
     */
    public void switchToFrame(final String frameId) {
         driver.switchTo().frame(frameId);
    }
    
    
	public void type(String inputText, WebElement locator) {
		locator.sendKeys(inputText);		
	}
    
	public void visit(String url) {
		driver.get(url);
	}
	        
   
    public void verifyAssertTrue(final boolean value) {
    	Assert.assertTrue(value);
   }
    
    public void verifyAssertFalse(final boolean value) {
    	Assert.assertFalse(value);
   }

    protected void waitUntilBusyDivDisaapers() {
        final ExpectedCondition<Boolean> expectation2 = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver d) {
                return isLoadingDivPresent();
            }

            private boolean isLoadingDivPresent() {
                return javascriptExecutor.executeScript("return document.getElementById('loadingDiv')!=null;")
                        .toString().equals("false");
            }
        };
        wait.until(expectation2);
    }

    /**
     * Action Method to wait for atleast 20 seconds or until element is found whichever is occur first
     * 
     * @param path
     * @param input
     *            replaceable element in the xpath
     */
    protected void waitForVisibilityOfElement(final String path, final String input) {
        final WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path.replace("XXX", input))));
    }

    /**
     * Action Method to wait for atleast 20 seconds or until element is found whichever is occur first
     * 
     * @param by
     *            Web Element Locator
     */
    protected void waitForVisibilityOfElement(final By by) {
        final WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Action Method to wait until element become invisible
     * 
     * @param by
     *            Web Element Locator
     */
    protected void waitForInVisibilityOfElement(final By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * Wait until expected text is found
     * 
     * @param by
     *            Locator to find the element
     * @param text
     *            To be search in element
     */
    protected void waitForTextPresence(final By by, final String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    /**
     * Wait for the Presence for the element
     * 
     * @param by
     *            Locator to find Element
     */
    protected void waitForPresenceOfElement(final By by) {
        try {
            final WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            LOGGER.info("Element is not displayed [" + by.toString() + " ]");
        }
    }

    // Wait till element is not exit
    protected void waitForElementToNotExist(final By by) {
        final WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.pollingEvery(2000, TimeUnit.MILLISECONDS);
        wait.withTimeout(20000, TimeUnit.MILLISECONDS);
        final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver d) {
                try {
                    d.findElement(by);
                    return false;
                } catch (final Exception e) {
                    return true;
                }
            }
        };
        wait.until(expectation);
    }

    /**
     * Wait for the Presence for the element
     * 
     * @param by
     *            Locator to find Element
     */
 //   protected void waitForElementPresence(final FindBy by) {    
 //           final WebDriverWait wait = new WebDriverWait(driver, 20);
 //           wait.until(ExpectedConditions.presenceOfElementLocated(by));         
 //   }

	protected void waitForElement(WebElement elementToWaitFor){
		waitForElement(elementToWaitFor, null);		
	}
	
	protected void waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
	    if (waitTimeInSeconds == null) {
	    	waitTimeInSeconds = 10;
	    }
	    
	    final WebDriverWait wait = new WebDriverWait(this.driver, waitTimeInSeconds);	    
	    wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
	}
	
    /**
     * Wait for Alert to be visible
     * 
     * @param by
     *            Locator to find Element
     */
    protected void waitForAlertTobePresent() {
        final WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
    }
        
    }