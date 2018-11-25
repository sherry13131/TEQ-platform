package com.teqlip.database;

import com.teqlip.database.DatabaseInserter;
import com.teqlip.excel.ExcelBook;
import com.teqlip.excel.ExcelSheet;
import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseDriverHelper;
import com.teqlip.exceptions.DatabaseInsertException;

import Templates.TemplateEnum;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;


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
	   * @return the id of the query inserted, -1 if fail to insert
	   * @throws DatabaseInsertException if query is not unique
	   * @throws SQLException 
	   */
	  public static int insertNewQuery(String query, Connection con) 
			  throws DatabaseInsertException, SQLException {
		// this make sure the query are unique
		List<String> queries = DatabaseSelectHelper.getSavedQueries();
	    if (!queries.contains(query)) {
	      if(DatabaseInserter.insertQuery(query,con) == 1) {
	    	  return DatabaseSelector.getQueryID(query, con);
	      }
	      else {
	    	  return -1;
	      }
	    }
	    throw new DatabaseInsertException();
	  }
	  
	  public static boolean insertTemplateDataHelper(String filepath) throws SQLException, ParseException {
	    Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
	    try {
  	    con.setAutoCommit(false);
  	    ExcelBook excelBook = new ExcelBook(filepath);
        HashMap<String, ExcelSheet> sheets = excelBook.getSheetMap();
        ExcelSheet excelSheet;
  	    for(String sheetName : sheets.keySet()) {
  	      System.out.println(sheetName);
  	      for (TemplateEnum tenum : TemplateEnum.enumIteration()) {
  	        if (tenum.getString().equalsIgnoreCase(sheetName)) {
  	          excelSheet = excelBook.getSheetMap().get(tenum.getString());
  	          tenum.insertThisSheet(filepath, excelSheet, con);
  	          System.out.println("inserted sheet "+ sheetName);
  	          break;
  	        }
  	      }
          continue;
  	    }
  	    con.commit();
  	    return true;
      } catch (SQLException e) {
        try {
          con.rollback();
          System.out.println("Something wrong when inserting template data");
          System.out.println("Rolling back data...");
          e.printStackTrace();
          int count = 1;
          while (e != null) {
//            System.out.println("SQLException " + count);
//            System.out.println("Code: " + e.getErrorCode());
//            System.out.println("SqlState: " + e.getSQLState());
//            System.out.println("Error Message: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "upload icare data error", JOptionPane.ERROR_MESSAGE);
            e = e.getNextException();
            count++;
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      } finally {
        try {
          con.setAutoCommit(true);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
	    return false;
	  }
}
