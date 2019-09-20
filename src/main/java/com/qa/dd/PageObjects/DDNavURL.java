package com.qa.dd.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.qa.dd.utilities.DriverManager;

public class DDNavURL extends BasePage {
	
	@FindBy(css="input#username")
	public WebElement UserName;
	
	public DDLoginPage open(String url) {
		DriverManager.getDriver().navigate().to(url);
		return (DDLoginPage) openPage(DDLoginPage.class);
	}
	
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		return null;
	}
	
	
	

}
