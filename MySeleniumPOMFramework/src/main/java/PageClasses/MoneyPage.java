package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MoneyPage extends PageBaseClass{
	public TopMenuClass topmenu;
	
	public  MoneyPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu=new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}
	@FindBy(xpath="//*[@id=\'signin_info\']/a[1]")
	public WebElement signinLink;
	
	public PortfolioLoginPage clicksigninLink() {
		logger.log(Status.INFO, "Clicking the sign in link");
		signinLink.click();
		logger.log(Status.PASS, "Clicked the Sign in link");
		PortfolioLoginPage portfolioLogin=new PortfolioLoginPage(driver, logger);
		PageFactory.initElements(driver, portfolioLogin);
		return portfolioLogin;
	}
}
