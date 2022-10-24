package baseClasses;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.DateUtil;

public class PageBaseClass extends BaseTestClass {
	
	public ExtentTest logger;
	public PageBaseClass(WebDriver driver,ExtentTest logger) {
		this.driver=driver;
		this.logger=logger;
	}
	
	
	
	/********************* Get page title************************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual title"+driver.getTitle()+"matches expected title"+expectedTitle);
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		
	}
	
	/********************* Accept alert function************************/
	public void acceptAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
			logger.log(Status.PASS, "Page alert accepted");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/********************* Cancel alert function************************/
	public void cancelAlert() {
		try {
			Alert alert=driver.switchTo().alert();
			alert.dismiss();
			logger.log(Status.PASS, "Page alert Rejected");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value){
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selected the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/********************* Get all elements of dropdown  ************************/
	public List getAllElementsOfDropDown(WebElement webElement){
		try {
			Select select = new Select(webElement);
			List<WebElement> allElements = select.getOptions();
			return allElements;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		 return null;
	}
	
	/********************* Reporting Functions ************************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenshotonFailure();
		Assert.fail(reportString);

	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
	
	public void takeScreenshotonFailure() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\Screenshots" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\Screenshots" + DateUtil.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}