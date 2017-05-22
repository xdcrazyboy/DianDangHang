package com.alisure.tool.core;

import java.util.Random;

/**
 * 关于数字的操作
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreNumber {
	
	/**
	 * 生成一个随机数,0 <= x < max
	 * @param max
	 * @return
	 */
	public static int randomInteger(int max){
		return max <= 0 ? 0 : new Random().nextInt(max);
	}
	
	/**
	 * 生成一个随机数,0 <= x < max
	 * @param max
	 * @param seed
	 * @return
	 */
	public static int randomInteger(int max,long seed){
		return max <= 0 ? 0 : new Random(seed).nextInt(max);
	}
	
	/**
	 * 生成一个随机数,left <= x < right
	 * @param left
	 * @param right
	 * @return
	 */
	public static int randomInteger(int left, int right){
		return ( left < right && left > 0 ) ? (new Random().nextInt(right - left) + left) : 0;
	}
	
	/**
	 * 生成一个随机数,left <= x < right
	 * @param left
	 * @param right
	 * @param seed
	 * @return
	 */
	public static int randomInteger(int left, int right,long seed){
		return ( left < right && left > 0 ) ? (new Random(seed).nextInt(right - left) + left) : 0;
	}
	
}
