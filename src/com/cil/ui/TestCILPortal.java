package com.cil.ui;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import com.common.Base;
import com.common.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCILPortal extends BaseTest {
	
	
	@Test
	public void VerifyCILDashboardComponents() throws InterruptedException
	{
		System.out.println("\n-------------------- IN CIL - HOME SCREEN TESTCASES ---------------------------");
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyCILDashboardComponents");
		cilM.VerifyCILDashboardComponents();
		System.out.println("Completed execution of :: VerifyCILDashboardComponents");
		System.out.println("-----------------------------------------------------------------\n");
	}
	
	@Test
	public void VerifyLinksOnCILDashboard() throws InterruptedException
	{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyLinksOnCILDashboard");
		cilM.VerifyLinksOnCILDashboard();
		System.out.println("Completed execution of :: VerifyLinksOnCILDashboard");
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
	
	@Test
	public void VerifyOnGoingProjects() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyOnGoingProjects");
		cilM.VerifyOnGoingProjects();
		System.out.println("Completed execution of :: VerifyOnGoingProjects");
		System.out.println("-----------------------------------------------------------------\n");
	
		
	}
	
	@Test
	public void zVerifyLinksOnCILHomePage() throws IOException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyLinksOnCILHomePage");
		cilM.VerifyLinksOnCILHomePage();
		System.out.println("Completed execution of :: VerifyLinksOnCILHomePage");
		System.out.println("-----------------------------------------------------------------\n");
	
		
	}
	
}
