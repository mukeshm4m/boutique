package com.boutique.common.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	
	private static Properties resoucesProperties;
	
	private static void loadResoucesProperties() {

		try {
			resoucesProperties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("resources.properties");
			
			resoucesProperties.load(input);
		} catch (Exception e) {
			
		}
	}
	
	public static String getPropertyByName(String key) {
		
		if(resoucesProperties == null) {
			loadResoucesProperties();
		}
		
		return resoucesProperties.getProperty(key);
	}
	
}
