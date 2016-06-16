package com.ensono.app.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{	
    
	@FindBy(id = "admin_menu")
    private WebElement homePageTitle;
	
	@FindBy(id = "log_out")
    private WebElement logout;

	 /**
     * Constructor to create the HomePage Instance
     * @param driver WebDriver
     */
    public HomePage(final WebDriver  driver) {    	
        super(driver);        
        PageFactory.initElements(driver, this);
        LOGGER.info("Home Page has loaded");
    }
    
	public void isDisplayedCheck(){
		LOGGER.info("Checking home page is displayed");
		waitForElement(homePageTitle);		
		homePageTitle.isDisplayed();
	}
	
	public void logout(){
		LOGGER.info("Logging out");	
		logout.click();
		LOGGER.info("Logout clicked");
	}
}

