package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.way2AutomationComponents.DragAndDropControls;

public class BaseTest{

	protected static WebDriver driver;
	protected Properties webElementProperties;
	protected Properties appProperties;
	protected Properties configProperties;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void beforeSuiteSetup(){
		System.out.println("Setting up extent report config..");
		extent = new ExtentReports(System.getProperty("user.dir")+ "/extent-output/ExtentExecutionReport.html",true);
		extent.addSystemInfo("HostName", "Yogesh")
		.addSystemInfo("Environment", "QA")
		.addSystemInfo("User Name", " Yogesh Agrawal");
		extent.loadConfig(new File(System.getProperty("user.dir")+ "/extent-config.xml"));
	}
	
	@BeforeMethod
	public void beforeMethodSetup(Method method){
		System.out.println("In BeforeMethodSetup Method..");
		test = extent.startTest(("Class: " +this.getClass().getSimpleName() + " :: " +method.getName()), method.getName());
		test.assignAuthor("Author : Yogesh").assignCategory("Smoke Tests");
		
	}
	
	  @AfterMethod
	    public void getResult(ITestResult result) throws IOException
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = GetScreenShot.capture(driver, "Screenshot-"+System.currentTimeMillis());
	            test.log(LogStatus.FAIL, result.getThrowable());
	            test.log(LogStatus.FAIL, "Snapshot of failed test below: " + test.addScreenCapture(screenShotPath));
	        }
	        extent.endTest(test);
	    }
	
	@AfterSuite
	public void endReport(){
		extent.flush();
		
		//extent.close();
		
	}
	
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
