package com.teqlip.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelSheet {

	private String name;
	private List<ExcelRow> sheetData = new ArrayList<ExcelRow>();
	
	public ExcelSheet(Sheet worksheet) {
		this.name = worksheet.getSheetName();
		
		initializeSheetData(worksheet);
	}
	
	public String getSheetName() {
		return this.name;
	}
	
	public List<ExcelRow> getRows() {
		return this.sheetData;
	}
	
	public int myGetSize() {
	  return this.getRows().size();
	}
	
	@Override
	public String toString() {
		String result = "";
		for (ExcelRow row : sheetData) {
			result += row.toString() + "\n";
		}
		
		return result;
	}
	
	private void initializeSheetData(Sheet worksheet) {
		Iterator<Row> rowIterator = worksheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			sheetData.add(new ExcelRow(row));
		}
	}
	
}
