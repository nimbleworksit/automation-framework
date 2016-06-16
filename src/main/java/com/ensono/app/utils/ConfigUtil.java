package com.ensono.app.utils;

import com.ensono.app.bean.Config;

public class ConfigUtil {

    private static ConfigUtil configUtil = null;
    private static Config config = null;
    
    public Config getConfig() {
//   	if (config == null) {
        //	if(runningMode == null || runningMode.equals("") ){
        //		configurationDetailsUtilForDataSource();
       //     config = setConfig("", "");
 //       }    	
        return config;
    }

    public void setConfigBean(String url,String browser){    	
    	ConfigUtil.config = new Config(url, browser);
    }
 

    /**
     * Action Method to create the Instance for the ConfigurationDetailsUtil
     * @return ConfigurationDetailsUtil instance
     */
    public static ConfigUtil getInstance() {
        if (configUtil == null) {
            return configUtil = new ConfigUtil();
        } else {
            return configUtil;
        }

    }
}

   