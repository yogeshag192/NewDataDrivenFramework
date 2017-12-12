package com.cil.ui;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

import com.common.Base;
import com.way2AutomationComponents.DragAndDropControls;

public class TestCILPortal extends Base {
	
	
	@BeforeTest
	public void launchWebsite() throws InterruptedException, IOException{
		launchURL("http://way2automation.com/way2auto_jquery/index.php");
		waitForPageToLoad(driver);
		System.out.println("On way2automation Page..");
		DragAndDropControls d = new DragAndDropControls();
		d.loginToWay2Automation();
	}

}
