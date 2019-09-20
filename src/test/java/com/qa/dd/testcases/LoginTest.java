package com.qa.dd.testcases;

import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.qa.dd.PageObjects.*;
import com.qa.dd.utilities.*;


public class LoginTest extends BaseTest {

	@Test(dataProviderClass=DataProviders.class,dataProvider="masterDP")
	public void loginTest(Hashtable<String,String> data) {
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginTest", data.get("Runmode"), excel);
		log.info("Inside Login Test");
		openBrowser(data.get("browser"));
		logInfo("Launched Browser : "+data.get("browser"));
		DDLoginPage home = new DDNavURL().open(testURL());
		DDHomePage login = home.gotoLogin(data.get("username"), data.get("password"));
		logInfo("Username entered as "+data.get("username")+" and Password entered as "+Base64.encodeBase64(data.get("password").getBytes()));
		DDMyProfile myP=login.navToMyProfile();
		Assert.assertEquals(myP.UName.getText(),data.get("username"),"Failing the verification test");
		DDLogOutPage loggOff=myP.clickLogOff();
		Assert.assertEquals(loggOff.logOffContibtn.isDisplayed(),true,"Failing the verification test");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		logInfo("Login Test Completed");
		quit();
	}
}
