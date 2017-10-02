package com.alisure.tool.core;

public class CoreReflect {
	/**
	 * ���ִ�и÷����ķ�����
	 * @return
	 */
	public static String[] getExceptionPlace(){
		String[] result = {Thread.currentThread().getStackTrace()[2].getClassName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(),
				""+Thread.currentThread().getStackTrace()[2].getLineNumber()};
		return result;
	}

	/**
	 * ���ִ�и÷���������
	 * @return
	 */
	public static String getClassName(){
		return Thread.currentThread().getStackTrace()[2].getClassName();
	}
	
	/**
	 * ���ִ�и÷����ķ�����
	 * @return
	 */
	public static String getMethodName(){
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	/**
	 * ���ִ�и÷������к�
	 * @return
	 */
	public static int getLineNumber(){
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
}
