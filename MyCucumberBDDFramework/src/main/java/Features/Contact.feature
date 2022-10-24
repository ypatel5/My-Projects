Feature: Add new contact
Scenario Outline: User Login Scenario
	Given User is on Application Home Page 
	When  Application Page Title FREE CRM 
	Then  user enters "<username>" and "<password>" 
	And  user clicks on Login Button 
	When User navigate to user Profile page
	Then Go to contacts
	Then open contact form
	Then user puts details "<firstName>" and "<lastName>" and "<Companyname>" and "<position>"
	Then Save the contact
	Then close the browser
	
	Examples:
		| username | password | firstName | lastName | Companyname | position |
		| yeshapatel.2081@gmail.com | Test@1234 | Peter | Joseph | TD | Quality Engineer I |
		| yeshapatel.2081@gmail.com | Test@1234 | Rob | Stewart | Amazon | Tester |
		| yeshapatel.2081@gmail.com | Test@1234 | Maria | Philip | Infosys | Quality Assurance |		