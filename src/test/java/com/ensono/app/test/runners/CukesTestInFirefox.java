package com.ensono.app.test.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;

@CucumberOptions(
        features = {"src/test/resources/features"},//path to the features   		
        plugin = {"pretty", "json:target/firefox.json"},//what formatters to use
        glue = {"com.ensono.app.test.stepDefinitions"}
  //      tags = {Config.tag}
        )//what tags to incluse(@)/exclude(@~)

public class CukesTestInFirefox extends BaseCukesTest{	      

    /**
     * Create one test method that will be invoked by TestNG and invoke the
     * Cucumber runner within that method.
     */
        
    @Test(groups = "cucumber", description = "Using TestNGCucumberRunner to invoke Cucumber")
    public void runCukes() {    	
    	LOGGER.info("Starting Cuke Test in Firefox...");
        new TestNGCucumberRunner(getClass()).runCukes();
    }



    
}
