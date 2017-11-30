package com.testGmail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.Base;


public class TestGmail extends Base {

	
	@BeforeTest
	public void launch() throws IOException, InterruptedException{
		
		launchURL(getInputValue("URLGmail"));
		waitForElementToBeClickable(getElementValue("enterEmailTextBox"));
		System.out.println("On Gmail Login Page..");
	}
	
	@Test
	public void login() throws IOException, InterruptedException{
		System.out.println("Logging in to Gmail..");
		type(getElementValue("enterEmailTextBox"), getInputValue("userNameGmail"));
		clickElement(getElementValue("nextButtonOnLoginPage"));
		waitForElementToBeVisible(getElementValue("passwordTextBoxOnLoginPage"));
		type(getElementValue("passwordTextBoxOnLoginPage"), getInputValue("passwordGmail"));
		clickElement(getElementValue("signInButtonOnLoginPage"));
		waitForElementToBeClickable(getElementValue("gmailLogo"));
		waitForPageToLoad(driver);
		System.out.println(getInputValue("userNameGmail") + " Logged in Successfully..");
	}
	
	@Test (dependsOnMethods = {"login"})
	public void verifyInbox() throws IOException, InterruptedException{
		clickElement(getElementValue("inboxLink1"));
		System.out.println("Inbox Clicked Successfully..");
		List<WebElement> allMails = driver.findElements(By.xpath("//*[@class='F cf zt']//tr"));
		System.out.println("Number of mails in Inbox (Read + Unread) := " +allMails.size() );
		if(allMails.size()==0)
			{
			System.out.println("There are no mails in Inbox!");
			}
		else{
			System.out.println("Clicking on first mail..");
			allMails.get(0).click();
			waitForElementToBeClickable(getElementValue("replyEmailButton"));
			System.out.println("First Email Opened Successfully..");
			Thread.sleep(2000);
			clickElement(getElementValue("backToInboxButton"));
			waitForElementToBeClickable(getElementValue("refreshButton"));
			System.out.println("Navigated Back to Inbox..");
			}			
		}
	
	
	@Test (dependsOnMethods = {"verifyInbox"})
	public void verifyComposeAndSendEmail() throws IOException, InterruptedException{
		
		Assert.assertTrue(isElementPresentAndDisplayed(getElementValue("composeButton")), "Element is not displayed!");
		clickElement(getElementValue("composeButton"));
		System.out.println("Compose button clicked..");
		waitForElementToBeVisible(getElementValue("newMailBoxHeader"));
		type(getElementValue("recepientTextBox"), getInputValue("recepientEmail"));
		String subject = "TestMail-"+System.currentTimeMillis();
		type(getElementValue("subjectBox"), subject );
		type(getElementValue("messageBox"), "This is a Test Message..");
		clickElement(getElementValue("sendMailButton"));
		System.out.println("Email Sent Successfully..");
		clickElement(getElementValue("refreshButton"));
		Thread.sleep(3000);
		
		//check new mail in mailbox received or not
		List<WebElement> updatedMailsList = driver.findElements(By.xpath("//*[@class='F cf zt']//tr"));
		System.out.println("New Mail Row : = "+updatedMailsList.get(0).getText());
		if(updatedMailsList.get(0).getText().contains(subject)){
			System.out.println("New Email Received after refresh..");
		}
		else{
			System.out.println("New Email not Received after refresh..");
		}
		
		
	}
	

	@Test (dependsOnMethods = {"verifyComposeAndSendEmail"})
	public void setVacationResponder() throws IOException, AWTException, InterruptedException{
		
		System.out.println("Setting vacation responder..");
		clickElement(getElementValue("settingsButton"));
		Robot robo = new Robot();
		for(int i=0; i<5; i++){
			robo.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
		}
		robo.keyPress(KeyEvent.VK_ENTER);
		
		waitForElementToBeClickable(getElementValue("vacationResponderOn"));
		clickElement(getElementValue("vacationResponderOn"));
		
		selectTomorrowsDateFromCalendar();
		
		System.out.println("Date selected as tomorrow's date..");
		driver.findElement(By.xpath("//input[@aria-label='Subject']")).clear();
		type(getElementValue("vacationSubjectBox"), "Out of Office"+Math.random());
		Thread.sleep(2000);
		clickElement(getElementValue("vacationSaveChanges"));
		System.out.println("Vacation Settings Updated Successfully..");
		Thread.sleep(5000);

	}
	
	
	
	@Test (dependsOnMethods = {"setVacationResponder"})
	public void logout() throws IOException, InterruptedException{
		clickElement(getElementValue("profileButton"));
		clickElement(getElementValue("signOut"));
		Thread.sleep(5000);
		System.out.println("User logged out successfully..");	
		
	}
	
	//@AfterTest
	public void quit(){
		driver.quit();
		
	}
	
	
}
