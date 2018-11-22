package com.teqlip.database;

import com.teqlip.database.DatabaseInserter;
import com.teqlip.excel.ExcelBook;
import com.teqlip.excel.ExcelRow;
import com.teqlip.excel.ExcelSheet;
import com.teqlip.excel.FileHelper;
import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseDriverHelper;
import com.teqlip.exceptions.DatabaseInsertException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class DatabaseInsertHelper extends DatabaseInserter {
	
	/**
	 * insert a new user login account info
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param middleName can be null
	 * @param role
	 * @param email
	 * @param phoneNumber can be null
	 * @param active
	 * @return userID that newly created
	 * @throws DatabaseInsertException
	 * @throws SQLException
	 */
	public static int createNewUserAccount(String username, String password,
			  String firstName,String lastName, String middleName, RoleEnum role,
			  String email, String phoneNumber, int active) throws DatabaseInsertException, SQLException {
		Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
		int userID = -1;
		boolean valid = DataValidator.createNewAccountValidation(username, password, firstName, lastName, role, email);
		if (valid) {
			userID = DatabaseInsertHelper.insertNewUserAccount(username, password, firstName, lastName, middleName, role, email, phoneNumber, active, con);
		}
		return userID;
	}

	/**
	   * Use this to insert a new user.
	   * @param name the user's name.
	   * @param age the user's age.
	   * @param address the user's address.
	   * @param password the user's password (not hashsed).
	   * @param connection the database connection.
	   * @return the user id
	   * @throws DatabaseInsertException if there is a failure on the insert
	   * @throws SQLException 
	   */
	  public static int insertNewUserAccount(String username, String password,
			  String firstName,String lastName, String middleName, RoleEnum role,
			  String email, String phoneNumber, int active, 
			  Connection con) throws DatabaseInsertException, SQLException {
		// get -1 means error
		int roleID = DatabaseSelectHelper.getRoleId(role);
	    int userID = DatabaseInserter.insertUser(firstName, lastName, middleName, roleID, email, phoneNumber, con);
	    DatabaseInserter.insertUserLogin(userID, username, active, con);
	    if (userID != -1) {
	    	DatabaseInserter.insertPassword(password, userID, con);
	      return userID;
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  /**
	   * Use this to insert a new template and its file.
	   * @param name of template.
	   * @param path to xslx file.
	   * @param connection the database connection.
	   * @return 1 if insert suceeded, -1 is failed, 
	   * @throws DatabaseInsertException if name is not unique
	   * @throws SQLException 
	   */
	  public static int insertNewTemplate(String templateName, String templatePath,
			  Connection con) throws DatabaseInsertException, SQLException {
		// this make sure the template names are unique
		List<String> templateNames = DatabaseSelectHelper.getTemplatesName();
	    if (!templateNames.contains(templateName)) {
	      return DatabaseInserter.insertTemplate(templateName, templatePath, con);
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  /**
	   * Use this to insert a new query.
	   * @param query.
	   * @param connection the database connection.
	   * @return 1 if insert suceeded, -1 is failed, 
	   * @throws DatabaseInsertException if query is not unique
	   * @throws SQLException 
	   */
	  public static int insertNewQuery(String query, Connection con) 
			  throws DatabaseInsertException, SQLException {
		// this make sure the query are unique
		List<String> queries = DatabaseSelectHelper.getSavedQueries();
	    if (!queries.contains(query)) {
	      return DatabaseInserter.insertQuery(query,con);
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  public static void insertTemplateDataHelper(String filename) throws SQLException, ParseException {
	    Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
	    ExcelBook excelBook = new ExcelBook(filename);
      HashMap<String, ExcelSheet> sheets = excelBook.getSheetMap();
      ExcelSheet excelSheet;
	    for(String sheetName : sheets.keySet()) {
	      System.out.println(sheetName);
	      if (sheetName.equalsIgnoreCase("LT Client Exit")) {
	        excelSheet = excelBook.getSheetMap().get("LT Client Exit");
	        DatabaseTemplateDataInserter.insertLTClientExit(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("Client Profile")) {
	        excelSheet = excelBook.getSheetMap().get("Client Profile");
	        DatabaseTemplateDataInserter.insertClientProfile(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("Needs Assessment&Referrals")) {
	        excelSheet = excelBook.getSheetMap().get("Needs Assessment&Referrals");
	        DatabaseTemplateDataInserter.insertNeedsAssessmentReferrals(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("Community Connections")) {
	        excelSheet = excelBook.getSheetMap().get("Community Connections");
	        DatabaseTemplateDataInserter.insertCommunityConnections(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("Info&Orien")) {
	        excelSheet = excelBook.getSheetMap().get("Info&Orien");
	        DatabaseTemplateDataInserter.insertInfoAndOrien(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("Employment")) {
	        excelSheet = excelBook.getSheetMap().get("Employment");
	        DatabaseTemplateDataInserter.insertEmploymentTemplateData(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("LT Client Enrol")) {
	        excelSheet = excelBook.getSheetMap().get("LT Client Enrol");
	        DatabaseTemplateDataInserter.insertLTClientEnroll(filename, excelSheet, con);
	      } else if (sheetName.equalsIgnoreCase("LT Course Setup")) {
	        excelSheet = excelBook.getSheetMap().get("LT Course Setup");
	        DatabaseTemplateDataInserter.insertLTCourseSetup(filename, excelSheet, con);
	      } else {
	        System.out.println("Skip this sheet since we don't need it");
	        continue;
	      }
	    }
	    System.out.println("Done inserting excel sheets data");
	  }
}
