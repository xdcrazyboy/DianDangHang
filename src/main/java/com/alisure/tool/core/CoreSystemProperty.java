package com.alisure.tool.core;

/**
 * 
 * 用于获得系统属性
 * 
 * @author ALISURE
 * @version 1507
 * 
 */
public class CoreSystemProperty {
	/**
	 * # java.version	Java Runtime Environment version 
	 */
	public static final String Java_Version = "java.version";
	/**
	 * # java.vendor	Java Runtime Environment vendor 
	 */
	public static final String Java_Vendor = "java.vendor";
	/**
	 * # java.vendor.url	Java vendor URL
	 */
	public static final String Java_Vendor_Url = "java.vendor.url";
	/**
	 * # java.home	Java installation directory 
	 */
	public static final String Java_Home = "java.home";
	/**
	 * # java.vm.specification.version	Java Virtual Machine specification version
	 */
	public static final String Java_Vm_Specification_Version = "java.vm.specification.version";
	/**
	 * # java.vm.specification.vendor	Java Virtual Machine specification vendor 
	 */
	public static final String Java_Vm_Specification_Vendor = "java.vm.specification.vendor";
	/**
	 * # java.vm.specification.name	Java Virtual Machine specification name 
	 */
	public static final String Java_Vm_Specification_Name = "java.vm.specification.name";
	/**
	 * # java.vm.version	Java Virtual Machine implementation version 
	 */
	public static final String Java_Vm_Version = "java.vm.version";
	/**
	 * # java.vm.vendor	Java Virtual Machine implementation vendor 
	 */
	public static final String Java_Vm_Vendor = "java.vm.vendor";
	/**
	 * # java.vm.name	Java Virtual Machine implementation name 
	 */
	public static final String Java_Vm_Name = "java.vm.name";
	/**
	 * # java.specification.version	Java Runtime Environment specification version 
	 */
	public static final String Java_Specification_Version = "java.specification.version";
	/**
	 * # java.specification.vendor	Java Runtime Environment specification vendor 
	 */
	public static final String Java_Specification_Vendor = "java.specification.vendor";
	/**
	 * # java.specification.name	Java Runtime Environment specification name 
	 */
	public static final String Java_Specification_Name = "java.specification.name";
	/**
	 * # java.class.version	Java class format version number 
	 */
	public static final String Java_Class_Version = "java.class.version";
	/**
	 * # java.class.path	Java class path 
	 */
	public static final String Java_Class_Path = "java.class.path";
	/**
	 * # java.library.path	List of paths to search when loading libraries 
	 */
	public static final String Java_Library_Path = "java.library.path";
	/**
	 * # java.io.tmpdir	Default temp file path 
	 */
	public static final String Java_Io_Tmpdir = "java.io.tmpdir";
	/**
	 * # java.compiler	Name of JIT compiler to use
	 */
	public static final String Java_Compiler = "java.compiler";
	/**
	 * # java.ext.dirs	Path of extension directory or directories 
	 */
	public static final String Java_Ext_Dirs = "java.ext.dirs";
	/**
	 * # os.name	Operating system name 
	 */
	public static final String Os_Name = "os.name";
	/**
	 * # os.arch	Operating system architecture
	 */
	public static final String Os_Arch = "os.arch";
	/**
	 * # os.version	Operating system version
	 */
	public static final String Os_Version = "os.version";
	/**
	 * # file.separator	File separator ("/" on UNIX) 
	 */
	public static final String File_Separator = "file.separator";
	/**
	 * # path.separator	Path separator (":" on UNIX) 
	 */
	public static final String Path_Separator = "path.separator";
	/**
	 * # line.separator	Line separator ("\n" on UNIX) 
	 */
	public static final String Line_Separator = "line.separator";
	/**
	 * # user.name	User's account name
	 */
	public static final String User_Name = "user.name";
	/**
	 * # user.home	User's home directory 
	 */
	public static final String User_Home = "user.home";
	/**
	 * # user.dir	User's current working directory
	 */
	public static final String User_Dir = "user.dir";
	
	
	/**
	 * 项目所在路径
	 * @return
	 */
	public static String userDir(){
		return System.getProperty(User_Dir);
	}
	
	/**
	 * 路径分隔符
	 * @return
	 */
	public static String fileSeparator(){
		return System.getProperty(File_Separator);
	}
	
	/**
	 * 换行符
	 * @return
	 */
	public static String lineSeparator(){
		return System.getProperty(Line_Separator);
	}
	
	/**
	 * 获取系统属性
	 * @return
	 */
	public static String getSystemProperty(final String key){
		return System.getProperty(key);
	}
	
}
