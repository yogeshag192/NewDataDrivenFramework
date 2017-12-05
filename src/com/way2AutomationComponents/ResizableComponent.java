package com.way2AutomationComponents;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.Base;

public class ResizableComponent extends Base {

	
	@BeforeTest
	public void launchWebsite() throws InterruptedException, IOException{
		launchURL("http://way2automation.com/way2auto_jquery/index.php");
		waitForPageToLoad(driver);
		System.out.println("On way2automation Page..");
		DragAndDropControls d = new DragAndDropControls();
		d.loginToWay2Automation();
	}
	
	
	@Test
	public void ResizableBox() throws InterruptedException{
		
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//figure/img[@src = 'images/resizable.jpg']"));
		
		action.moveToElement(element).build().perform();
		System.out.println("Moved to element : " +element.getAttribute("src"));
		action.click(element).perform();
		waitForPageToLoad(driver);
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class = 'demo-frame']")));
		WebElement resizableBox = driver.findElement(By.id("resizable"));
		action.moveToElement(resizableBox).build().perform();
		System.out.println("Moved to Element DragBox : " +resizableBox.getSize().toString());
		
		
		//resize pointer
		WebElement resizeArrow = driver.findElement(By.xpath("//div[contains(@class,'gripsmall-diagonal-se')]"));
		action.clickAndHold(resizeArrow).moveByOffset(800, 150).release().build().perform();
		resizableBox = driver.findElement(By.id("resizable"));
		System.out.println("Box resised to new dimentions : " +resizableBox.getSize().toString());
		
		
		
	}
}
