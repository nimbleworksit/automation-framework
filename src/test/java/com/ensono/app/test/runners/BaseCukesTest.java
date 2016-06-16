package com.ensono.app.test.runners;

import java.util.logging.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ensono.app.utils.ConfigUtil;
import com.ensono.app.utils.DriverManager;

public class BaseCukesTest {

	protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
	private ConfigUtil configUtil = new ConfigUtil();
	
    @BeforeClass(alwaysRun = true)
    @Parameters({ "host-url", "browser-name" })
    public void beforeClass(final String hostURL, final String browserName) throws Exception {
    	LOGGER.info(hostURL + "  " + browserName);
    	
    	configUtil.setConfigBean(hostURL, browserName);
    	DriverManager.getDriver(browserName);	
    }	
    
    @AfterClass(alwaysRun = true)    
    public void afterClass() throws Exception {  
    	LOGGER.info("Closing the browser");
    	DriverManager.close();	
    }	
    
}
