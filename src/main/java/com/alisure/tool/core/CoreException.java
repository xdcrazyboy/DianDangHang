package com.alisure.tool.core;

/**
 * 
 * 用于处理异常信息
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreException {
	/**
	 * 打印异常
	 * @param e
	 */
	public static void printStackTrace(Exception e){
		System.err.println(
				 "*********************************************************\n"
				+"Error come place\n"
				+"--------------------------------------------");
		
		e.printStackTrace();
		
		System.err.println(
				 "--------------------------------------------\n"
				+"The author of this information is ALISURE\n"
				+"*********************************************************\n");
	}
	
	public static void printStackTrace(String string){
		System.err.println(
				 "*********************************************************\n"
				+"Error come place\n"
				+"--------------------------------------------");
		
		System.err.println(string);
		
		System.err.println(
				 "--------------------------------------------\n"
				+"The author of this information is ALISURE\n"
				+"*********************************************************\n");
	}
	
	/**
	 * 打印异常
	 * @param e
	 */
	public static void printStackTraceAndPlace(Exception e,String[] place){
		System.err.println("*********************************************************");
		System.err.println("Error come place");
		if (place.length == 3) {
			System.err.println("--------------------------------------------");
			System.err.println("className:  "+place[0]);
			System.err.println("methodName: "+place[1]);
			System.err.println("lineNumber: "+place[2]);
		}
		System.err.println("--------------------------------------------");
		e.printStackTrace();
		System.err.println("--------------------------------------------");
		System.err.println("The author of this information is ALISURE");
		System.err.println("*********************************************************");
	}
	
}
