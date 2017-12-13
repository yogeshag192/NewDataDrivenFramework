package com.common;

public class Constants {

	public static String CONFIGURATION_PATH = System.getProperty("user.dir") +"\\Files\\config.properties";
	public static String INPUT_DATA_FILE = System.getProperty("user.dir") +"\\Files\\input.properties";
	public static String WEBELEMENT_FILE = System.getProperty("user.dir") +"\\Files\\webElement.properties";
	public static String DOWNLOAD_DIRECTORY = System.getProperty("user.dir") + "\\Downloads\\";
	public static String CHROME_DRIVER_PATH = System.getProperty("user.dir") +"\\Files\\chromedriver.exe";
	public static String IE_DRIVER_PATH = System.getProperty("user.dir") +"\\Files\\IEDriverServer.exe";
	
	
	public static void main(String[] args) {
		System.out.println(CHROME_DRIVER_PATH);
	}
}
