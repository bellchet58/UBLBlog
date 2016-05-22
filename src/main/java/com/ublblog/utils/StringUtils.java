package com.ublblog.utils;

public class StringUtils {
	/**
	 * 获取文件后缀名
	 * 
	 * @param inputString
	 * @return
	 */
	public static String getSuffix(String inputString)
	{
		if(!inputString.contains("."))
			return "ERROR";
		int pointIndex = inputString.lastIndexOf(".");
		String suffix = inputString.substring(pointIndex+1);
		return suffix;
	}
	
	
}
