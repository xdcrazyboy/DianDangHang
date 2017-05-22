package com.alisure.tool.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 
 * 用于联网，并获得内容
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreNetwork {

	public static final String Utf8 = "UTF-8";

	private String encoding = null;
	
	public CoreNetwork(String encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * 获得InputStream
	 * 
	 * @param url
	 * @return
	 */
	public InputStream getInputStream(String url) throws IOException{
		return new DataInputStream(new URL(url).openConnection().getInputStream());
	}
	
	/**
	 * 获取数据
	 * 
	 * @param inputStream
	 * @param encoding
	 * @return
	 */
	public String getResult(InputStream inputStream,String encoding) throws IOException{
		return result(inputStream, encoding);
	}

	/**
	 * 获取数据
	 * @param encoding
	 * @return
	 */
	public String getResult(String url,String encoding) throws IOException{
		return result(getInputStream(url), encoding);
	}
	
	/**
	 * 获取数据
	 * @param url
	 * @return
	 */
	public String getResult(String url) throws IOException{
		return result(getInputStream(url), encoding);
	}
	
	/**
	 * 获取数据
	 * @param inputStream
	 * @param encoding
	 * @return
	 */
	private String result(InputStream inputStream,String encoding) throws IOException{
		byte b[] = new byte[1024];
		StringBuilder value = new StringBuilder();
		int num = 0;
		while (( num = inputStream.read(b)) != -1) {
			value.append(new String(b,0,num,encoding));
		}
		inputStream.close();
		return value.toString();
	}
}
