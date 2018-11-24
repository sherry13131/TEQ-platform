package com.teqlip.gui;

import java.sql.SQLException;
import java.text.ParseException;

import com.teqlip.database.DatabaseInsertHelper;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.EmailInterface;
import com.teqlip.email.type.ChangedPassEmail;
import com.teqlip.email.type.NoReplyEmail;
import com.teqlip.excel.ExcelBook;
import com.teqlip.gui.frames.*;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.gui.panels.teq.TEQMainMenuPanel;

public class MainFrame {

	public static void main(String[] args) throws SQLException, ParseException {

		runLoginMenu();

		// runMainMenu();

		// runEmail();
		
//		 runExcel();
	  
//	  tryAddFileData();
	}

	// These just make it easier to test
	private static void runLoginMenu() {
		LoginMenu login = new LoginMenu();
		login.packAndShow();
	}

	private static void runMainMenu() {
		AppFrame main = new AppFrame("Bob", "YEET");
		main.packAndShow();
		TEQMainMenuPanel body = new TEQMainMenuPanel(main);
		body.goToMenu(MenuOptions.MAIN_MENU);
	}
	
	private static void runEmail() {
		EmailInterface email = new NoReplyEmail("ay yo its me", "anthony.leung.canada@gmail.com", "waddup");
		EmailInterface email2 = new ChangedPassEmail("pat meheiny", "anthony.leung.canada@gmail.com");
		
		EmailHandler.addEmail(email);
		EmailHandler.addEmail(email2);
		EmailHandler.sendEmails();
	}
	
	private static void runExcel() {
		String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
		
		ExcelBook excelBook = new ExcelBook(filename);
		System.out.println(excelBook.getSheetMap().get("Employment"));
	}
	
	private static void tryAddFileData(String filepath) throws SQLException, ParseException {
	  // file default location: Application/
//	  String filename = "New_iCARE_Template_Comb_with_Examples.xlsx";
	  DatabaseInsertHelper.insertTemplateDataHelper(filepath);
	}
}
