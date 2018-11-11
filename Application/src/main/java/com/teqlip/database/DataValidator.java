package com.teqlip.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataValidator {

  /**
   * use to validate when new account is creating
   * @param username
   * @param password
   * @param firstName
   * @param lastName
   * @param role
   * @param email
   * @return true if success, else false
   * @throws SQLException
   */
	public static boolean createNewAccountValidation(String username, String password,
			  String firstName,String lastName, String role,
			  String email) throws SQLException {
		boolean valid = true;
		valid = !username.equals(null);
		valid = !password.equals(null);
		valid = !firstName.equals(null);
		valid = !lastName.equals(null);
		if (DatabaseSelectHelper.getRoleId(role) <= 0) {
			valid = false;
		}
		valid = !email.equals(null);
		return valid;
	}
	
	/**
	 * validate if userID > 0
	 * @param userID
	 * @return true if userID > 0
	 */
	public static boolean validateUserId(int userID) {
		boolean valid = true;
		if (userID <= 0) {
			valid = false;
		}
		else if (!DatabaseSelectHelper.getUserIdExist(userID)) {
			valid = false;
		}
		return valid;
	}
	
	/**
	 * validate user role exist in the current role list, and roleID > 0
	 * @param roleID
	 * @param ids
	 * @return true if role ID exist in the current role list 
	 */
	public static boolean validateUserRole(int roleID, List<Integer> ids) {
		boolean valid = true;
		if (roleID <= 0) {
			valid = false;
		} else {
			for (int id: ids) {
				if (id == roleID) {
					valid = true;
				}
			}
		}
		return valid;
	}

	/**
	 * validate the roleID if it is a valid roleID
	 * @param roleId
	 * @return true if roleID is a exist
	 */
	public static boolean getRoleExist(int roleId) {
		Connection con = DatabaseDriverHelper.connectDataBase();
		try {
			ResultSet ids = DatabaseSelector.getRoles(con);
			while (ids.next()) {
				if(ids.getInt("roleID") == roleId) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
