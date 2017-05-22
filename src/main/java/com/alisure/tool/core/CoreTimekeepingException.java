package com.alisure.tool.core;

public class CoreTimekeepingException extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		System.out.println("*********************************************************");
		System.out.println("The key of the timekeeping maybe invalid");
		System.out.println("--------------------------------------------");
		super.printStackTrace();
		System.out.println("--------------------------------------------");
		System.out.println("The author of this information is ALISURE");
		System.out.println("*********************************************************");
	}

	
}
