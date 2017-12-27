package com.cil.ui;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.common.Base;

public class CILPageMethods extends Base{

	public CILPageMethods(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	//Test Case Methods start here
	public void VerifyCILHomePageComponents() {
		
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
		
		System.out.println("-----Verifying Components on the Home Screen..");
		for(String counter : componentList){
			String pageSource = driver.getPageSource();
			if (pageSource.contains(counter)){
				System.out.println(counter + " Component is available");
				Assert.assertTrue(pageSource.contains(counter), counter + " Component is not available! ");
			}
		}
		
		// Verify Links on the page
		System.out.println("\n" +"-----Verifying Links on the Home Screen..");
		
		List<WebElement> links = new ArrayList<WebElement>();
		links = driver.findElements(By.xpath("//a[@class = 'dash-menu-adjust cilFont']"));
		for(WebElement linkElement : links){
			Assert.assertTrue(isElementPresentAndDisplayed(linkElement), "Element is not present!");
			System.out.println("Link Verified : " +linkElement.getText());
		}
		
	
	}

}
