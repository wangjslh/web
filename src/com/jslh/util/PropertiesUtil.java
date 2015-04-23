package com.jslh.util;

import java.util.ResourceBundle;

public class PropertiesUtil {
	
	private static ResourceBundle configProperties = ResourceBundle.getBundle("config");
	
	private static ResourceBundle uploadProperties = ResourceBundle.getBundle("upload");

	public static ResourceBundle getConfigProperties() {
		return configProperties;
	}

	public static ResourceBundle getUploadProperties() {
		return uploadProperties;
	}
	
	public static String getConfigValue(String key) {
		return configProperties.getString(key);
	}
	
	public static String getConfigValue(String key, String defaultValue) {
		String value = PropertiesUtil.getConfigValue(key);
		if(value.trim().equals("")){
			value = defaultValue;
		}
		return value;
	}

	public static String getUploadValue(String key) {
		return uploadProperties.getString(key);
	}
	
	public static String getUploadValue(String key, String defaultValue) {
		String value = PropertiesUtil.getUploadValue(key);
		if(value.trim().equals("")){
			value = defaultValue;
		}
		return value;
	}
	
}
