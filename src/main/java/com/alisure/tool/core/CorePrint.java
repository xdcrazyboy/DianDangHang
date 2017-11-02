package com.alisure.tool.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * �������
 *
 * @author ALISURE
 * @version 1507
 */
public class CorePrint {
	/**
	 * �����̨������ݣ������У�����ʱ��
	 * @param value
	 */
	public static void printlnTime(Object value){
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(new Date())+": "+(value == null ? "" : value).toString());
	}
	/**
	 * �����̨������ݣ�������
	 * @param value
	 */
	public static void print(Object value){
		System.out.print((value == null ? "" : value).toString());
	}

	/**
	 * �����̨�������,������
	 * @param value
	 */
	public static void println(Object value){
		System.out.println((value == null ? "" : value).toString());
	}
}
