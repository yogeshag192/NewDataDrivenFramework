package com.cil.ui;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import com.common.Base;
import com.common.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCILPortal extends BaseTest {
	
	
	@Test
	public void VerifyCILHomePageComponents() throws InterruptedException
	{
	
		System.out.println("\n-------------------- IN CIL - HOME SCREEN TESTCASES ---------------------------");
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyCILHomePageComponents");
		cilM.VerifyCILHomePageComponents();
		System.out.println("Completed execution of :: VerifyCILHomePageComponents");
		System.out.println("-----------------------------------------------------------------\n");
	}
	
	@Test
	public void VerifyCILHomePageLinks() throws InterruptedException
	{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyCILHomePageLinks");
		cilM.VerifyCILHomePageLinks();
		System.out.println("Completed execution of :: VerifyCILHomePageLinks");
		System.out.println("-----------------------------------------------------------------\n");
	}
	
	
	@Test
	public void test7(){
	
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: test7");
		cilM.VerifyTest7();
		System.out.println("Completed execution of :: test7");
		System.out.println("-----------------------------------------------------------------\n");
	
		
	}
	
}
