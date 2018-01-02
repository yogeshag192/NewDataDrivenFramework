package com.cil.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	public void VerifyLinksOnCILHomePage() throws IOException, InterruptedException{
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
			String text = linkElement.getText();
		
			System.out.println("Link Element text : " +linkElement.getText());
			
			if(!(text.equalsIgnoreCase("LOGIN")))
			{
			  linkElement.click();
			  Thread.sleep(2000);
			}

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

	
	public void VerifyToolsAndTechnologiesOnDashboard() throws InterruptedException{
		//Hover Mouse on each tools and technology
		
		BaseTest.test.log(LogStatus.INFO, "Verifying Tools and Technologies on DashBoard : ");
		List<WebElement> toolsList = new ArrayList<WebElement>();
		toolsList = driver.findElements(By.xpath("//div[@class = 'panel-body col-md-12 panel-bg upper-panel tech-stack-panel']/div/span"));   
		
		if(toolsList.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "There are no tools available or the referenced xpath has been changed!");
			Assert.fail("Assertion Failed.");
		}
		
		Actions action = new Actions(driver);
		for (WebElement tool : toolsList){
			action.moveToElement(tool).build().perform();
			Thread.sleep(1000);
			BaseTest.test.log(LogStatus.PASS, "Hover Successful on Tool : " +tool.getText());
			System.out.println("Hover Successful on Tool : " +tool.getText());
		}
		
		
	}

	public void VerifyUpcomingEventsOnDashboard() throws InterruptedException{
		
		BaseTest.test.log(LogStatus.INFO, "Verifying Upcoming Events : ");
		List<WebElement> eventList = new ArrayList<WebElement>();
		eventList = driver.findElements(By.xpath("//ul[@class = 'event-list list-group']/li/div/a"));
		
		if(eventList.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "There are no upcoming events available or the referenced xpath has been changed!");
			Assert.fail("Assertion Failed.");
		}
		
		//Scroll till bottom of page
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(3000);
		
		
		for (WebElement event : eventList){
			action.moveToElement(event).build().perform();
			Thread.sleep(1000);
			BaseTest.test.log(LogStatus.PASS, "Hover Successful on Event : " +event.getText());
			System.out.println("Hover Successful on Tool : " +event.getText());
		}
		
	}

	public void VerifyLabVisitsOnDashboard() throws InterruptedException{
		
		BaseTest.test.log(LogStatus.INFO, "Verifying Lab Visits : ");
		List<WebElement> labVisits = new ArrayList<WebElement>();
		labVisits = driver.findElements(By.xpath("//ul[@class = 'list-group']/li/div/span"));
		
		if(labVisits.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "There are no lab visits available or the referenced xpath has been changed!");
			Assert.fail("Assertion Failed.");
		}
		
		//Scroll till bottom of page
		Actions action = new Actions(driver);
		/*action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		Thread.sleep(3000);*/
		
		
		for (WebElement visit : labVisits){
			action.click(visit).build().perform();
			Thread.sleep(1000);
			action.click(visit).build().perform();
			Thread.sleep(1000);
			BaseTest.test.log(LogStatus.PASS, "Verified Lab Visit : " +visit.getText());
			System.out.println("Verified Lab Visit : " +visit.getText());
		}
		
	}


	public void VerifyTrendingIdeasOnDashboard(){
		BaseTest.test.log(LogStatus.INFO, "Verifying Trending Ideas : ");
		List<WebElement> Ideas = new ArrayList<WebElement>();
		Ideas = driver.findElements(By.xpath("//li[@id = 'trending']"));
		
		if(Ideas.isEmpty()){
			BaseTest.test.log(LogStatus.FAIL, "There are no Trending Ideas available or the referenced xpath has been changed!");
			Assert.fail("Assertion Failed.");
		}
		
		for (WebElement idea : Ideas){
			
			BaseTest.test.log(LogStatus.PASS, "Verified Trending Idea : " +idea.getText());
			System.out.println("Verified Lab Visit : " +idea.getText());
		}
		
	}

	public void VerifyOpinionPollSubmit() throws IOException{
		BaseTest.test.log(LogStatus.INFO, "Verify Available Options for Opinion Poll:");
		//input[@type = 'radio' and @value = 'Good']
		List<WebElement> opinionsList = new ArrayList<WebElement>();
		opinionsList = driver.findElements(By.xpath("//div[@class = 'radio cilFont14']/label"));
		for (WebElement opinion : opinionsList){
			
			BaseTest.test.log(LogStatus.PASS, opinion.getText());
		}
		
		for (WebElement opinion : opinionsList){
			
			if(opinion.getText().equalsIgnoreCase("Good")){
				opinion.click();
				clickElement(getElementValue("voteNowButton"));
				BaseTest.test.log(LogStatus.PASS, "Submitted Opinion : " +opinion.getText());
			}
			
		}
	}


	
	
	public void VerifySignUpFunctionalityOnCILTest() throws IOException, InterruptedException{

		//clickElement(getElementValue("signUpLink"));
		waitForPageToLoad("20");
			clickElement(getElementValue("userNameTextBox"));
			type(getElementValue("userNameTextBox"), "AutoTestUser" +System.currentTimeMillis());
			type(getElementValue("passwordTextBox"), "Test@123");
			type(getElementValue("firstName"), "Robot");
			type(getElementValue("lastName"), "Auto");
			type(getElementValue("phoneNo"), "1234567890");
			type(getElementValue("email"), "agrawaly@hcl.com");
			Thread.sleep(2000);
			clickElement(getElementValue("signUpButton"));
			Thread.sleep(3000);
		
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert1 Message: " +alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
		alert = driver.switchTo().alert();
		System.out.println("Alert2 Message: " +alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
		
		
		
	}
}
