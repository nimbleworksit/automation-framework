package com.ensono.app.bean;

public class Config {
    private String hostURL; 
    private String browserName; 
	
    public Config(String hostURL, String browserName) {
		super();
		this.hostURL = hostURL;
		this.browserName = browserName;
	}
	
    public String getURL() {
    	return hostURL;
    }
    
    public void setURL(final String hostURL) {
    	this.hostURL = hostURL;
    }
    
    public String getBrowser() {
    	return browserName;
    }
    
    public void setBrowser(final String browserName) {
    	this.browserName = browserName;
    }
         
}