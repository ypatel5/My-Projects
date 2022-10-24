package TestCases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import PageClasses.LandingPage;
import PageClasses.MoneyPage;
import PageClasses.MyPortfolioPage;
import PageClasses.PortfolioLoginPage;
import Utilities.ConstantValue;
import Utilities.TestDataProvider;
import baseClasses.BaseTestClass;
import baseClasses.PageBaseClass;
import baseClasses.TopMenuClass;

public class MyPortfolioTest extends BaseTestClass {
	
	
	LandingPage landingpage;
	MoneyPage moneypage;
	PortfolioLoginPage portfoliologinpage;
	MyPortfolioPage myportfoliopage;
	TopMenuClass topMenu;

	//@Test(dataProvider = "getOpenPortfolioTestData", groups = {"Regression","Login Test"})
	public void openPortfolio(Hashtable<String, String> testData) {
		logger=report.createTest("Login Rediff Portfolio" +testData.get("Comment"));
		
		invokeBrowser("Chrome");
		landingpage=OpenApplication();
		moneypage = landingpage.clickmoneyLink();
		portfoliologinpage = moneypage.clicksigninLink();
		myportfoliopage = portfoliologinpage.doLogin(testData.get("UserName"), testData.get("Password"));
		myportfoliopage.verifyMoneyBiz();
		myportfoliopage.getTitle(testData.get("PageTitle"));
		topMenu=myportfoliopage.gettopmenu();
		topMenu.signoutApplication();

	}
	
	//@Test(dataProvider = "addPortfolioTestData", groups = {"Regression","AddPortfolio"})
	public void addPortfolioTest(Hashtable<String, String> testData) {
		logger = report.createTest("Create Porfolio Test : " + testData.get("Comment"));
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingpage = pageBase.OpenApplication();
		moneypage =landingpage.clickmoneyLink();
		portfoliologinpage = moneypage.clicksigninLink();
		myportfoliopage = portfoliologinpage.doLogin(ConstantValue.userName, ConstantValue.password);
		myportfoliopage.verifyMoneyBiz();
		myportfoliopage.clickCreatePortfolio();
		waitForPageLoad();
		myportfoliopage.enterPortfolioName(testData.get("PortfolioName"));
		myportfoliopage = myportfoliopage.submitPortfolio();
		waitForPageLoad();
		myportfoliopage.isPorfolioExists(testData.get("PortfolioName"));
	}
	
	@Test(dataProvider = "addPortfolioTestData", groups = {"Regression","DeletePortfolio"})
	public void deletePortfolio(Hashtable<String, String> testData) {
		logger = report.createTest("Create Porfolio Test : " + testData.get("Comment"));
		invokeBrowser("chrome");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		PageFactory.initElements(driver, pageBase);
		landingpage = pageBase.OpenApplication();
		moneypage =landingpage.clickmoneyLink();
		portfoliologinpage = moneypage.clicksigninLink();
		myportfoliopage = portfoliologinpage.doLogin(ConstantValue.userName, ConstantValue.password);
		waitForPageLoad();
		myportfoliopage.verifyMoneyBiz();
		myportfoliopage=myportfoliopage.selectPortfolio(testData.get("PortfolioName"));
		myportfoliopage=myportfoliopage.deletePortfolio();
		waitForPageLoad();
		myportfoliopage.isPorfolioDeleted(testData.get("PortfolioName"));
	}
	
	@DataProvider
	public Object[][] getOpenPortfolioTestData(){
		return TestDataProvider.getTestData("RediffPortfolioLaunch.xlsx", "RediffLoginTest", "openPortfolio");
	}
	
	@DataProvider
	public Object[][] addPortfolioTestData(){
		return TestDataProvider.getTestData("RediffPortfolioLaunch.xlsx", "RediffLoginTest", "addPortfolioTest");
	}

}
