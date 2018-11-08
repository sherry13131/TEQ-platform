package com.teqlip.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataValidator {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
