package com.alisure.tool.core;

public class CoreReflect {
	/**
	 * 获得执行该方法的方法名
	 * @return
	 */
	public static String[] getExceptionPlace(){
		String[] result = {Thread.currentThread().getStackTrace()[2].getClassName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(),
				""+Thread.currentThread().getStackTrace()[2].getLineNumber()};
		return result;
	}

	/**
	 * 获得执行该方法的类名
	 * @return
	 */
	public static String getClassName(){
		return Thread.currentThread().getStackTrace()[2].getClassName();
	}
	
	/**
	 * 获得执行该方法的方法名
	 * @return
	 */
	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	/**
	 * 获得执行该方法的行号
	 * @return
	 */
	public static int getLineNumber(){
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
}
