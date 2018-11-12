package com.teqlip.database;

import java.sql.Connection;

public class DatabaseDeleteHelper {

	/**
	 * delete all relevant rows of record of a user. USE IT WISELY.
	 * @param userId
	 * @param con
	 * @return true if successfully deleted/no action, else false
	 */
	public static boolean deleteAUserHelper(int userID) {
		Connection con = DatabaseDriverHelper.connectOrCreateDataBase();
		int status = 1;
		// check if user exist
		if (DatabaseSelectHelper.getUserIdExist(userID)) {
			status = DatabaseDeleter.deleteAUser(userID, con);
			if (status < 0) {
				// something wrong when deleting from tables
				System.out.println("Status: " + status + " , delete not success");
				return false;
			} else {
				// delete successfully
				status = 0;
			}
		} else {
			// user not exist
			System.out.println("user not exist, no action");
		}
		return true;
	}

}
