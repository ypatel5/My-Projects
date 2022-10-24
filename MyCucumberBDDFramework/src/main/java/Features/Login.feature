Feature: Login Application feature 

#Simple defination
#Scenario: User Login Scenario
#	Given User is on Application Home Page 
#	When  Application Page Title FREE CRM 
#	Then  user enters "yeshapatel.2081@gmail.com" and "Test@1234" 
#	And  user clicks on Login Button 
#	Then User navigate to user Profile page 
	
#Definition using example keyword	
Scenario Outline: User Login Scenario
	Given User is on Application Home Page 
	When  Application Page Title FREE CRM 
	Then  user enters "<username>" and "<password>" 
	And  user clicks on Login Button 
	Then User navigate to user Profile page 
	
	
Examples:
	| username | password |
	| yeshapatel.2081@gmail.com | Test@1234 | 
#can contain n number of rows(works like excel sheet)
	

	
