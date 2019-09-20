package com.qa.dd.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DDSignIn extends BasePage {

	
	@FindBy(css="span.errormsg")
	public WebElement errMsg;
	
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(errMsg);
	}

}
