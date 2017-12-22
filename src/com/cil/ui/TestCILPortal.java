package com.cil.ui;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.common.Base;
import com.common.BaseTest;

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
	
	
	
}
