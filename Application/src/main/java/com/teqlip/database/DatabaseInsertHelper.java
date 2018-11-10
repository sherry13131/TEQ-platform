package com.teqlip.database;

import com.teqlip.database.DatabaseInserter;
import com.teqlip.database.DatabaseDriverHelper;
import com.teqlip.exceptions.DatabaseInsertException;

import java.sql.Connection;
import java.sql.SQLException;

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
			  String firstName,String lastName, String middleName, String role,
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
			  String firstName,String lastName, String middleName, String role,
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
}
