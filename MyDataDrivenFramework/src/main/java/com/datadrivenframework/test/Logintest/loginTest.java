package com.datadrivenframework.test.Logintest;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.datadrivenframework.base.BaseUI;
import com.datadrivenframework.utils.TestDataProvider;


public class loginTest extends BaseUI {
	
	
	@Test(dataProvider = "getTestOneData")
	public void TestOne(Hashtable<String, String> dataTable) {
		
		logger= report.createTest("Test One");
		invokeBrowser("Chrome");
		openURL("websiteURL");
		clickElement("signinBtn_Xpath");
		enterText("usernameTextbox_Id", dataTable.get("Col1"));
		enterText("passwordTextbox_Xpath", dataTable.get("Col3"));
		tearDown();
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}
	
	@DataProvider
	public Object[][] getTestOneData(){
		return TestDataProvider.getTestData("TestData_Testmanagement.xlsx","Feature 1","Test 1");
	}
	//@Test(dependsOnMethods = "TestOne")
	public void TestTwo() {
		invokeBrowser("Chrome");
		openURL("websiteURL");
		clickElement("signinBtn_xpath");
		enterText("usernameTextbox_xpath", "anshulc55");
		tearDown();
	}
	
	//@Test
	public void TestThree() {
		invokeBrowser("Chrome");
		openURL("websiteURL");
		clickElement("signinBtn_xpath");
		enterText("usernameTextbox_xpath", "anshulc55");
		tearDown();
	}
}
