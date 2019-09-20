package com.qa.dd.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DDLogOutPage extends BasePage{

	@FindBy(xpath="//input[@value='Continue']")
	public WebElement logOffContibtn;
	
	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {
		//return ExpectedConditions.visibilityOf(logOffContibtn);
		return ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Continue']"));
	}
	//html/body/center/div/div[2]/table/tbody/tr[2]/td[2]/form/input
}
