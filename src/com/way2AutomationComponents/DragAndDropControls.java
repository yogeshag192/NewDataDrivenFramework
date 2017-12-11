package com.way2AutomationComponents;

import java.io.IOException;

import com.common.Base;

import com.google.common.base.*;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragAndDropControls extends Base {
	
	
	
	@BeforeTest
	public void launchWebsite() throws InterruptedException, IOException{
		launchURL("http://way2automation.com/way2auto_jquery/draggable.php");
		waitForPageToLoad(driver);
		System.out.println("On draggable Page..");
	}
	
	@Test
	public void loginToWay2Automation() throws IOException, InterruptedException{
		waitForElementToBeClickable(getElementValue("signInLinkWay2"));
		clickElement(getElementValue("signInLinkWay2"));
		waitForElementToBeVisible(getElementValue("userNameWay2"));
		type(getElementValue("userNameWay2"), getInputValue("way2UserName"));
		type(getElementValue("PasswordWay2"), getInputValue("way2Password"));
		clickElement(getElementValue("submitButtonWay2"));
		Thread.sleep(9000);
		
			
	}
	
	@Test(dependsOnMethods = "loginToWay2Automation")
	public void DraggableBox() throws InterruptedException{
		/*JavascriptExecutor es = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//img[@src = 'images/draggable.jpg']"));
		es.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(4000);
			System.out.println("Scroll Complete");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//img[@src = 'images/draggable.jpg']"));
		
		action.moveToElement(element).build().perform();
		System.out.println("Action Performed");
		action.click(element).perform();
		waitForPageToLoad(driver);
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class = 'demo-frame']")));
		WebElement dragBox = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		action.moveToElement(dragBox).build().perform();
		System.out.println("Moved to Element DragBox : " +dragBox.getLocation().toString());
		action.dragAndDropBy(dragBox, 550, 100).build().perform();
		System.out.println("Drag Performed"); 
		
		
		
	}
	
	@Test(dependsOnMethods = "DraggableBox")
	public void DroppableBox(){
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().defaultContent();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//figure/img[@src='images/droppable.jpg']"));
		waitForPageToLoad(driver);
		
		
		action.moveToElement(element).build().perform();
		action.click(element).perform();
		waitForPageToLoad(driver);
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class = 'demo-frame']")));
		
		WebElement dragBox = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		WebElement dropBox = driver.findElement(By.xpath("//*[@id='droppable']"));
		action.dragAndDrop(dragBox, dropBox).build().perform();;
		System.out.println("Drag and Drop Performed.....!");
		
	}

}
