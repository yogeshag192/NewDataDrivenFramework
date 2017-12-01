package com.moco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.common.Base;

public class RecruitPage extends Base {
	
	
	@BeforeTest
	public void loginToMoco() throws InterruptedException, IOException{
		launchURL("https://www.mocospace.com/games?gid=1000&r=687&source=internal&fakeWk=true&url=%2Fwap2%2Fgame%2Fstreetwars%2Fbrowse-players.jsp");
		waitForPageToLoad(driver);
		System.out.println("On Moco Login Page..");
		clickElement(getElementValue("mocoLoginSelect"));
		waitForElementToBeClickable(getElementValue("mocoUserNameTextBox"));
		type(getElementValue("mocoUserNameTextBox"), "karan_12345");
		type(getElementValue("mocoPasswordTextBox"), "karansetwet");
		clickElement(getElementValue("mocoLoginButton"));
		waitForPageToLoad(driver);
	}
	
	
	@Test
	public void launchRecruitPage() throws InterruptedException, IOException{
		driver.navigate().to("https://www.mocospace.com/games?gid=1000&r=687&source=internal&fakeWk=true&url=%2Fwap2%2Fgame%2Fstreetwars%2Fbrowse-players.jsp%3Ftype%3Dnew%26v%3D1512120480686%26u%3D31277073");
		Thread.sleep(8000);
		System.out.println("On Recruit Page..");
	}
	
	@Test
	public void recruitPlayers() throws InterruptedException, IOException{
		Select select = new Select(driver.findElement(parseLocator(getElementValue("topRowPageSelectDropDown"))));
		List<WebElement> selectList = select.getOptions();
		int numberOfPages = selectList.size();
		System.out.println("Total Number of Page are : " +numberOfPages);
		int currentPageNumber ;
		
		for(int i = 1; i<=numberOfPages ; i++){
			List<WebElement> recruitElements = driver.findElements(By.xpath("//*[@id='game_content']//span[contains(@class,'recruit_button')]"));
			System.out.println("Total number of recruit links : " +recruitElements.size());
			
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElements(recruitElements));
			System.out.println("All elements are visible..");
			
			currentPageNumber = Integer.parseInt (select.getFirstSelectedOption().getText().toString());
			System.out.println("Current Page Number : " +currentPageNumber);
			
			Iterator<WebElement> it = recruitElements.iterator();
			while(it.hasNext()){
				WebElement recBut = it.next();
				recBut.click();
				Thread.sleep(2000);
			}
			/*for(WebElement recruitBut : recruitElements.){
	
				recruitBut.click();
				Thread.sleep(2000);
			}*/
			
			currentPageNumber++;
			select.selectByValue(String.valueOf(currentPageNumber));
			waitForPageToLoad(driver);
				
		}
		
		
		
	}
	
	

}
