package com.datadrivenframework.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.datadrivenframework.utils.DateUtils;
import com.datadrivenframework.utils.ExtentReportManager;

public class BaseUI {
	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	SoftAssert softassert = new SoftAssert();

	/********************* Invoke browser window ************************/
	public void invokeBrowser(String browserName) {

		try {
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else {
				driver = new SafariDriver();
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1500));

		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\ObjectRepository\\ProjectConfig.properties");
				prop.load(file);
			} catch (Exception e) {
				reportFail(e.getMessage());
				e.printStackTrace();
			}

		}
	}

	/********************* Open the URL ************************/
	public void openURL(String websiteURLKey) {
		try {
			driver.get(prop.getProperty(websiteURLKey));
			reportPass(websiteURLKey + "identified succesfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/********************* Close the browser ************************/
	public void tearDown() {
		driver.close();
	}

	/********************* Quit the browser ************************/
	public void quitBrowser() {
		driver.quit();
	}

	/********************* Enter data ************************/
	public void enterText(String xpathKey, String data) {
		try {
			getElement(xpathKey).sendKeys(data);
			reportPass(data + "entered successfully in" + xpathKey);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/********************* Click element ************************/
	public void clickElement(String xpathKey) {
		try {
			getElement(xpathKey).click();
			reportPass(xpathKey + "element clicked successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/********************* Getting the element ************************/
	public WebElement getElement(String locatorKey) {
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else if (locatorKey.endsWith("_Css")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator identified " + locatorKey);
			} else {
				reportFail("Failing the test case, Invalid locator" + locatorKey);
			}
		} catch (Exception e) {
			// Fail test case and report error
			reportFail(e.getMessage());
			e.printStackTrace();

			Assert.fail("Failing the test case" + e.getMessage());
		}
		return element;

	}

	public boolean iselementPresent(String locatorKey) {
		try {
			if (getElement(locatorKey).isDisplayed()) {
				reportPass(locatorKey + "Element is displayed");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean iselementSelected(String locatorKey) {
		try {
			if (getElement(locatorKey).isSelected()) {
				reportPass(locatorKey + "Element is Selected");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public boolean iselementEnabled(String locatorKey) {
		try {
			if (getElement(locatorKey).isEnabled()) {
				reportPass(locatorKey + "Element is Enabled");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	public void verifyPageTitle(String pageTitle) {
		try {
			String actualTitle=driver.getTitle();
			logger.log(Status.INFO, "Actual Title is:" +actualTitle);
			logger.log(Status.INFO, "Expected Title is:" +pageTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/********************* Assertion Functions ************************/
	public void asserttrue(boolean flag) {
		softassert.assertTrue(flag);
	}

	public void assertfalse(boolean flag) {
		softassert.assertFalse(flag);
	}

	public void assertequals(String actual, String expected) {
		softassert.assertEquals(actual, expected);
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

	@AfterMethod
	public void afterTest() {
		softassert.assertAll();
	}

	/********************* Capture Screenshot ************************/
	public void takeScreenshotonFailure() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\Screenshots" + DateUtils.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\Screenshots" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
