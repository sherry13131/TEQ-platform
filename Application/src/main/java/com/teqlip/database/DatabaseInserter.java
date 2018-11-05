package com.teqlip.database;

import com.teqlip.exceptions.DatabaseInsertException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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
   * upload a template in database table `template` with a given nickname for identification
   * @param nickname of the file to be stored in db
   * @param filename the name of the file that is going to be uploaded
   * @param con connection
   * @return 0 if successfully stored
   * @throws FileNotFoundException
   */
  public static int insertTemplate(String nickname, String filename, Connection con) throws FileNotFoundException {
	  String sql = "INSERT INTO template (nickname, data) VALUES (?,?);";
	  try {
		  File file = new File(filename);
		  FileInputStream input = new FileInputStream(file);
		  
		  PreparedStatement preparedStatement = con.prepareStatement(sql);
		  preparedStatement.setString(1, nickname);
		  preparedStatement.setBinaryStream(2, input, (int) file.length());
          
		  preparedStatement.executeUpdate();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
	return 0;
  }

}
