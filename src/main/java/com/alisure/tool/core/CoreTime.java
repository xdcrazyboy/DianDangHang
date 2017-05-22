package com.alisure.tool.core;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间操作的核心类
 * @author ALISURE
 * @version 1507
 */
public class CoreTime {
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	
	private static String init(String value){
		simpleDateFormat.applyPattern(value);
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 输出时间,格式为：pattern
	 * @param pattern
	 * @return
	 */
	public static String getTimeByPattern(String pattern){
		return init(pattern);
	}
	
	/**
	 * 输出年月日时分秒,格式为: yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDataTime() {
		return init("yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 输出时分秒,格式为: HH:mm:ss
	 * @return
	 */
	public static String getTime() {
		return init("HH:mm:ss");
	}
	/**
	 * 输出年月日,格式为: yyyy-MM-dd
	 * @return
	 */
	public static String getData() {
		return init("yyyy-MM-dd");
	}
	/**
	 * 输出年份,格式为: yyyy
	 * @return
	 */
	public static String getYear() {
		return init("yyyy");
	}
	/**
	 * 输出月日,格式为: MM-dd
	 * @return
	 */
	public static String getMonthAndDay() {
		return init("MM-dd");
	}
	/**
	 * 输出月份,格式为: MM
	 * @return
	 */
	public static String getMonth() {
		return init("MM");
	}
	/**
	 * 输出日,格式为: dd
	 * @return
	 */
	public static String getDay() {
		return init("dd");
	}
	/**
	 * 输出时,格式为: hh
	 * @return
	 */
	public static String getHour() {
		return init("hh");
	}
	/**
	 * 输出分秒,格式为: mm:ss
	 * @return
	 */
	public static String getMinuteAndSecond() {
		return init("mm:ss");
	}
	/**
	 * 输出分,格式为: mm
	 * @return
	 */
	public static String getMinute() {
		return init("mm");
	}
	/**
	 * 输出秒,格式为: ss
	 * @return
	 */
	public static String getSecond() {
		return init("ss");
	}
	/**
	 * 输出时间戳,如:2014-08-16 23:23:30.787
	 * @return
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	/**
	 * 获取系统毫秒数
	 */
	public static long currentTimeMillis(){
		return System.currentTimeMillis();
	}
	/**
	 * 当前系统时间增加day天后的时间
	 * @param day
	 * @return
	 */
	public static String addDay(int day){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTimeInMillis(System.currentTimeMillis() + ((long) day) * 24 * 3600 * 1000);
		 return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}
}
