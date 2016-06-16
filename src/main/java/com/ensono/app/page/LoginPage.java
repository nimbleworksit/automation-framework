package com.ensono.app.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ensono.app.bean.User;

public class LoginPage extends BasePage{		
	
	@FindBy(id = "login-container")
    private WebElement loginPageDiv;
		
	@FindBy(id = "login-form-username")
    private WebElement usernameInput;
		
	@FindBy(id = "login-form-password")
    private WebElement passwordInput;
	
	@FindBy(name = "login")
    private WebElement submitButton;	
	
	 /**
     * Constructor to create the LoginPage Instance
     * @param driver WebDriver
     */
    public LoginPage(final WebDriver  driver) {    	
        super(driver);        
        PageFactory.initElements(driver, this);
        LOGGER.info("Login Page has loaded");
    }

	public void isDisplayedCheck(){
		LOGGER.info("Checking login page is displayed");
		waitForElement(loginPageDiv);				
		loginPageDiv.isDisplayed();		
	}

	public void EnterUsername(User user){
		LOGGER.info("Logging in with username:" + user.getUsername());
		type(user.getUsername(), usernameInput);
	}

	public void EnterPassword(User user){
		LOGGER.info("Logging in with password:" + user.getPassword());
		type(user.getPassword(), passwordInput);
	}

	public HomePage LoginAsValidUser(User user){
		click(submitButton);		
		LOGGER.info("Login submitted");
		return new HomePage(driver);
	}
	
	public LoginPage LoginAsInvalidUser(User user){
		click(submitButton);		
		LOGGER.info("Login submitted");
		return this;
	}

	public void checkLoginErrors(){
		LOGGER.info("Check login errors displayed");
	//	waitForElement(usernameInput);
	//	usernameInput.isDisplayed();
	}	
}
