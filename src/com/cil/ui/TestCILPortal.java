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
		System.out.println("\n-------------------- IN CIL - DASHBOARD TESTCASES ---------------------------");
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
	
	@Test (dependsOnMethods = "VerifyOnGoingProjects")
	public void VerifyToolsAndTechnologiesOnDashboard() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyToolsAndTechnologiesOnDashboard");
		cilM.VerifyToolsAndTechnologiesOnDashboard();
		System.out.println("Completed execution of :: VerifyToolsAndTechnologiesOnDashboard");
		System.out.println("-----------------------------------------------------------------\n");
	
	}
	
	@Test (dependsOnMethods = "VerifyToolsAndTechnologiesOnDashboard")
	public void VerifyUpcomingEventsOnDashboard() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyUpcomingEventsOnDashboard");
		cilM.VerifyUpcomingEventsOnDashboard();
		System.out.println("Completed execution of :: VerifyUpcomingEventsOnDashboard");
		System.out.println("-----------------------------------------------------------------\n");
	
	}
	
	
	
	@Test (dependsOnMethods = "VerifyUpcomingEventsOnDashboard")
	public void VerifyLabVisitsOnDashboard() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyLabVisitsOnDashboard");
		cilM.VerifyLabVisitsOnDashboard();
		System.out.println("Completed execution of :: VerifyLabVisitsOnDashboard");
		System.out.println("-----------------------------------------------------------------\n");
	
	}
	
	@Test (dependsOnMethods = "VerifyLabVisitsOnDashboard")
	public void VerifyTrendingIdeasOnDashboard() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyTrendingIdeasOnDashboard");
		cilM.VerifyTrendingIdeasOnDashboard();
		System.out.println("Completed execution of :: VerifyTrendingIdeasOnDashboard");
		System.out.println("-----------------------------------------------------------------\n");
	
	}
	
	@Test (dependsOnMethods = "VerifyTrendingIdeasOnDashboard")
	public void VerifyOpinionPollSubmit() throws InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyOpinionPollSubmit");
		cilM.VerifyOpinionPollSubmit();
		System.out.println("Completed execution of :: VerifyOpinionPollSubmit");
		System.out.println("-----------------------------------------------------------------\n");
	
	}
	
	@Test (priority = 1)
	public void VerifyLinksOnCILHomePage() throws IOException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyLinksOnCILHomePage");
		cilM.VerifyLinksOnCILHomePage();
		System.out.println("Completed execution of :: VerifyLinksOnCILHomePage");
		System.out.println("-----------------------------------------------------------------\n");
	
		
	}
	
	
	
}
