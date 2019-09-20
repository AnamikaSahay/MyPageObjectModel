package com.qa.dd.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.qa.dd.PageObjects.DDLoginPage;
import com.qa.dd.PageObjects.DDNavURL;
import com.qa.dd.PageObjects.DDSignIn;
import com.qa.dd.utilities.Constants;
import com.qa.dd.utilities.DataProviders;
import com.qa.dd.utilities.DataUtil;
import com.qa.dd.utilities.ExcelReader;

public class InvalidLoginTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void invalidLoginTest(Hashtable<String,String> data) {

		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "InvalidLoginTest", data.get("Runmode"), excel);
		log.info("Inside InvalidLogin Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		DDLoginPage home = new DDNavURL().open(testURL());
		DDSignIn loginerr = home.doLoginAsInvalidUser(data.get("username"), data.get("password"));
		logInfo("Username entered as "+data.get("username")+" and Password entered as "+data.get("password"));
		Assert.assertEquals(loginerr.errMsg.getText().toString().trim(),"Username or password entered is not valid.");
		logInfo("Invalid Username or password error is displayed" );
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		logInfo("InvalidLogin Test Completed");
		quit();
	}
}
