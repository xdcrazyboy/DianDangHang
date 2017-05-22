package com.alisure.tool.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

/**
 * 
 * 对文件的操作,新建、删除、写入
 * 
 * @author ALISURE
 * @version 1507
 *
 */
public class CoreFile {
	/**
	 * 如果文件存在就把内容追加到后面
	 */
	public static final int File_Append_After = 1;
	/**
	 * 如果文件存在就把内容追加到前面
	 */
	public static final int File_Append_Before = 2;
	/**
	 * 如果文件存在就把内容覆盖
	 */
	public static final int File_Cover_If_Have = 3;
	/**
	 * 如果文件存在就把内容舍弃
	 */
	public static final int File_Abandon_If_Have_File = 4;
	/**
	 * 如果文件不存在就把内容舍弃
	 */
	public static final int File_Abandon_If_No_File = 5;
	
	/**
	 * 写入文件
	 * @param pathAndName
	 * @param content
	 * @param method
	 */
	public static void write(String pathAndName, String content, int method) throws Exception{
		switch (method) {
		case File_Append_After:
			appendFile(pathAndName, content);
			break;
		case File_Append_Before:
			beforeFile(pathAndName, content);
			break;
		case File_Cover_If_Have:
			newFile(pathAndName, content);
			break;
		case File_Abandon_If_Have_File:
			newFile(pathAndName, content);
			break;
		case File_Abandon_If_No_File:
			if (new File(pathAndName).exists()) appendFile(pathAndName, content);
			break;
		default:
			break;
		}
	}

	/**
	 * 向文件中追加内容
	 * 
	 * @param filePathAndName
	 * @param content
	 */
	public static void beforeFile(String filePathAndName, String content)
			throws Exception {
		String filePath = filePathAndName.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) myFilePath.createNewFile();

		// 创建临时文件
		String temp = System.getProperty("user.dir")
				+ System.getProperty("file.separator")
				+ System.currentTimeMillis() + ".txt";
		// 将要写入内容写入到临时文件
		FileWriter fileWriter = new FileWriter(temp, true);
		fileWriter.write(content + System.getProperty("line.separator"));
		// 读取原文件内容到临时文件
		FileReader fileReader = new FileReader(filePathAndName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String tempString = "";
		while ((tempString = bufferedReader.readLine()) != null) fileWriter.write(tempString);

		bufferedReader.close();
		fileReader.close();
		fileWriter.close();
		// 复制文件
		fileChannelCopy(new File(temp), myFilePath);
		// 删除临时文件
		delFile(temp);
	}

	/**
	 * 文件通道方式复制文件
	 * 
	 * @param inFile
	 * @param outFile
	 */
	public static void fileChannelCopy(File inFile, File outFile)
			throws Exception {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;

		fileInputStream = new FileInputStream(inFile);
		fileOutputStream = new FileOutputStream(outFile);
		inFileChannel = fileInputStream.getChannel();
		outFileChannel = fileOutputStream.getChannel();
		// 连接两个通道，并且从in通道读取，然后写入out通道
		inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);

		outFileChannel.close();
		inFileChannel.close();
		fileOutputStream.close();
		fileInputStream.close();
	}

	/**
	 * 向文件中追加内容
	 * 
	 * @param filePathAndName
	 * @param content
	 */
	public static void appendFile(String filePathAndName, String content)
			throws Exception {
		File myFilePath = new File(filePathAndName);
		if (!myFilePath.exists()) myFilePath.createNewFile();

		FileWriter fileWriter = new FileWriter(myFilePath, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(content);
		bufferedWriter.flush();

		bufferedWriter.close();
		fileWriter.close();
	}

	/**
	 * 向文件中追加内容
	 * 
	 * @param filePathAndName
	 * @param content
	 */
	public static void appendFile2(String filePathAndName, String content)
			throws Exception {
		String filePath = filePathAndName.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) myFilePath.createNewFile();

		FileWriter fileWriter = new FileWriter(myFilePath, true);
		fileWriter.write(content);
		fileWriter.close();
	}

	/**
	 * 向文件中追加内容
	 * 
	 * @param filePathAndName
	 * @param content
	 */
	public static void appendFile(String filePathAndName, byte[] content)
			throws Exception {
		String filePath = filePathAndName.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) myFilePath.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(myFilePath,true);
		fileOutputStream.write(content);
		fileOutputStream.close();
	}

	/**
	 * 新建文件,并把内容存到文件中
	 * 
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void newFile(String filePathAndName, byte[] fileContent)
			throws Exception {
		String filePath = filePathAndName.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) myFilePath.createNewFile();

		FileOutputStream out = new FileOutputStream(filePathAndName);
		out.write(fileContent);
		out.flush();
		out.close();
	}

	/**
	 * 新建文件,如果不存在则新建并存入信息，如果存在则结束
	 * 
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void newFile(String filePathAndName, String fileContent)
			throws Exception {
		String filePath = filePathAndName.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(filePathAndName);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream,"UTF-8");
			out.write(fileContent);
			out.flush();
			out.close();
			fileOutputStream.close();
		}
	}

	/**
	 * 新建文件,如果不存在则新建，如果存在则结束
	 * 
	 * @param filePathAndName
	 */
	public static void newFile(String filePathAndName) throws Exception {
		File myFilePath = new File(filePathAndName);
		if (!myFilePath.exists()) myFilePath.createNewFile();
	}

	/**
	 * 新建文件夹,如果不存在则新建，如果存在则结束
	 * 
	 * @param folderPath
	 */
	public static void newFolder(String folderPath) throws Exception {
		File myFilePath = new File(folderPath);
		if (!myFilePath.exists()) myFilePath.mkdir();
	}

	/**
	 * 新建文件夹，父路径可以不存在
	 * 
	 * 需要优化算法
	 * @param path
	 * @return
	 */
	public static boolean newFullPath(String path) {
		if (path.contains(".")) return false;
		File file = new File(path);
		while (!file.exists()) {
			if (file.getParentFile().exists()) file.mkdir();
			else newFullPath(file.getParent());
		}
		return true;
	}

	/**
	 * 文件是否存在
	 * 
	 * @param pathOrFileName
	 * @return
	 */
	public static boolean isExists(String pathOrFileName) {
		return new File(pathOrFileName).exists();
	}

	/**
	 * 新建文件，父路径可以不存在
	 * 
	 * @param pathAndFileName
	 * @return
	 */
	public static boolean newFullPathAndFileName(String pathAndFileName) throws IOException{
		if (!pathAndFileName.contains(".")) return false;
		if (!pathAndFileName.contains(":")) pathAndFileName = System.getProperty("user.dir") + System.getProperty("file.separator") + pathAndFileName;
		
		File file = new File(pathAndFileName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) newFullPath(file.getParent());
			
			return file.createNewFile();
		}
		return false;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) throws Exception {
		File myDelFile = new File(filePathAndName);
		if (!myDelFile.isDirectory()) myDelFile.delete();
	}

	/**
	 * 删除文件并返回是否成功
	 * 
	 * @param filePathAndName
	 */
	public static boolean delFileAndReturn(String filePathAndName)
			throws Exception {
		File myDelFile = new File(filePathAndName);
		if (!myDelFile.isDirectory()) return myDelFile.delete();
		return false;
	}

	/**
	 * 删除文件夹及其中所有文件
	 * 
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) throws Exception {
		delAllFile(folderPath); // 删除完里面所有内容
		new File(folderPath.toString()).delete(); // 删除空文件夹
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 */
	public static void delAllFile(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) return;
		if (!file.isDirectory()) return;
		
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			temp = path.endsWith(File.separator) ? new File(path + tempList[i]) : new File(path + File.separator + tempList[i]);
			
			if (temp.isFile()) temp.delete();
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 删除文件夹里面的所有文件 ,包括文件夹
	 * 
	 * @param path
	 */
	public static void delAllFileD(String path) throws Exception {
		File file = new File(path);
		
		if (!file.exists()) return;
		if (!file.isDirectory()) return;
		
		String[] fileList = file.list();
		for (int i = 0; i < fileList.length; i++) delFile(path + fileList[i]);
	}

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readFromFile(String fileName)
			throws FileNotFoundException, IOException {
		StringBuilder stringBuilder = new StringBuilder();

		File file = new File(fileName);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String temp = null;
		while ((temp = bufferedReader.readLine()) != null) stringBuilder.append(temp + System.getProperty("line.separator"));
		
		fileReader.close();
		bufferedReader.close();

		return stringBuilder.toString();
	}
}
