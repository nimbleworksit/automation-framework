package com.ensono.app.bean;

public class User {
	private String username;
	private String password;
	

	public void setUserName(String username){
		this.username = username;
	//	return this;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password = password;
	//	return this;
	}
	
	public String getPassword(){
		return password;
	}
	
}
