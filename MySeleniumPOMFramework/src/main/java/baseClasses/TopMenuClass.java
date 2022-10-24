package baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LogOutPage;

public class TopMenuClass extends PageBaseClass{
	
	public TopMenuClass(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		
	}
	
	@FindBy(xpath="//*[@id=\'headcontent\']/div[1]/ul/li[2]/a")
	public WebElement myPortfolioLink;
	
	@FindBy(xpath="//*[@id=\'signin_info\']/a")
	public WebElement signoutLink;
	
	public LogOutPage signoutApplication() {
		logger.log(Status.INFO, "Clicking the signout link");
		signoutLink.click();
		logger.log(Status.PASS, "Clicked the signout link");
		LogOutPage logoutPage=new LogOutPage(driver, logger);
		PageFactory.initElements(driver, logoutPage);
		return logoutPage;
	}

}
