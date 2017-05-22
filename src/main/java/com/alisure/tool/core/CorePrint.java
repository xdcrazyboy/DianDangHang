package com.alisure.tool.core;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 用于输出
 * 
 * @author ALISURE
 * @version 1507
 */
public class CorePrint {
	/**
	 * 向控制台输出内容，并换行，附带时间
	 * @param value
	 */
	public static void printlnTime(Object value){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())+": "+(value == null ? "" : value).toString());
	}
	/**
	 * 向控制台输出内容，不换行
	 * @param value
	 */
	public static void print(Object value){
		System.out.print((value == null ? "" : value).toString());
	}
	
	/**
	 * 向控制台输出内容,并换行
	 * @param value
	 */
	public static void println(Object value){
		System.out.println((value == null ? "" : value).toString());
	}
}
