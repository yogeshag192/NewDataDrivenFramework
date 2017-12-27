package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.way2AutomationComponents.DragAndDropControls;

public class BaseTest{

	protected static WebDriver driver;
	protected Properties webElementProperties;
	protected Properties appProperties;
	protected Properties configProperties;
	
		// Runs before every test class

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(String browser) throws InterruptedException, IOException{
		try {
			LoadProperties();
			System.out.println("Loaded Properties files..");
		} catch (FileNotFoundException e) {
			System.out.println("Properties file was not found..");
		} catch (IOException e) {
			System.out.println("Properties file could not be opened..");
		}
		
		configProperties = PropertiesUtil.readInputArguments();
		System.out.println("Loaded config arguments..");
		String portal = configProperties.getProperty("portal");
		System.out.println("portal selected is : " +portal);
		
		setupSeleniumWebDriver(browser);
		
		LoginToApp login = new LoginToApp(driver);
		login.loginToApplication(portal);
		
	}
		
	public WebDriver setupSeleniumWebDriver(String browser) {
			try {
				if (browser.equals("Firefox")) {
					System.out.println("Setting up FireFox Driver.");
					
					String geckoDriverPath = System.getProperty("user.dir") +"\\Files\\geckodriver.exe";
					System.out.println(geckoDriverPath);
					System.setProperty("webdriver.gecko.driver", geckoDriverPath); 
					driver = new FirefoxDriver();

					/*FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.download.dir", Constants.DOWNLOAD_DIRECTORY);
					profile.setPreference("browser.download.useDownloadDir", true);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		
					DesiredCapabilities caps=DesiredCapabilities.firefox();
					caps.setCapability(FirefoxDriver.PROFILE, profile);
					driver =  new FirefoxDriver(caps);*/
					
				} else if (browser.equals("IE")) {
					System.out.println("Setting up Internet Explorer Driver.");
					System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_PATH);
					driver = new InternetExplorerDriver();
					
				} else if (browser.equals("Chrome")) {
					System.out.println("Setting up Chrome Driver.");
					System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
					driver = new ChromeDriver();
					driver.manage().window().maximize();
				} 
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			} catch (WebDriverException e) {
				System.out.println("could not setup " + browser + " driver : " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Exception occured during driver setup : " + e.getMessage());
			}
			return driver;
		}

	
	@AfterClass
	public void tearDown(){
		driver.close();
	}
	
	private void LoadProperties() throws IOException {
		appProperties = PropertiesUtil.getAppProperties();
		webElementProperties = PropertiesUtil.getWebElementProperties();
	}
	
	public String getElementValue(String propertyName) throws IOException {
		return webElementProperties.getProperty(propertyName);
	}
	
	public String getInputValue(String propertyName) throws IOException {
		return appProperties.getProperty(propertyName);
		
	}
	
	}
