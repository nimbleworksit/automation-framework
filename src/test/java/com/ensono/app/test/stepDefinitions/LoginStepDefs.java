package com.ensono.app.test.stepDefinitions;

import java.util.logging.Logger;

import com.ensono.app.test.tests.LoginTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefs {

	private static final Logger LOGGER = Logger.getLogger(LoginStepDefs.class.getName());
	private LoginTest loginTest = new LoginTest();
		
	@Given("^I am on the login page$")
	public void user_is_on_login_page(){
		LOGGER.info("Entering: Loading Login Page");		
		loginTest.open_login_page();
	}
	
	@When("^I provide the username '(.+)'$")
	public void user_enters_username(String username){
		LOGGER.info("Entering: I try to login with username:" + username);
		loginTest.enter_username(username);		
	}
	
	@And("^I provide the password '(.+)'$")
	public void user_enters_password(String password){
		LOGGER.info("Entering: I try to login with password:" + password);
		loginTest.enter_password(password);		
	}
	
	@Then("^I should be successfully logged in$")	
	public void user_successful_login(){
		LOGGER.info("Entering: I should see that I logged in");
		loginTest.is_loggedin("successfully");		
	}

	@Then("^I should not be logged in$")	
	public void user_unsuccessful_login(){
		LOGGER.info("Entering: I should see that I am not logged in ");
		loginTest.is_loggedin("unsuccessfully");		
	}	
}
