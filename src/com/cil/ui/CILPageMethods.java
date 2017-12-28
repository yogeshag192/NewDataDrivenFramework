package com.cil.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.common.Base;
import com.common.BaseTest;
import com.common.ExtentReportSetup;
import com.relevantcodes.extentreports.LogStatus;

public class CILPageMethods extends Base{

	public CILPageMethods(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	//Test Case Methods start here
	public void VerifyLinksOnCILHomePage() throws IOException{
		// Verify Links on the page
		System.out.println("\n" +"-----Verifying Links on the Home Page..");
		
		clickElement(getElementValue("logoutButton"));
		waitForPageToLoad("30");
		List<WebElement> links = new ArrayList<WebElement>();
		links = driver.findElements(By.xpath("//ul[@class = 'nav navbar-nav navbar-right']/li/a"));
		
		if (links.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "Test Failed : No Links Present on Home Page!");
		}
		else {
			BaseTest.test.log(LogStatus.PASS, links.size()+" Links are Present on Home Page.");
		}
		for(WebElement linkElement : links){
			Assert.assertTrue(isElementPresentAndDisplayed(linkElement), "Assert Failed!");
			System.out.println("Link Verified : " +linkElement.getText());
			BaseTest.test.log(LogStatus.PASS,  linkElement.getText() +" : Link is verified");
		}
	}
	
	public void VerifyCILDashboardComponents() {
		
		//String allAvailableComponentsPath = "//div[Contains(@class ,'panel-heading')]";
		//Verify tabs on Home Page
		
		waitForPageToLoad("60");
		ArrayList<String> componentList = new ArrayList<String>();
		componentList.add("On-going Projects");
		componentList.add("Tools and Technologies");
		componentList.add("Newsletter");
		componentList.add("Upcoming Events");
		componentList.add("Lab Visits");
		componentList.add("Trending Ideas");
		componentList.add("Opinion Poll");
		//componentList.add("Upcoming Options");
		
		System.out.println("-----Verifying Components on the Dashboard..");
		for(String counter : componentList){
			String pageSource = driver.getPageSource();
			if (pageSource.contains(counter)){
				System.out.println(counter + " Component is available");
				Assert.assertTrue(pageSource.contains(counter));
				BaseTest.test.log(LogStatus.PASS, counter + " Component is available");	
			}
			else {
				BaseTest.test.log(LogStatus.FAIL, counter + " Component is not available");
			}
		}
		
	
	}
	
	public void VerifyLinksOnCILDashboard(){
		// Verify Links on the page
		System.out.println("\n" +"-----Verifying Links on the Dashboard..");
		
		List<WebElement> links = new ArrayList<WebElement>();
		links = driver.findElements(By.xpath("//a[@class = 'dash-menu-adjust cilFont']"));
		
		if (links.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "Test Failed : No Links Present on Dashboard");
		}
		else {
			BaseTest.test.log(LogStatus.PASS, links.size()+" Links are Present on Dashboard");
		}
		for(WebElement linkElement : links){
			Assert.assertTrue(isElementPresentAndDisplayed(linkElement));
			System.out.println("Link Verified : " +linkElement.getText());
			BaseTest.test.log(LogStatus.PASS,  linkElement.getText() +" : Link is verified");
		}
	}
	
	public void VerifyTest7(){
		Assert.assertTrue(true);
		BaseTest.test.log(LogStatus.PASS, "Assert Passed as condition is true.");
		Assert.assertTrue(false);
		BaseTest.test.log(LogStatus.FAIL, "Assert Failed as condition is false.");
	}

	public void VerifyOnGoingProjects() throws InterruptedException{
		List<WebElement> onGoingProjectList;
		
		//Verify Internal Projects
		BaseTest.test.log(LogStatus.INFO, "Task: Verify Internal Projects.");
		onGoingProjectList = driver.findElements(By.xpath("//div[@id = 'projects-pane-1']//button"));
		
		if(onGoingProjectList.isEmpty()){
			BaseTest.test.log(LogStatus.ERROR, "There are no internal Projects or the refrenced xpath has been changed!");
		}
		
		for (WebElement options : onGoingProjectList){
			options.click();
			System.out.println("Clicking on Project - " +options.getText());
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='fa fa-close cil-close']")).click();
			Thread.sleep(2000);
			BaseTest.test.log(LogStatus.PASS, options.getText()+ " Project is Verified..");
		}
		
		//Verify External, Projects
		
		BaseTest.test.log(LogStatus.INFO, "Task: Verify External Projects.");
		driver.findElement(By.xpath("//a[@id = 'projects-tab-2']")).click();
		onGoingProjectList = driver.findElements(By.xpath("//div[@id = 'projects-pane-2']//button"));
		
		if(onGoingProjectList.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "There are no external Projects or the refrenced xpath has been changed!");
		}
		
		for (WebElement options : onGoingProjectList){
			options.click();
			System.out.println("Clicking on Project - " +options.getText());
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='fa fa-close cil-close']")).click();
			Thread.sleep(2000);
			BaseTest.test.log(LogStatus.PASS, options.getText()+ " Project is Verified..");
		}
		BaseTest.test.log(LogStatus.PASS, " ----All Ongoing Projects Verified Successfully----");
		
		
	
	}

}
