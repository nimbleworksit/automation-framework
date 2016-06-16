@login @all @user
Feature: Login
  As a website user
  In order to access the website content
  I need to log in to the website

Background: 


Scenario Outline: invalid credentials
   Given I am on the login page
   When I provide the username '<username>'
   And I provide the password '<password>'
   Then I should not be logged in
   
Examples: 
	| username		| password	     	| 
	| sivs   			| P@ssw0r       	| 
	| sivsu  			| P@ssw0rd        | 
	


@smoke	
Scenario: valid credentials
   Given I am on the login page
   When I provide the username 'sivsur'
   And I provide the password 'P@ssw0rd'
   Then I should be successfully logged in

	