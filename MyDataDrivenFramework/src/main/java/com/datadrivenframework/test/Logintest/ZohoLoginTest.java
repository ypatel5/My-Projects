package com.datadrivenframework.test.Logintest;

import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadrivenframework.base.BaseUI;
import com.datadrivenframework.utils.TestDataProvider;

public class ZohoLoginTest extends BaseUI {

	@Test(dataProvider = "getDatadoZohoLoginTest")
	public void doZohoLoginTest(Hashtable<String, String> dataTable) {

		logger = report.createTest("Zoho Login Test Case : " + dataTable.get("Comment"));
		invokeBrowser("Chrome");
		openURL("websiteURL");
		clickElement("zohoLoginTextBox_ClassName");
		enterText("zohoUserNameTextBox_Id", dataTable.get("UserName"));
		enterText("zhPasswordTB_Id", dataTable.get("Password"));
		clickElement("zhSignBtn_Id");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		verifyPageTitle(dataTable.get("PageTitle"));

	}

	@DataProvider
	public Object[][] getDatadoZohoLoginTest() {
		return TestDataProvider.getTestData("ZohoTestData.xlsx", "LoginTest", "doZohoLoginTest");
	}

	@AfterTest
	public void endReport() {
		report.flush();
	}

}
