package com.selenium.basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTestCase {
	@Test
	public void verifyFacebookHomepage() {
		String URL="https://www.facebook.com/";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sonir\\eclipse-workspace\\SeleniumProject\\drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get(URL);
		
		String pageTItle= driver.getTitle();
		Assert.assertEquals(pageTItle, "Facebook - log in or sign up");
		System.out.println("The title is" +pageTItle);
		
		driver.close();
		
		
	}

}
