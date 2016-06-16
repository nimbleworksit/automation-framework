package com.ensono.app.test.runners;

import org.testng.IExecutionListener;

import com.ensono.app.utils.GenerateReport;

import java.util.logging.Logger;

public class TestNGExecutionListener implements IExecutionListener {
	
	private static final Logger LOGGER = Logger.getLogger(TestNGExecutionListener.class.getName());
	
  //  @Override
    public void onExecutionStart() {        
        LOGGER.info("TestNG is staring the execution");       
    }
   // @Override
    public void onExecutionFinish() {
    	LOGGER.info("Generating the Report");
        GenerateReport.GenerateMasterthoughtReport();
        LOGGER.info("TestNG has finished, the execution");
    }
}
