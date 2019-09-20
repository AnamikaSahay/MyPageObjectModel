package com.qa.dd.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DDLoginPage extends BasePage {

	@FindBy(id="loginButton")
	public WebElement logIn;
	
	@FindBy(id="username")
	public WebElement UserName;
	
	@FindBy(xpath="//input[@name='PASSWORD']")
	public WebElement PassWord;
	
	@FindBy(linkText="My Profile")
	public WebElement myProfile;
		
	public DDHomePage gotoLogin(String Id, String Pwd){
		type(UserName, Id, "Username");
		type(PassWord, Pwd, "Password");
		click(logIn, "Login Link");
		return (DDHomePage) openPage(DDHomePage.class);
	}
	
	public DDSignIn doLoginAsInvalidUser(String Id, String Pwd) {
			type(UserName, Id, "Username");
			type(PassWord, Pwd, "Password");
			click(logIn, "Login Link");
			return (DDSignIn) openPage(DDSignIn.class);
	}
	
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		return ExpectedConditions.elementToBeClickable(logIn);
	}
}
