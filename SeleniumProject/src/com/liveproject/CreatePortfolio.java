package com.liveproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatePortfolio {
	WebDriver driver=null;
	@BeforeMethod
	public void openBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sonir\\eclipse-workspace\\SeleniumProject\\drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		//Apply implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.quit();
	}
	
	@Test
	public void testCreatePortfolio() {
		driver.get("https://www.rediff.com/");
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\'signin_info\']/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\'useremail\']")).sendKeys("anshulc55@rediffmail.com");
		driver.findElement(By.xpath("//*[@id=\'userpass\']")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//*[@id=\'loginsubmit\']")).sendKeys(Keys.ENTER); 
		driver.findElement(By.id("createPortfolio")).click();
		driver.findElement(By.id("create")).clear();
		driver.findElement(By.id("create")).sendKeys("My Test Portfolio");
		driver.findElement(By.id("createPortfolioButton")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\'username\']/a"))));
		driver.findElement(By.xpath("//*[@id=\'username\']/a")).isDisplayed();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
