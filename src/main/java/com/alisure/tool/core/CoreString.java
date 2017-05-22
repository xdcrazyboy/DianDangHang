package com.alisure.tool.core;

/**
 * 
 * 用于关于字符串的操作
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreString {
	
	/**
	 * 工具函数，判断字符串是否为空
	 * @param input
	 * @return
	 */
	public static boolean isNull(String input){
		return (input == null || "".equals(input))?true:false;
	}
	/**
	 * 工具函数，判断JSON是否为空
	 * @param input
	 * @return
	 */
	public static boolean isNullJSON(String input){
		return (!input.startsWith("{")||!input.endsWith("}"))?true:false;
	}
	/**
	 * 工具函数，将null转换成""
	 * @param input
	 * @return
	 */
	public static String nullTrans(String input){
		return input == null ? "" : input ;
	}
	/**
	 * 工具函数,处理字符串中的空值,若第一个参数为空,替换为第二个参数
	 * @param v
	 * @param toV
	 * @return
	 */
	public static String nullToString(String v, String toV) {
		return (v == null || "".equals(v)) ? toV :v ;
	}
	/**
	 * 工具函数,汉字转码:ISO8859_1 to utf-8
	 * @param strvalue
	 * @return
	 */
	public static String toChinese(String strvalue) throws Exception {
		return strvalue == null ? "" : new String(strvalue.getBytes("ISO8859_1"), "utf-8").trim();
	}
	/**
	 * 工具函数,去掉前后空格
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str.trim();
	}
	/**
	 * 工具函数,过滤危险字符
	 * &  -->  &amp;
	 * <  -->  &lt;
	 * >  -->  &gt;
	 * '  -->  null
	 * -- -->  null
	 * /  -->  null
	 * %  -->  null
	 * @param str
	 * @return
	 */
	public static String filterStr(String str) {
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;").replaceAll("'", " ")
				  .replaceAll("--", " ").replaceAll("/", " ")
				  .replaceAll("%", " ");
	}
	
	/**
	 * 工具函数,过滤危险字符
	 * &  -->  &alisure1;
	 * <  -->  &alisure2;
	 * >  -->  &alisure3;
	 * '  -->  &alisure4;
	 * -- -->  &alisure5;
	 * /  -->  &alisure6;
	 * %  -->  &alisure7;
	 * @param str
	 * @return
	 */
	public static String filterStrAlisure(String str) {
		return str.replaceAll("&", "&alisure1;").replaceAll("<", "&alisure2;")
				  .replaceAll(">", "&alisure3;").replaceAll("'", "&alisure4;")
				  .replaceAll("--", "&alisure5;").replaceAll("/", "&alisure6;")
				  .replaceAll("%", "&alisure7;");
	}
	
	/**
	 * 工具函数,转换危险字符
	 * &amp; -->  &
	 * &lt;  -->  <
	 * &gt;  -->  >
	 * @param str
	 * @return
	 */
	public static String toFilter(String str) {
		return str.replaceAll("&amp;", "&").replaceAll("&lt;", "<")
				  .replaceAll("&gt;", ">");
	}
	
	/**
	 * 工具函数,转换危险字符
	 * &alisure1;  -->  &
	 * &alisure2;  -->  <
	 * &alisure3;  -->  >
	 * &alisure4;  -->  '
	 * &alisure5;  -->  --
	 * &alisure6;  -->  /
	 * &alisure7;  -->  %
	 * @param str
	 * @return
	 */
	public static String toFilterAlisure(String str) {
		return str.replaceAll("&alisure1;", "&").replaceAll("&alisure2;", "<")
				  .replaceAll("&alisure3;", ">").replaceAll("&alisure4;", "'")
				  .replaceAll("&alisure5;", "--").replaceAll("&alisure6;", "/")
				  .replaceAll("&alisure7;", "%");
	}
	
	/**
	 * 将危险字符过滤为空
	 * @param str
	 * @return
	 */
	public static String filterStrAllNull(String str) {
		return str.replaceAll(";", "").replaceAll("&", "")
					.replaceAll("<", "").replaceAll(">", "")
					.replaceAll("'", "").replaceAll("--", "")
					.replaceAll("/", "").replaceAll("%", "");
	}
	
	/**
	 * 转换字符串为int，异常返回0
	 * @param str
	 * @return int
	 */
	public static int toInteger(String str) throws NumberFormatException {
		return Integer.parseInt(str);
	}
}
