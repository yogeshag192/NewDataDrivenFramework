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

public class BaseTest extends Base{
	protected WebDriver driver;
	protected Properties webElementProperties;
	protected Properties appProperties;
	protected Properties configProperties;


	//@BeforeClass(alwaysRun = true)
	//@Parameters({ "browser" })
	@Test
	public void setUp(String browser) throws InterruptedException, IOException{
		try {
			LoadProperties();
		} catch (FileNotFoundException e) {
			System.out.println("Properties file was not found..");
		} catch (IOException e) {
			System.out.println("Properties file could not be opened..");
		}
		
		configProperties = PropertiesUtil.readInputArguments();
		String portal = configProperties.getProperty("portal");
		
		setupSeleniumWebDriver("Chrome");
		
		loginToApplication(portal);
		
	}
		
		
		
	public WebDriver setupSeleniumWebDriver(String browser) {
			try {
				if (browser.equals("Firefox")) {
					System.out.println("Setting up FireFox Driver.");

					System.setProperty("webdriver.firefox.bin", "D:\\Users\\agrawaly\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
					System.setProperty("webdriver.gecko.driver", "D:\\Users\\agrawaly\\Downloads\\geckodriver\\geckodriver.exe"); 
					
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.download.dir", Constants.DOWNLOAD_DIRECTORY);
					profile.setPreference("browser.download.useDownloadDir", true);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		
					DesiredCapabilities caps=DesiredCapabilities.firefox();
					caps.setCapability(FirefoxDriver.PROFILE, profile);
					driver =  new FirefoxDriver(caps);
					
				} else if (browser.equals("IE")) {
					System.out.println("Setting up Internet Explorer Driver.");
					System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER_PATH);
					driver = new InternetExplorerDriver();
					
				} else if (browser.equals("Chrome")) {
					System.out.println("Setting up Chrome Driver.");
					System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
					driver = new ChromeDriver();
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

	
	
	
	//@AfterClass
	public void tearDown(){
		driver.close();
	}
	
	private void LoadProperties() throws IOException {
		appProperties = PropertiesUtil.getAppProperties();
		webElementProperties = PropertiesUtil.getWebElementProperties();
	}
	
	public String getElementValue(String propertyName) throws IOException {
		return appProperties.getProperty(propertyName);
	}
	
	public String getInputValue(String propertyName) throws IOException {
		return appProperties.getProperty(propertyName);
		
	}
	
	
	public void loginToApplication(String portal) {
    	
    	String appUrl, userName ,password;
       	    	
        switch (portal) {
        case "CIL":
         
            break;
            
        case "BLOCKCHAIN":
            
            break; 
            
        case "WAY2A":
        	appUrl = configProperties.getProperty("URL");
        	userName = appProperties.getProperty("way2UserName");
        	password = appProperties.getProperty("way2Password");
        	driver.get(appUrl);
            waitForPageToLoad(driver);
            driver.manage().window().maximize();
        
            
        default:
            System.out.println("Something is wrong!!!!! Should not be in DEFAULT");
        }

    }
	}
