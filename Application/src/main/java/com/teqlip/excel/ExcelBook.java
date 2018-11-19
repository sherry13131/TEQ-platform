package com.teqlip.excel;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Workbook;

import com.teqlip.api.Logger;


public class ExcelBook {

	private String filename;
	private HashMap<String, ExcelSheet> sheetMap = new HashMap<String, ExcelSheet>();

	public ExcelBook(String filepath) {
		File workbookFile = new File(filepath);
		this.filename = workbookFile.getName();
		
		initializeWorkbook(workbookFile);
	}
	
	public HashMap<String, ExcelSheet> getSheetMap() {
		return this.sheetMap;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	/* 
	 * If you have large file... please just print sheet by sheet...
	 */
	@Override
	public String toString() {
		String result = "";
		for (String key : sheetMap.keySet()) {
			result += "Sheet name: " + key + "\n";
			result += sheetMap.get(key) + "\n";
		}
		
		return result;
	}

	private void initializeWorkbook(File workbookFile) {
		Workbook workbook = FileHelper.openWorkbook(workbookFile);
		
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			ExcelSheet sheet = new ExcelSheet(workbook.getSheetAt(i));
			Logger.info("Adding sheet (" + sheet.getSheetName() + ") [" + i + " of " + workbook.getNumberOfSheets() + "]...");
			sheetMap.put(sheet.getSheetName(), sheet);
		}
		
		FileHelper.close(workbook);
	}
}
