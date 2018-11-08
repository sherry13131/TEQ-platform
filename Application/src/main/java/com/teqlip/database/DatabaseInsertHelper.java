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
			userID = DatabaseInserter.insertNewUserAccount(username, password, firstName, lastName, middleName, role, email, phoneNumber, active, con);
		}
		return userID;
	}
}
