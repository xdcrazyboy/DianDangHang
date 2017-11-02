package com.alisure.tool.core;

import java.net.URLEncoder;

/**
 * 
 * ���ڹ����ַ����Ĳ���
 *
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreString {

	/**
	 * ���ߺ������ж��ַ����Ƿ�Ϊ��
	 * @param input
	 * @return
	 */
	public static boolean isNull(String input){
		return (input == null || "".equals(input))?true:false;
	}
	/**
	 * ���ߺ������ж�JSON�Ƿ�Ϊ��
	 * @param input
	 * @return
	 */
	public static boolean isNullJSON(String input){
		return (!input.startsWith("{")||!input.endsWith("}"))?true:false;
	}
	/**
	 * ���ߺ�������nullת����""
	 * @param input
	 * @return
	 */
	public static String nullTrans(String input){
		return input == null ? "" : input ;
	}
	/**
	 * ���ߺ���,�����ַ����еĿ�ֵ,����һ������Ϊ��,�滻Ϊ�ڶ�������
	 * @param v
	 * @param toV
	 * @return
	 */
	public static String nullToString(String v, String toV) {
		return (v == null || "".equals(v)) ? toV :v ;
	}
	/**
	 * ���ߺ���,����ת��:ISO8859_1 to utf-8
	 * @param strvalue
	 * @return
	 */
	public static String toChinese(String strvalue) throws Exception {
		return strvalue == null ? "" : new String(strvalue.getBytes("ISO8859_1"), "utf-8").trim();
	}
	/**
	 * ���ߺ���,ȥ��ǰ��ո�
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return str.trim();
	}
	/**
	 * ���ߺ���,����Σ���ַ�
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
	 * ���ߺ���,����Σ���ַ�
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
	 * ���ߺ���,ת��Σ���ַ�
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
	 * ���ߺ���,ת��Σ���ַ�
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
	 * ��Σ���ַ�����Ϊ��
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
	 * ת���ַ���Ϊint���쳣����0
	 * @param str
	 * @return int
	 */
	public static int toInteger(String str) throws NumberFormatException {
		return Integer.parseInt(str);
	}

	public static String URLEncoder_encode(String url, String encoding){
		try {
			return URLEncoder.encode(url, encoding);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
