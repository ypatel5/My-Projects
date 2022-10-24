package baseClasses;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.LandingPage;

public class BaseTestClass {
	public WebDriver driver;
	public ExtentReports report = Utilities.ExtentReportManager.getReportInstance();
	public static ExtentTest logger;
	
	/****************** Invoke Browser ***********************/
	public void invokeBrowser(String browserName) {

		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else {
				driver = new SafariDriver();
			}
		} catch (Exception e) {
			// reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1500));
	}
	
	/***********************Wait for page load**************************/
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/********************* Open Application ************************/
	public LandingPage OpenApplication() {
		logger.log(Status.INFO, "Opening the website");
		driver.get("https://www.rediff.com/");
		logger.log(Status.PASS, "Successfully opened the website");
		LandingPage landingpage=new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingpage);
		return landingpage;
	}

	@AfterMethod
	public void flushReports() {
		report.flush();
		driver.close();
	}

	
}
