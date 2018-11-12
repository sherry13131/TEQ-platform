package com.teqlip.api;

public class Logger {

	private static final String LOG_SPACING = "----------";
	
	private static final String INFO = "[INFO] ";
	private static final String ERROR = "[ERROR] ";
	
	public static void header(String header) {
		info(LOG_SPACING + " " + header + " " + LOG_SPACING);
	}
	
	public static void info(String infoMsg) {
		System.out.println(INFO + infoMsg);
	}
	
	public static void error(String errorMsg) {
		System.out.println(ERROR + errorMsg);
	}
}
