package com.common;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

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

public class BaseTest{

	protected static WebDriver driver;
	protected Properties webElementProperties;
	protected Properties appProperties;
	protected Properties configProperties;
	
	@BeforeSuite
	public void beforeSuiteSetup(){
		System.out.println("Before Suite Setup..");
	}
	
	@BeforeMethod
	public void beforeMethodExtentSetup(Method method){
		System.out.println("Setting up Joption Pane config..");
		//for JOptionPane Display Popup
		JOptionDialogue(method);
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = GetScreenShot.capture(driver, "Screenshot-"+System.currentTimeMillis());
	            System.out.println("Error Screenshot stored at: " +screenShotPath);
	            /*test.log(LogStatus.FAIL, result.getThrowable());
	            test.log(LogStatus.FAIL, "Snapshot of failed test below: " + test.addScreenCapture(screenShotPath));
	        */}
	  /*      extent.endTest(test);*/
	    }
	
	@AfterSuite
	public void endDriverSession(){
		
		driver.close();
		
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
	
	public void JOptionDialogue(Method method){
		
		String className = this.getClass().getSimpleName();
		String methodName = method.getName();
		String currentRunningTest = "Executing Test:"+ "\n\n" +"Class Name: "+className +"\n" +"Method Name: "+methodName;
		
		final JOptionPane optionPane = new JOptionPane(currentRunningTest, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

		final JDialog dialog = new JDialog();
		dialog.setTitle("Information");
		dialog.setModal(true);

		dialog.setContentPane(optionPane);

		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();

		//create timer to dispose of dialog after 5 seconds
		Timer timer = new Timer(5000, new AbstractAction() {
			 @Override
			    public void actionPerformed(ActionEvent ae) {
			        dialog.dispose();
			    }
		});
		timer.setRepeats(false);//the timer should only go off once

		//start timer to close JDialog as dialog modal we must start the timer before its visible
		timer.start();

		dialog.setVisible(true);
	}
	
	}
