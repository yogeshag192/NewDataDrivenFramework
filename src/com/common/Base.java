package com.common;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Base {
	public static WebDriver driver;
	public Properties appProperties;
	public static Properties webElementProperties;
	
	
	public Base(WebDriver driver) {
		this.driver = driver;
		Base.webElementProperties = PropertiesUtil.getWebElementProperties();
		this.appProperties = PropertiesUtil.getAppProperties();
	}
	
	public void launchURL(String url) throws InterruptedException{
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver(); 
		System.out.println("Opening URL: " +url);
		 driver.get(url);
		 driver.manage().window().maximize();
		 System.out.println("Title of URL is " +driver.getTitle());
		 Thread.sleep(2000);
	}
	
	
	public boolean isTextPresent(String text) {
		return driver.getPageSource().contains(text);
	}
	
	public String getElementValue(String propertyName) throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"\\Files\\webElement.properties");
		appProperties = new Properties();
		appProperties.load(fis);
		return appProperties.getProperty(propertyName);
	}
	
	public String getInputValue(String propertyName) throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"\\Files\\input.properties");
		appProperties = new Properties();
		appProperties.load(fis);
		return appProperties.getProperty(propertyName);
		
	}
	
	public By parseLocator(String webElementProperty) {
		By by = null;
		String locatorType = webElementProperty.substring(0, 2);
		String elementId = webElementProperty.substring(3, webElementProperty.length() - 1);
	
		System.out.println("Current Element id : " +elementId);
		if (locatorType.equals("xp")) // xpath
		{
			by = By.xpath(elementId);
		} else if (locatorType.equals("id")) // id
		{
			by = By.id(elementId);
		} else if (locatorType.equals("nm")) // name
		{
			by = By.name(elementId);
		} else if (locatorType.equals("cl")) // className
		{
			by = By.className(elementId);
		} else if (locatorType.equals("lt")) // linkText
		{
			by = By.linkText(elementId);
		}else if (locatorType.equals("pt")) // partialLinkText
		{
			by = By.partialLinkText(elementId);
		}
		else if (locatorType.equals("cs")) // cssSelector
			
		{
			by = By.cssSelector(elementId);
		} else if (locatorType.equals("tg")) // tagName
		{
			by = By.tagName(elementId);
		} else {
			System.out.println("Cannot identify locator Type");
		}
		return by;
	}
	
	public WebElement findElement(String webElementProperty){
		By by = parseLocator(webElementProperty);
		WebElement ele = driver.findElement(by);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		ele = wait.until(ExpectedConditions.elementToBeClickable(by));
		return ele;
	}
	
	public void type(String locator, String text) throws InterruptedException{
		By by = parseLocator(locator);

		WebElement ele = driver.findElement(by);
		ele.click();
		Thread.sleep(1000);
		ele.sendKeys(text);
	}
	
	public void waitForElementToBeVisible(String locator){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(parseLocator(locator)));
	}
	
	public boolean isElementPresentAndDisplayed(String locator){
		By by = parseLocator(locator);
		WebElement ele = driver.findElement(by);
		if(ele.isDisplayed()&&ele.isEnabled()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean isElementPresentAndDisplayed(WebElement ele){
		if(ele.isDisplayed()&&ele.isEnabled()){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public void waitForElementToBeClickable(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(parseLocator(locator)));
	}
	
	public void clickElement(String locator) {
		By by = parseLocator(locator);
		WebElement element = driver.findElement(by);
		if (element == null || (!element.isEnabled())) {
			fail("Did not find element to click : " + element.getAttribute("id"));
		}
		WebDriverWait wait = new WebDriverWait(driver, 120);
		element = wait.until(ExpectedConditions.elementToBeClickable(by));
		element.click();
		
	}
	
	public void waitForPageToLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	            return ((JavascriptExecutor) driver).executeScript(
	            "return document.readyState").equals("complete");
	        }
	    };
	    Wait<WebDriver> wait = new WebDriverWait(driver, 60);
	    try {
	        wait.until(expectation);
	    } catch (Throwable error) {
	        Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");
	    }
	}
	
	public void waitForPageToLoad(String timeOutInSeconds) {
		String windowTitle = driver.getTitle();
		int time = Integer.parseInt(timeOutInSeconds);
		int pageLength = 0;
		for (int second = 0;; second++) {
			if (second >= time) {
				fail("Timeout... Page load could not complete in " + timeOutInSeconds + " seconds");
			}
			if (pageLength == driver.getPageSource().length() && windowTitle != driver.getTitle()) {
				break;
			}
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pageLength = driver.getPageSource().length();
		}
	}
	
	public void selectTomorrowsDateFromCalendar(){
		List<WebElement> calendars = driver.findElements(By.xpath("//input[@class='sE' and @readonly ='readonly']"));
		calendars.get(0).click();
		
		String tomorrowDatePicker = "xp(//*[@class='J-jxwiSc J-JB-KA']//tr//td[@aria-label='DateInput'])";
		
		GregorianCalendar calendar = new GregorianCalendar();
		DateFormat fmt = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.UK);
		calendar.add(calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();
		String formattedDate = fmt.format(tomorrow);
		String newDateInput = formattedDate.substring(0,6).replace('-', ' ');
		System.out.println(newDateInput);
		String newDatePath = tomorrowDatePicker.replace("DateInput", newDateInput);
		System.out.println("New Date Path Created : " +newDatePath);
		clickElement(newDatePath);
	}
	
	public boolean isAlertPresent(){
		try{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException ex){
		return false;
		}
				
}
	
	//Amazon base methods
	
	public void clearCart() throws InterruptedException, IOException{
		clickElement(getElementValue("cartButton"));
		waitForPageToLoad(driver);
		List <WebElement> deleteLinks = driver.findElements(By.xpath("//input[@value='Delete']"));
		System.out.println("Total items already in cart: = " +deleteLinks.size());
		if(deleteLinks.size()==0){
			System.out.println("Cart is empty! No items to remove.");
		}
		else{
		for (int i =0; i<deleteLinks.size(); i++) {
			deleteLinks.get(i).click();
			Thread.sleep(6000);
			}
		System.out.println("Cart is cleared..Total items removed : = " +deleteLinks.size());
		}
	}
	
	public void checkOutfromMultipleSellers() throws InterruptedException, AWTException, IOException{
		System.out.println("Selecting a seller from multiple sellers..");
		List <WebElement> sellersList = driver.findElements(By.xpath(".//input[@name='submit.addToCart']"));
		if(sellersList.size()!=0){
			sellersList.get(0).click();
			waitForPageToLoad(driver);
			waitForElementToBeClickable(getElementValue("cartButtonAtCheckout"));
			clickElement(getElementValue("cartButtonAtCheckout"));
			waitForPageToLoad(driver);
			clickElement(getElementValue("quantitySelectDropdown"));
			Thread.sleep(2000);
			Robot robo = new Robot();
			robo.keyPress(KeyEvent.VK_2);
			Thread.sleep(2000);
			robo.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			clickElement(getElementValue("proceedToCheckOutButton"));
			waitForPageToLoad(driver);
			clickElement(getElementValue("selectAddress"));
			waitForPageToLoad(driver);
			clickElement(getElementValue("continueShippingButton"));
			Thread.sleep(6000);
		}
	}
	
	public void checkOutfromSameSeller() throws InterruptedException, AWTException, IOException{
		WebElement addtionalItem = driver.findElement(By.xpath("//*[@id='sims-fbt-form']//div//li[2]//input"));
		Thread.sleep(2000);
		if(addtionalItem!=null){
			addtionalItem.click();
			
			Thread.sleep(3000);
			System.out.println("Additional item unchecked.");
		}
		
		clickElement(getElementValue("addToCartButtonSingleSeller"));
		Thread.sleep(6000);
		if(isAlertPresent()==true){
			driver.switchTo().alert();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id='siNoCoverage-announce']")).click();
			Thread.sleep(8000);
		}
		
		waitForElementToBeClickable(getElementValue("cartButtonAtCheckout"));
		clickElement(getElementValue("cartButtonAtCheckout"));
		waitForPageToLoad(driver);
		clickElement(getElementValue("quantitySelectDropdown"));
		Thread.sleep(2000);
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_2);
		Thread.sleep(2000);
		robo.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		clickElement(getElementValue("proceedToCheckOutButton"));
		waitForPageToLoad(driver);
		clickElement(getElementValue("selectAddress"));
		waitForPageToLoad(driver);
		clickElement(getElementValue("continueShippingButton"));
		Thread.sleep(6000);
		
	}
	
	
	public void selectProduct(String name) throws InterruptedException{
		List <WebElement> links = driver.findElements(By.xpath(".//*[@id='resultsCol']//li//h2"));
		if (links.size()==0){
			System.out.println("No items are displayed after search!");
				
		}
		else{
			System.out.println("Total Items Displayed After Search : = " +links.size());
		}
		
		Boolean flag = false;
		for (WebElement product : links)
			{
			String productText = product.getText();
			if (productText.contains(name))
					{
					System.out.println("Selecting Product: " +productText);
					product.click();
					waitForPageToLoad(driver);
					System.out.println("Product found and clicked..");
					flag = true;
					break;
					}
			}
		
			if (flag == false)
			{
			links.get(0).click();
			waitForPageToLoad(driver);
			System.out.println("Desired product not found..Selected other available product..");
			}
	}
	
	public void selectCapacity(String cap) throws InterruptedException{
		
		List <WebElement> capList = driver.findElements(By.xpath("//*[@class='a-button-inner']//div/div/span"));
		Assert.assertTrue(capList.size()>0,"Capacity Selection not present!");
		System.out.println("Total available capacity options: = " +capList.size());
		
		Boolean flag1 = false;
		for( WebElement capacity : capList){
			if (capacity.getText().equalsIgnoreCase(cap)){
				capacity.click();
				waitForPageToLoad(driver);
				Thread.sleep(4000);
				System.out.println("Capacity Selected is : " +capacity.getText());
				flag1 = true;
				break;
			}
		}
			if(flag1 == false) {
					capList.get(0).click();
					Thread.sleep(6000);
					System.out.println(cap + " Capacity is not present hence Selected Other Available Capacity : " +capList.get(0).getText());
			}
		
	}
	
	//------------------------------Way 2 Automation Base Methods-----------------------//
	
	
	public String getStatusOfDate(String myDate) throws ParseException{
		
		GregorianCalendar calendar = new GregorianCalendar();
		Date today = calendar.getTime();
		DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		Date newDateObject = (Date)formatter.parse(myDate);
		System.out.println(newDateObject);
		System.out.println(today);
		if (newDateObject.before(today)){
			return "before";
		}else if (newDateObject.after(today)){
			return "after";
		}
		else{
			return "same";
		}
		
		
	}
	
}
