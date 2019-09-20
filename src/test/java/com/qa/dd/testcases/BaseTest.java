package com.qa.dd.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.qa.dd.ExtentListeners.ExtentListeners;
import com.qa.dd.utilities.DriverFactory;
import com.qa.dd.utilities.DriverManager;

public class BaseTest {

	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log=LogManager.getLogger(BaseTest.class);
	public boolean grid=false;
//	private String defaultUserName;
//	private String defaultPassword;
//	
//	public String getDefaultUserNam`e() {
//		return defaultUserName;
//	}
//	
//	public void setDefaultUserName(String defaultUserName) {
//		this.defaultUserName = defaultUserName;
//	}
//
//	public String getDefaultPassword() {
//		return defaultPassword;
//	}
//
//	public void setDefaultPassword(String defaultPassword) {
//		this.defaultPassword = defaultPassword;
//	}

	@BeforeSuite
	public void setUpFramework() {

		//configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
	
        if (System.getProperty("os.name").equalsIgnoreCase("mac")) {
        	DriverFactory.setChromeDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
    		DriverFactory.setGeckoDriverExePath(
    				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");
        }else {		
		DriverFactory.setChromeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		DriverFactory.setGeckoDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		DriverFactory.setIeDriverExePath(
				System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
        }
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);

			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logInfo(String message) {		
		ExtentListeners.testReport.get().info(message);
	}
	
	public String testURL() {
		return Config.getProperty("testurl");
	}
//	public void configureLogging() {
//		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
//				+ "log4j.properties";
//	}

//	public void destroyFramework() {
//
//	}

	public void openBrowser(String browser) {		
		if(System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equals("Grid")) {
			grid=true;
		}

		DriverFactory.setRemote(grid);
		if (DriverFactory.isRemote()) {
			Capabilities options = null;
			if (browser.equalsIgnoreCase("firefox")) {
				options = new FirefoxOptions();
				
				ProfilesIni listprofiles = new ProfilesIni();				
				//create profile using firefox.exe -p
				  //FirefoxProfile profile = listprofiles.getProfile("default");		
				  FirefoxProfile profile = listprofiles.getProfile("Selenium");	
				  //profile.addExtension("");
				  profile.setAcceptUntrustedCertificates(true);	
				  profile.setAssumeUntrustedCertificateIssuer(false);
			    ((FirefoxOptions) options).setCapability("firefox_profile", profile);
			    ((FirefoxOptions) options).addArguments("--disable-extensions");
			    //((FirefoxOptions) options).addArguments("window-size=800,480");
		        ((FirefoxOptions) options).addArguments("--disable-notifications");
		        ((FirefoxOptions) options).addArguments("--disable-infobars");
		        ((FirefoxOptions) options).addArguments("--ignore-certificate-errors");
		        ((FirefoxOptions) options).addArguments("--disable-popup-blocking");
		        ((FirefoxOptions) options).setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		        ((FirefoxOptions) options).setCapability("platform","ANY");
		        ((FirefoxOptions) options).setCapability("version","real");
		        
			} else if (browser.equalsIgnoreCase("chrome")) {
				options = new ChromeOptions();
		        ((ChromeOptions) options).addArguments("--ignore-certificate-errors");
		        ((ChromeOptions) options).addArguments("--disable-popup-blocking");
		        ((ChromeOptions) options).setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		        ((ChromeOptions) options).setCapability("platform","ANY");
		        ((ChromeOptions) options).setCapability("version","real");

			} else if (browser.equalsIgnoreCase("ie")) {
				options = new InternetExplorerOptions();
		        ((InternetExplorerOptions) options).setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		        ((InternetExplorerOptions) options).setCapability("platform","ANY");
		        //((InternetExplorerOptions) options).setCapability("version","real");
		        ((InternetExplorerOptions) options).setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		        ((InternetExplorerOptions) options).setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
}
			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), options);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver",
				DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver",
					DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.out.println("Launching : " + browser);
//			WebDriverManager.iedriver().setup();
//			FirefoxDriver driverF = new FirefoxDriver();
			
			System.setProperty("webdriver.ie.driver",
					DriverFactory.getIeDriverExePath());
			driver = new InternetExplorerDriver();
		}
//		EventFiringWebDriver webDriver= new EventFiringWebDriver(driver);
//		webDriver.register(eventListener);
		DriverManager.setWebDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		setDefaultUserName(Config.getProperty("defaultUserName"));
//		setDefaultPassword(Config.getProperty("defaultPassword"));

	}

	public void quit() {
		DriverManager.getDriver().quit();
		logInfo("Test Execution Completed !!!");
	}
}
