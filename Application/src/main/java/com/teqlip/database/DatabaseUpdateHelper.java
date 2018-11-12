package com.teqlip.database;

import com.teqlip.database.DatabaseUpdater;
import com.teqlip.exceptions.ConnectionFailedException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUpdateHelper extends DatabaseUpdater {

	/**
	 * update a user's role
	 * @param roleId
	 * @param userId
	 * @return true if update successfully
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
	public static boolean updateUserRole(int roleId, int userId) throws SQLException, ConnectionFailedException {
		// in the case we aren't able to update the userRole possibly due to an invalid
		// roleId or userId.
		boolean complete = false;
		// check to see if the given roleID and userID both exist within the database.
		boolean validUserId = DataValidator.validateUserId(userId);
		boolean validRoleId = DataValidator.validateUserRole(roleId, DatabaseSelectHelper.getRoleIds());
		if (validUserId && validRoleId) {
			Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
			complete = DatabaseUpdater.updateUserRole(roleId, userId, con);
			con.close();
		}
		return complete;
	}
	
}
