package com.way2AutomationComponents;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.Base;

public class DatePicker extends Base {
	

	
		@BeforeTest
		public void launchWebsite() throws InterruptedException, IOException{
			launchURL("http://way2automation.com/way2auto_jquery/index.php");
			waitForPageToLoad(driver);
			System.out.println("On way2automation Page..");
			DragAndDropControls d = new DragAndDropControls();
			d.loginToWay2Automation();
		}
		
		
		
		@Test
		public void launchDatePickerControls() throws InterruptedException{
			
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//figure/img[@src = 'images/datepicker.jpg']"));
			
			action.moveToElement(element).build().perform();
			System.out.println("Moved to element : " +element.getAttribute("src"));
			action.click(element).perform();
			waitForPageToLoad(driver);
			
			
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='example-1-tab-1']//iframe[@class = 'demo-frame']")));
			
		}
		
		
		
		@Test (dependsOnMethods = "launchDatePickerControls")
		public void defaultCalendar(){
			
			driver.findElement(By.id("datepicker")).click();
			
			String dateToSelect = "26/August/2015";
			String dateArray[]  = dateToSelect.split("/");
			System.out.println("Contents of Array : " + Arrays.asList(dateArray));
			
			String monthXpath = "//*[@id='ui-datepicker-div']/div/div/span[1]";
			String yearXpath = "//*[@id='ui-datepicker-div']/div/div/span[2]";
			
			String prev = "//*[@id='ui-datepicker-div']/div/a[1]/span";
			String next = "//*[@id='ui-datepicker-div']/div/a[2]/span";
			
			for(int i = 0; i<100;i++){
			driver.findElement(By.xpath(prev)).click();
			String month = driver.findElement(By.xpath(monthXpath)).getText();
			String year = driver.findElement(By.xpath(yearXpath)).getText();
			System.out.println("Navigated to Month : " +month +" and year : " +year);
			
			if(month.equalsIgnoreCase(dateArray[1]) && year.equalsIgnoreCase(dateArray[2])){
				break;
				}
			}
			
			List <WebElement> days = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/table//a"));
			for (WebElement dayToSelect : days){
				
				if(dayToSelect.getText().equalsIgnoreCase(dateArray[0])){
					dayToSelect.click();
					System.out.println("Selected Date as : " +dayToSelect.getText());
				}
				
				
			}
			
			
			
			
			
		}

}
	

