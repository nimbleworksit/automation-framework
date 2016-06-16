package com.ensono.app.test.stepDefinitions;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriverException;

import com.ensono.app.utils.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BeforeAfterHooks {
	
	private static final Logger LOGGER = Logger.getLogger(BeforeAfterHooks.class.getName());
    
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    @Before
    public void deleteAllCookies() {        
        LOGGER.info("Deleting all cookies...");
        DriverManager.getDriver().manage().deleteAllCookies();  
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public static void embedScreenshot(Scenario scenario) {
    	
        if ( scenario.isFailed() ) {
        	LOGGER.info("Scenario failed! Browser: " + DriverManager.getDriver() + " Taking screenshot...");
        	scenario.write("Current Page URL is: " + DriverManager.getDriver().getCurrentUrl());        	
            scenario.write("Scenario Failed in: " + DriverManager.getBrowserInfo());
            try {
                  byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                  scenario.embed(screenshot, "target/image");                  
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            	LOGGER.info(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }

}
