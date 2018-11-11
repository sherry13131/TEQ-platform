package com.teqlip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDeleter {

	/**
	 * delete all relevant rows of record of a user. USE IT WISELY.
	 * @param userId
	 * @param con
	 * @return 0 if successfully delete all records, 
	 * -1 if sth wrong in deleting from user_password, 
	 * -2 if sth wrong in deleting from user_login, 
	 * -3 if sth wrong in deleting from users
	 */
	public static int deleteAUser (int userId, Connection con) {
		int success = 0;
		PreparedStatement ps;
		// delete from user_password table
		String sql = "DELETE FROM user_password WHERE userID = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			success = -1;
			e.printStackTrace();
		}
		// delete from user_login
		sql = "DELETE FROM user_login WHERE userID = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			success = -2;
			e.printStackTrace();
		}
		sql = "DELETE FROM users WHERE userID = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			success = -3;
			e.printStackTrace();
		}
		return success;
	}
}
