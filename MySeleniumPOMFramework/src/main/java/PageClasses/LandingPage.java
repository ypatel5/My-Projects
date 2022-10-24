package PageClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;


public class LandingPage extends PageBaseClass {
	
	public LandingPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	/*
	 * All WebElements of landing page and associated operations
	 */
	
	/*
	 * public void openBrowser() { System.setProperty("webdriver.chrome.driver",
	 * System.getProperty("user.dir")+"\\drivers\\chromedriver.exe"); WebDriver
	 * driver=new ChromeDriver(); }
	 */
	
	/*
	 * public void openWebsite() { driver.get("https://www.rediff.com/"); }
	 */
	public PortfolioLoginPage clickSignin() {
		//Perform the click
		PortfolioLoginPage portfoliologinpage=new PortfolioLoginPage(driver, logger);
		PageFactory.initElements(driver, portfoliologinpage);
		return portfoliologinpage;
	}
	
	@FindBy(xpath="//*[contains(@class,'moneyicon')]")
	public WebElement moneyLink;
	
	public MoneyPage clickmoneyLink() {
		logger.log(Status.INFO, "Clicking the money link");
		moneyLink.click();
		logger.log(Status.PASS, "Clicked the money link");
		MoneyPage moneypage=new MoneyPage(driver, logger);
		PageFactory.initElements(driver, moneypage);
		return moneypage;
	}
	
	//Open browser Code
	//WebElements
	//Operations on WebElements

	
}
