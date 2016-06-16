package com.ensono.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;

public class GenerateReport {
    public static void GenerateMasterthoughtReport(){
        try{
            String RootDir = System.getProperty("user.dir");
            File reportOutputDirectory = new File("target/cucumber-reports");
            List<String> list = new ArrayList<String>();
            list.add("target/firefox.json");
       //    list.add("target/chrome.json");
       //     list.add("target/ie.json");

            String pluginUrlPath = "";
            String buildNumber = "1";
            String buildProject = "cucumber-jvm";
            boolean skippedFails = true;
            boolean pendingFails = true;
            boolean undefinedFails = true;
            boolean missingFails = true;
            boolean flashCharts = true;
            boolean runWithJenkins = false;
            boolean highCharts = false;
            boolean parallelTesting = true;
            boolean artifactsEnabled = false;
            String artifactConfig = "";

            if (!reportOutputDirectory.exists()) {
            	 /*
            	 * mkdirs() method creates the directory mentioned by this abstract
            	 * pathname including any necessary but nonexistent parent
            	 * directories.
            	 * 
            	 * Accordingly it will return TRUE or FALSE if directory created
            	 * successfully or not. If this operation fails it may have
            	 * succeeded in creating some of the necessary parent directories.
            	 */
            	  reportOutputDirectory.mkdirs();
            	 }
            
            ReportBuilder reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
                    buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins,
                    highCharts, parallelTesting);

            reportBuilder.generateReports();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
