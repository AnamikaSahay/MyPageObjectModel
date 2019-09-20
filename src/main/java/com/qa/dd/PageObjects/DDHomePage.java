package com.qa.dd.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DDHomePage extends BasePage{

	@FindBy(linkText="My Profile")
	public WebElement myProfile;
	
	@FindBy(xpath="//div[@id='pt1:r1:0:pgl20']/div[1]//span[@id='pt1:r1:0:it7::content']")
	public WebElement UName;
	
	public DDMyProfile navToMyProfile() {
		click(myProfile, "My Profile");
		return (DDMyProfile) openPage(DDMyProfile.class);
	}
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.elementToBeClickable(By.linkText("My Profile"));
	}

}
