package com.qa.dd.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DDMyProfile extends BasePage{
	
	@FindBy(linkText="Logout")
	public WebElement logOff;
	
	@FindBy(xpath="//div[@id='pt1:r1:0:pgl20']/div[1]//span[@id='pt1:r1:0:it7::content']")
	public WebElement UName;

	public DDLogOutPage clickLogOff() {
		click(logOff, "LogOff Link");
		return (DDLogOutPage) openPage(DDLogOutPage.class);
	}
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(UName);
	}
}
