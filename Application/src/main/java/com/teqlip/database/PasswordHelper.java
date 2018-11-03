package com.teqlip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordHelper {
	
	/**
	 * check the login password of a user
	 * @param userID
	 * @param password
	 * @return true if password matched
	 * @throws SQLException
	 */
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
	
	/** 
	 * get hashed password of a user by userID
	 * @param userId
	 * @return the Hashed password of that user
	 */
	public static String getPassword(int userId) {
		Connection con = DatabaseDriverHelper.connectDataBase();
		String password = "";
		try {
			password = DatabaseSelector.getPassword(userId, con);
			con.close();
		} catch (SQLException e) {
		}
		return password;
	}
	
}
