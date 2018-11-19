package com.teqlip.excel;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.teqlip.api.Logger;

public class FileHelper {

	/**
	 * To open excel workbooks. Remember to close the workbook after.
	 * 
	 * @param filepath
	 * @return
	 */
	public static Workbook openWorkbook(String filepath) {
		return openWorkbook(new File(filepath));
	}
	
	public static Workbook openWorkbook(File file) {
		FileInputStream inputStream = openFile(file);
		Workbook workbook = null;

		Logger.info("Attempting to open excel workbook...");
		try {
			workbook = new XSSFWorkbook(inputStream);
			inputStream.close();
		} catch (IOException e) {
			Logger.error("Workbook couldn't be opened!");
			e.printStackTrace();
		}

		Logger.info("Successfully opened workbook!");
		return workbook;
	}
	
	/**
	 * A helper for opening files in java and to handle exceptions. Make sure to
	 * close inputstream after.
	 * 
	 * @param filepath
	 * @return
	 */
	public static FileInputStream openFile(String filepath) {
		return openFile(new File(filepath));
	}

	public static FileInputStream openFile(File file) {
		FileInputStream inputStream = null;
		
		Logger.info("Attempting to find file (" + file.getName() + ")...");
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			Logger.error("File was not found!");
			Logger.error("File path is: " + file.getPath());
			e.printStackTrace();
		}

		Logger.info("Successfully found file!");
		return inputStream;
	}

	/**
	 * Attempts to close the given file.
	 * @param file
	 */
	public static void close(Closeable file) {
		Logger.info("Attempting to close file...");

		try {
			file.close();
		} catch (IOException e) {
			Logger.error("Unable to close file!");
			e.printStackTrace();
		}

		Logger.info("Successfully closed file!");
	}
}
