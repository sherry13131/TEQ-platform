package com.teqlip.database;

import com.teqlip.exceptions.DatabaseInsertException;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInserter {
  
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
  protected static int insertNewUserAccount(String username, String password,
		  String firstName,String lastName, String middleName, String role,
		  String email, String phoneNumber, int active, 
		  Connection con) throws DatabaseInsertException, SQLException {
	// get -1 means error
	int roleID = DatabaseSelectHelper.getRoleId(role);
    int userID = insertUser(firstName, lastName, middleName, roleID, email, phoneNumber, con);
    insertUserLogin(userID, username, active, con);
    if (userID != -1) {
      insertPassword(password, userID, con);
      return userID;
    }
    throw new DatabaseInsertException();
  }
  
  private static int insertUserLogin(int userID, String username, int active, Connection con) {
	  String sql="INSERT INTO user_login(userID, username, active) VALUES (?,?,?)";
	  try {
		  PreparedStatement preparedStatement = con.prepareStatement(sql);
		  preparedStatement.setInt(1, userID);
		  preparedStatement.setString(2, username);
		  preparedStatement.setInt(3, active);
		  preparedStatement.executeUpdate();
		  return 0;
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	return -1;
	   
  }
  
  private static boolean insertPassword(String password, int userID, Connection con) {
    String sql = "INSERT INTO user_password(userID, password) VALUES(?,?);";
    try {
      password = PasswordHelper.passwordHash(password);
      PreparedStatement preparedStatement = con.prepareStatement(sql);
      preparedStatement.setInt(1, userID);
      preparedStatement.setString(2, password);
      preparedStatement.executeUpdate();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  private static int insertUser(String firstName,String lastName, String middleName, int roleID, String email, String phoneNumber,
		  Connection con) {
    String sql = "INSERT INTO users(userID, firstName, lastName, middleName, roleID, email, phoneNumber) VALUES(?,?,?,?,?,?,?);";
    try {
    	int userID = DatabaseSelectHelper.newUserId();
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userID);
		preparedStatement.setString(2, firstName);
		preparedStatement.setString(3, lastName);
		preparedStatement.setString(4, middleName);
		preparedStatement.setInt(5, roleID);
		preparedStatement.setString(6, email);
		preparedStatement.setString(7, phoneNumber);
		preparedStatement.executeUpdate();
		return userID;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return -1;
  }
  /**
   * Use this to insert a new template.
   * @param name of the template.
   * @param file path of the template.
   * @param connection the database connection.
   * @return -1 if fail 1 if inserted
   * @throws DatabaseInsertException if there is a failure on the insert
   * @throws SQLException 
   */
  public static int insertTemplate(String templateName,String templatePath, Connection con) {
	  String sql = "INSERT INTO Template(templateName, file) VALUES(?,?);";
	  try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, templateName);
			File file= new File(templatePath);
			FileInputStream inputStream= new FileInputStream(file);
			preparedStatement.setBinaryStream(2, inputStream, (int) file.length());
			preparedStatement.executeUpdate();
			return 1;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  return -1;
  }
  //test main function
 // public static void main(String[] args) {
	//  Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
	//  insertTemplate("iCare", "/Users/Sean/Desktop/iCARE_template.xlsx", con);
 // }
  
}
