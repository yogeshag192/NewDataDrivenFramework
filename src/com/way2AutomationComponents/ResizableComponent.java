package com.way2AutomationComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.common.Base;

public class ResizableComponent extends Base {

	public void DraggableBox() throws InterruptedException{
		
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//figure/img[@src = 'images/resizable.jpg']"));
		
		action.moveToElement(element).build().perform();
		System.out.println("Moved to element : " +element.getAttribute("src"));
		action.click(element).perform();
		waitForPageToLoad(driver);
		
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class = 'demo-frame']")));
		WebElement dragBox = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		action.moveToElement(dragBox).build().perform();
		System.out.println("Moved to Element DragBox : " +dragBox.getLocation().toString());
		action.dragAndDropBy(dragBox, 550, 100).build().perform();
		System.out.println("Drag Performed...!!"); 
		
		
		
	}
}
