package com.alisure.tool.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 
 * ���ڼ�ʱ
 * 
 * ȱ�㣺ʱ�仨������Ҫ����
 * 
 * ʾ��һ��
 * 		int key = CoreTimekeeping.start();
 * 		��Ҫ��ʱ�Ĵ���
 * 		CoreTimekeeping.endAndPrintln(key);
 * 
 * ʾ������
 * 		String keyString = CoreTimekeeping.start("string");
 * 		��Ҫ��ʱ�Ĵ���
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
	 * ��ȡ����е����ʱ��
	 * @return
	 */
	private static String getTime(){
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * ��ȡ��һ��Key
	 * @return
	 */
	private static int nextInteger(){
		int next = new Random(System.currentTimeMillis()%100000).nextInt();
		next = next < 0 ? -next : next ;
		return mapInteger.containsKey(next) ? nextInteger() : next ;
	}
	
	/**
	 * ��ʼ��ʱ�����ػ�ȡ��ʱ�����ID
	 * @return int
	 */
	public static int start(){
		int next = nextInteger();
		mapInteger.put(next, System.currentTimeMillis());
		return next;
	}
	
	/**
	 * ��ʼ��ʱ�����ػ�ȡ��ʱ�����Value,�������ʼ��ʱ��Ϣ
	 * @return String
	 */
	public static int startAndPrint(){
		System.out.println(getTime() + " : ��ʼ��ʱ");
		return start();
	}
	
	/**
	 * ��ʼ��ʱ�����ػ�ȡ��ʱ�����Value
	 * @return String
	 */
	public static String start(String value){
		mapString.put(value, System.currentTimeMillis());
		return value;
	}
	
	/**
	 * ��ʼ��ʱ�����ػ�ȡ��ʱ�����Value,�������ʼ��ʱ��Ϣ
	 * @return String
	 */
	public static String startAndPrint(String value){
		System.out.println(getTime() + " : " + value + " ��ʼ��ʱ");
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
	 * ���ؼ����ʱ���,��ʽ: 1371143,��λ: ms
	 * @param int
	 * @return long
	 */
	public static long end(int key) throws CoreTimekeepingException {
		return System.currentTimeMillis() - remove(key);
	}
	/**
	 * ���ؼ����ʱ���,��ʽ: 1371143,��λ: ms
	 * @param String
	 * @return long
	 * @throws CoreTimekeepingException 
	 */
	public static long end(String key) throws CoreTimekeepingException {
		return System.currentTimeMillis() - remove(key);
	}
	
	/**
	 * ��������ʱ���,��ʽ��yyyy-MM-dd HH:mm:ss : 123 ms
	 * @param int
	 */
	public static void endAndPrintln(int key) throws CoreTimekeepingException {
		System.out.println(getTime()+" : "+end(key)+" ms");
	}
	
	/**
	 * @throws  
	 * ��������ʱ���,��ʽ��yyyy-MM-dd HH:mm:ss : ִ�� value ����   123 ms
	 * @param String
	 * @throws  
	 */
	public static void endAndPrintln(String key) throws CoreTimekeepingException {
		System.out.println(getTime() + " : ִ�� " + key + " ����  " + end(key) + " ms");
	}
}
