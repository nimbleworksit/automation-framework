package com.ensono.app.test.tests;

import com.ensono.app.bean.User;
import com.ensono.app.constants.Outcome;
import com.ensono.app.page.HomePage;
import com.ensono.app.page.LoginPage;
import com.ensono.app.utils.ConfigUtil;
import com.ensono.app.utils.DriverManager;

public class LoginTest{	
	private LoginPage loginPage = new LoginPage(DriverManager.getDriver()); 
	private HomePage homePage;
	private User userExp = new User();
	
	public void open_login_page(){	
		loginPage.loadPage(ConfigUtil.getInstance().getConfig().getURL());
		loginPage.isDisplayedCheck();
	}

	public void enter_username(String username) {
		userExp.setUserName(username);
		loginPage.EnterUsername(userExp);
	}

	public void enter_password(String password) {
		userExp.setPassword(password);
		loginPage.EnterPassword(userExp);
	}
	

	public void is_loggedin(String outcomeString){
		Outcome outcome = Outcome.outcomeForName(outcomeString);
		switch(outcome){
			case SUCCESS:		
				homePage = loginPage.LoginAsValidUser(userExp);
				homePage.isDisplayedCheck();
			break;
			case FAILURE:
				loginPage = loginPage.LoginAsInvalidUser(userExp);
				loginPage.checkLoginErrors();
			break;
		}
	}
	

}
