package com.alisure.tool.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 
 * 用于计时
 * 
 * 缺点：时间花销大，需要处理
 * 
 * 示例一：
 * 		int key = CoreTimekeeping.start();
 * 		需要计时的代码
 * 		CoreTimekeeping.endAndPrintln(key);
 * 
 * 示例二：
 * 		String keyString = CoreTimekeeping.start("string");
 * 		需要计时的代码
 * 		CoreTimekeeping.endAndPrintln(keyString);
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreTimekeeping {
	
	private static Map<Integer, Long> mapInteger = new TreeMap<Integer, Long>();
	private static Map<String, Long> mapString = new TreeMap<String, Long>();
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取输出中的输出时间
	 * @return
	 */
	private static String getTime(){
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取下一个Key
	 * @return
	 */
	private static int nextInteger(){
		int next = new Random(System.currentTimeMillis()%100000).nextInt();
		next = next < 0 ? -next : next ;
		return mapInteger.containsKey(next) ? nextInteger() : next ;
	}
	
	/**
	 * 开始计时，返回获取计时结果的ID
	 * @return int
	 */
	public static int start(){
		int next = nextInteger();
		mapInteger.put(next, System.currentTimeMillis());
		return next;
	}
	
	/**
	 * 开始计时，返回获取计时结果的Value,并输出开始计时信息
	 * @return String
	 */
	public static int startAndPrint(){
		System.out.println(getTime() + " : 开始计时");
		return start();
	}
	
	/**
	 * 开始计时，返回获取计时结果的Value
	 * @return String
	 */
	public static String start(String value){
		mapString.put(value, System.currentTimeMillis());
		return value;
	}
	
	/**
	 * 开始计时，返回获取计时结果的Value,并输出开始计时信息
	 * @return String
	 */
	public static String startAndPrint(String value){
		System.out.println(getTime() + " : " + value + " 开始计时");
		return start(value);
	}
	
	private static long remove(int key) throws CoreTimekeepingException {
		Long endLong = mapInteger.remove(key);
		if (endLong == null) {
			throw new CoreTimekeepingException();
		}
		return endLong;
	}
	private static long remove(String key) throws CoreTimekeepingException {
		Long endLong = mapString.remove(key);
		if (endLong == null) {
			throw new CoreTimekeepingException();
		}
		return endLong;
	}
	
	/**
	 * 返回计算的时间差,格式: 1371143,单位: ms
	 * @param int
	 * @return long
	 */
	public static long end(int key) throws CoreTimekeepingException {
		return System.currentTimeMillis() - remove(key);
	}
	/**
	 * 返回计算的时间差,格式: 1371143,单位: ms
	 * @param String
	 * @return long
	 * @throws CoreTimekeepingException 
	 */
	public static long end(String key) throws CoreTimekeepingException {
		return System.currentTimeMillis() - remove(key);
	}
	
	/**
	 * 输出计算的时间差,格式：yyyy-MM-dd HH:mm:ss : 123 ms
	 * @param int
	 */
	public static void endAndPrintln(int key) throws CoreTimekeepingException {
		System.out.println(getTime()+" : "+end(key)+" ms");
	}
	
	/**
	 * @throws  
	 * 输出计算的时间差,格式：yyyy-MM-dd HH:mm:ss : 执行 value 共用   123 ms
	 * @param String
	 * @throws  
	 */
	public static void endAndPrintln(String key) throws CoreTimekeepingException {
		System.out.println(getTime() + " : 执行 " + key + " 共用  " + end(key) + " ms");
	}
}
