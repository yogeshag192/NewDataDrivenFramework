package com.cil.ui;

import java.io.IOException;

import org.testng.annotations.Test;

import com.common.BaseTest;

public class CILTestSuite2 extends BaseTest {

	
	@Test
	public void VerifyLinksOnCILHomePage() throws IOException, InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifyLinksOnCILHomePage");
		cilM.VerifyLinksOnCILHomePage();
		System.out.println("Completed execution of :: VerifyLinksOnCILHomePage");
		System.out.println("-------------------------------------------------------------------\n");
	
		
	}
	
	@Test (priority = 1)
	public void VerifySignUpFunctionalityOnCILTest() throws IOException, InterruptedException{
		
		CILPageMethods cilM = new CILPageMethods(driver);
		System.out.println("Started execution of :: VerifySignUpFunctionalityOnCILTest");
		cilM.VerifySignUpFunctionalityOnCILTest();
		System.out.println("Completed execution of :: VerifySignUpFunctionalityOnCILTest");
		System.out.println("-------------------------------------------------------------------\n");
	
		
	}
	
}
