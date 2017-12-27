package com.common;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportSetup {

	public static ExtentReports extent;
	public static ExtentTest test;
	

	
	@BeforeSuite
	public void beforeSuiteSetup(){
		System.out.println("In BeforeSuiteSetup Method..");
		extent = new ExtentReports(System.getProperty("user.dir")+ "/extent-output/ExtentExecutionReport.html",true);
		extent.addSystemInfo("HostName", "Yogesh")
		.addSystemInfo("Environment", "QA")
		.addSystemInfo("User Name", " Yogesh Agrawal");
		extent.loadConfig(new File(System.getProperty("user.dir")+ "/extent-config.xml"));
	}
	
	@BeforeMethod
	public void beforeMethodSetup(Method method){
		System.out.println("In BeforeMethodSetup Method..");
		test = extent.startTest((this.getClass().getSimpleName() + " :: " +method.getName()), method.getName());
		test.assignAuthor("Yogesh Author").assignCategory("Smoke Tests");
		
	}
	
	@AfterSuite
	public void endReport(){
		extent.flush();
		
		//extent.close();
		
	}
}
