package com.testAmazonWebsite;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.Base;


public class TestAmazon extends Base {

	
	@BeforeTest
	public void launchWebsite() throws InterruptedException, IOException{
		launchURL(getInputValue("URLAmazon"));
		waitForPageToLoad(driver);
		System.out.println("On Amazon Home Page..");
	}
	
	@Test
	public void login() throws InterruptedException, IOException{
		System.out.println("Logging in to Amazon..");
		clickElement(getElementValue("loginButtonAmazon"));
		waitForElementToBeClickable(getElementValue("enterUserNameTextBoxAmazon"));
		type(getElementValue("enterUserNameTextBoxAmazon"), getInputValue("userNameAmazon"));
		type(getElementValue("enterPasswordTextBoxAmazon"), getInputValue("passwordAmazon"));
		clickElement(getElementValue("signINButtonAmazon"));
		waitForPageToLoad(driver);
		System.out.println("Logged in to Amazon Successfully..");
		clearCart();
	}
	
	
	@Test (dependsOnMethods = {"login"})
	public void verifySearchProduct() throws InterruptedException, AWTException, IOException
		{
		System.out.println("Searching a product Seagate Hard Drive..");
		type(getElementValue("productSearchTextBoxAmazon"), "seagate hard drive");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		waitForPageToLoad(driver);
		selectProduct("STDR2000101");
		
		}
	
	@Test (dependsOnMethods = {"verifySearchProduct"})
	public void verifyProductDetailsandCheckout() throws InterruptedException, AWTException, IOException{
		
		//verify whether product is in stock
		waitForElementToBeVisible(getElementValue("stockStatus"));
		WebElement stockStatus = driver.findElement(By.xpath("//*[@id='availability']/span"));
		String status = (stockStatus).getText();	
		System.out.println("Availability Status : = " +status);
		Assert.assertTrue(status.contains("In Stock"), "Product is not IN Stock");
		System.out.println("Product is In Stock..");
		
		//Selecting Capacity--below method will check if the capacity is available or it will select any other capacity..
		selectCapacity("500GB");
		
		String updatedStatus = driver.findElement(By.xpath("//*[@id='availability']/span")).getText();	
		System.out.println("Updated Availability Status : = " +updatedStatus);
		if(updatedStatus.contains("Available from")){
			System.out.println("There are Multiple Sellers for this item..");
			driver.findElement(By.linkText("these sellers")).click();
			waitForPageToLoad(driver);
			checkOutfromMultipleSellers();
		}
		else if(updatedStatus.contains("In Stock")){
			System.out.println("Checking out from same seller..");
			checkOutfromSameSeller();
		}		
		
	}

	@Test (dependsOnMethods = {"verifyProductDetailsandCheckout"})
	public void verifyAvailablePaymentMethods(){
		waitForPageToLoad(driver);
		System.out.println("Checking Available Payment Options..");
		List <WebElement> paymentMethods = driver.findElements(By.xpath("//*[@id='new-payment-methods']//h3"));
		System.out.println("Total Available Payment Methods:= " +paymentMethods.size());
		for(WebElement methods: paymentMethods){
			System.out.println("->" +methods.getText());
		}
	}
	
	@Test (dependsOnMethods = {"verifyAvailablePaymentMethods"})
	public void logout() throws IOException, InterruptedException{
		driver.get(getInputValue("URLAmazon"));
		waitForPageToLoad(driver);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("nav-link-accountList"))).perform();
		Thread.sleep(4000);
		clickElement(getElementValue("signOutButtonAmazon"));
		waitForElementToBeClickable(getElementValue("enterUserNameTextBoxAmazon"));
		System.out.println("User Logged out successfully..");
		
		
	}
	
		//@AfterTest
		public void quit(){
			driver.quit();
			
		} 
}
