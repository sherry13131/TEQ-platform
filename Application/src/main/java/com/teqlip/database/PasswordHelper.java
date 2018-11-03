package com.teqlip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordHelper {
	
	public static boolean checkPassword(int userID, String password) throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		Statement stmt = con.createStatement();
		String sql="select COUNT(*) found from user_password where password = sha2(sha('"+ password + "'),384) and userID=" + userID ;
		ResultSet rs=stmt.executeQuery(sql);
		while (rs.next()) {
			// check password in database with the input password
			if (Integer.parseInt(rs.getString("found")) == 1) {
				return true;
			} else {
				// throw exception
				System.out.println("password incorrect");
			}
		}
		return false;
	}
}
