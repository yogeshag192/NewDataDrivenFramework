package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
	public static Properties configProperties;
	public static Properties webElementProperties;
	public static Properties inputProperties;
	public static InputStream inputStream;

	public static Properties readInputArguments() {

		if (configProperties == null) {
			try {
				inputStream = new FileInputStream(Constants.CONFIGURATION_PATH);
				configProperties = new Properties();
				configProperties.load(inputStream);
			} catch (IOException e) {
				System.out.println("Unable to load Arguments Properties file." + e.getMessage());
			}

		}
		return configProperties;
	}
	
	
	public static Properties getWebElementProperties() {
		if (webElementProperties == null) {
			try {
				inputStream = new FileInputStream(Constants.WEBELEMENT_FILE);
				webElementProperties = new Properties();
				webElementProperties.load(inputStream);
			} catch (IOException e) {
				System.out.println("Unable to load Arguments Properties file." + e.getMessage());
				}
		}
		return webElementProperties;
	}

	 public static Properties getAppProperties() {
	        if (inputProperties == null) {
	            try {
	                inputStream = new FileInputStream(Constants.INPUT_DATA_FILE);
	                inputProperties = new Properties();
	                inputProperties.load(inputStream);
	            } catch (IOException e) {
	            	System.out.println("Unable to load Arguments Properties file." + e.getMessage());
	    			}
	        }
	        return inputProperties;
	    }
}

