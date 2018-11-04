package com.teqlip.database;

import java.sql.SQLException;

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
}
