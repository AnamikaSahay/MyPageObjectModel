package com.qa.dd.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.dd.ExtentListeners.ExtentListeners;
import com.qa.dd.utilities.DriverManager;

public abstract class BasePage<T extends BasePage<?>> {
	protected WebDriver driver;
	  private long LOAD_TIMEOUT = 40;
	    public BasePage() {
	        this.driver = DriverManager.getDriver();
	    }

	    public T openPage(Class<T> retPageClass) {
	        T page = null;
	        try {
	            driver = DriverManager.getDriver();
	            page = PageFactory.initElements(driver, retPageClass);
	            ExpectedCondition<?> pageLoadCondition = ((BasePage<?>) page).getPageLoadCondition();
	            waitForPageToLoad(pageLoadCondition);
	        } catch (NoSuchElementException e) {
	        /*    String error_screenshot = System.getProperty("user.dir") + "\\target\\screenshots\\" + clazz.getSimpleName() + "_error.png";
	            this.takeScreenShot(error_screenshot);
	     */       throw new IllegalStateException(String.format("This is not the %s page", retPageClass.getSimpleName()));
	        }
	        return page;
	    }

	    private void waitForPageToLoad(ExpectedCondition<?> pageLoadCondition) {
	    	WebDriverWait wait = new WebDriverWait(driver,LOAD_TIMEOUT);
	        wait.until(pageLoadCondition);
	    }

	    protected abstract ExpectedCondition<?> getPageLoadCondition();
		
		public void click(WebElement element, String elementName) {
			element.click();
			ExtentListeners.testReport.get().info("Clicking on : "+elementName);		
		}
		public void type(WebElement element, String value, String elementName) {
			element.sendKeys(value);
			ExtentListeners.testReport.get().info("Typing in : "+elementName+" entered the value as : "+value);
		}
}
