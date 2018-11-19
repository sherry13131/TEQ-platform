package com.teqlip.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelRow {

	private List<Object> rowData = new ArrayList<Object>();
	
	public ExcelRow(Row row) {
		initializeRowData(row);
	}
	
	public List<Object> getCells() {
		return this.rowData;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Object cell : rowData) {
			result += cell.toString() + " - ";
		}
		
		return result;
	}
	
	private void initializeRowData(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		
		while(cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			rowData.add(getCellValue(cell));
		}
	}
	
	protected Object getCellValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
			case STRING:
				return cell.getStringCellValue();
			case BOOLEAN:
				return cell.getBooleanCellValue();
			case NUMERIC:
				return cell.getNumericCellValue();
			case BLANK:
				return "";
			default:
				return null;
		}
	}
}
