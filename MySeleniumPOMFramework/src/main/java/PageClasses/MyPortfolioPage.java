package PageClasses;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPortfolioPage extends PageBaseClass {
	public TopMenuClass topmenu;

	public MyPortfolioPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		topmenu = new TopMenuClass(driver, logger);
		PageFactory.initElements(driver, topmenu);
	}
	
	@FindBy(xpath="//*[@id='headcontent']/div[1]/div[1]/a/span")
	public WebElement moneyBiz_text;
	
	@FindBy(id="createPortfolio")
	public WebElement createPortfolio_Btn;
	
	@FindBy(id="create")
	public WebElement createportfolio_Textbox;
	
	@FindBy(id="createPortfolioButton")
	public WebElement submitCreatePortfolio_Btn;
	
	@FindBy(id="portfolioid")
	public WebElement myPortfolioList;
	
	@FindBy(id="deletePortfolio")
	public WebElement deletePortfolio_Btn;
	
	public MyPortfolioPage deletePortfolio() {
		try {
			deletePortfolio_Btn.click();
			acceptAlert();
			logger.log(Status.PASS, "Deleted the portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		MyPortfolioPage myportfolio=new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public MyPortfolioPage selectPortfolio(String Value){
		selectDropDownValue(myPortfolioList, Value);
		MyPortfolioPage myportfolio = new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public void isPorfolioExists(String portfolio){
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (option.getText().equalsIgnoreCase(portfolio)){
					flag=true;
				}else{
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , Present in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void isPorfolioDeleted(String portfolio){
		boolean flag = false;
		try {
			List<WebElement> allOptions = getAllElementsOfDropDown(myPortfolioList);
			for (WebElement option : allOptions) {
				if (!option.getText().equalsIgnoreCase(portfolio)){
					flag=true;
				}else{
					flag = false;
				}
			}
			Assert.assertTrue(flag);
			logger.log(Status.PASS, "Given Portfolio : " + portfolio + " , is not Prsent in Portfolio List");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public MyPortfolioPage submitPortfolio() {
		try {
			submitCreatePortfolio_Btn.click();
			logger.log(Status.PASS, "Submitted the portfolio");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		MyPortfolioPage myportfolio=new MyPortfolioPage(driver, logger);
		PageFactory.initElements(driver, myportfolio);
		return myportfolio;
	}
	
	public void enterPortfolioName(String portfolioName) {
		try {
			createportfolio_Textbox.clear();
			createportfolio_Textbox.sendKeys(portfolioName);
			logger.log(Status.PASS, "Entered the portfolio name: " +portfolioName);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void clickCreatePortfolio() {
		try {
			createPortfolio_Btn.click();
			logger.log(Status.PASS, "Clicked the create portfolio button");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void verifyMoneyBiz() {
		moneyBiz_text.isDisplayed();
	}

	public TopMenuClass gettopmenu() {
		return topmenu;
	}

}
