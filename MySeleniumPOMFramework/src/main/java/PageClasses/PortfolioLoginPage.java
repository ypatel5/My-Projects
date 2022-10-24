package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class PortfolioLoginPage extends PageBaseClass{
	public TopMenuClass topmenu;
	
	public PortfolioLoginPage (WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu=new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}
	@FindBy(id="useremail")
	public WebElement useremail_Textbox;
	
	@FindBy(id="userpass")
	public WebElement password_Textbox;
		
	@FindBy(id="loginsubmit")
	public WebElement submitlogin_button;
	
	public MyPortfolioPage doLogin(String userName, String password) {
		useremail_Textbox.sendKeys(userName);
		logger.log(Status.PASS, "Entered the username" +userName);
		password_Textbox.sendKeys(password);
		logger.log(Status.PASS, "Entered the password" +password);
		submitlogin_button.click();
		logger.log(Status.PASS, "Clicked the submit login button");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String currentpageTitle=driver.getTitle();
		if(currentpageTitle.equals("Indian stock markets: Login to Portfolio")) {
			reportFail("Login is failed on portfolio login page");
			Assert.fail("Login Failed");
		}
		MyPortfolioPage myportfolioPage=new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolioPage);
		return myportfolioPage;
	}
}
