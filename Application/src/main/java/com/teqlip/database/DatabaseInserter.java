package com.teqlip.database;

import com.teqlip.exceptions.DatabaseInsertException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInserter {
  
  /**
   * Use this to insert new roles into the database.
   * @param role the new role to be added.
   * @param connection the database.
   * @return the id of the role that was inserted.
   * @throws DatabaseInsertException  on failure.
   */
  protected static int insertRole(String role, Connection connection) 
      throws DatabaseInsertException {
    String sql = "INSERT INTO ROLES(NAME) VALUES(?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql, 
                                              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1,role);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          return uniqueKey.getInt(1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new DatabaseInsertException();
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

/**
   * Insert a relationship between a user and a role.
   * @param userId the id of the user.
   * @param roleId the role id of the user.
   * @param connection the database connection.
   * @return the unique relationship id.
   * @throws DatabaseInsertException if there is a failure on the insert.
   */
  protected static int insertUserRole(int userId, int roleId, 
      Connection connection) throws DatabaseInsertException {
    String sql = "INSERT INTO USERROLE(USERID, ROLEID) VALUES (?, ?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql, 
                                              Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, userId);
      preparedStatement.setInt(2, roleId);
      int id = preparedStatement.executeUpdate();
      if (id > 0) {
        ResultSet uniqueKey = preparedStatement.getGeneratedKeys();
        if (uniqueKey.next()) {
          return uniqueKey.getInt(1);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    throw new DatabaseInsertException();
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
}
