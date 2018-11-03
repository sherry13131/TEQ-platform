package com.teqlip.database;

import com.teqlip.database.DatabaseSelector;
import com.teqlip.exceptions.ConnectionFailedException;
//import com.teqlip.database.ValidIdHelperFunctions;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseSelectHelper extends DatabaseSelector {
	
	/**
	 * get a list of roleID
	 * @return list of all roleIDs
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
	public static List<Integer> getRoleIds() throws SQLException, ConnectionFailedException {
		
		Connection con = DatabaseDriverHelper.connectDataBase();
		// get the roles from the database
		ResultSet results = DatabaseSelector.getRoles(con);
		// create a new List to add all the Roles into
		List<Integer> ids = new ArrayList<Integer>();
		// checks to see if there is another row with the column to add another ID.
		// moves to the next row if it exists.
		while (results.next()) {
			// add the current row's ID
			ids.add(results.getInt("roleID"));
		}
		results.close();
		con.close();
		return ids;
	}
	
	/**
	 * get roleID of a given role name
	 * @param role
	 * @return roleID
	 * @throws SQLException
	 */
	public static int getRoleId(String role) throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		ResultSet results = DatabaseSelector.getRoles(con);
		int roleID = -1;
		while (results.next()) {
			if (results.getString("role").equalsIgnoreCase(role)) {
				// add the current row's ID
				roleID = results.getInt("roleID");
				return roleID;
			}
		}
		// throw exception
		System.out.println("role not found");
		results.close();
		con.close();
		return roleID;
	}

	/**
	 * get role name of a roleID
	 * @param roleId
	 * @return role name of that roleID
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
	public static String getRoleName(int roleId) throws SQLException, ConnectionFailedException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		String role = "";
		// if the roleID exists in the list
//		if (ValidIdHelperFunctions.ValidRoleId(roleId, getRoleIds())) {
		if (getRoleIds().contains(roleId)) {
			role = DatabaseSelector.getRole(roleId, con);
			con.close();
		}
		return role;
	}

	/**
	 * get user's roleID
	 * @param userId
	 * @return user roleID
	 * @throws SQLException
	 */
	public static int getUserRoleId(int userId) throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		// return zero if the userId doesn't exist within the database.
		int roleId = -1;
		// Use a try and catch to see if the userId exists if a SQLException is thrown
		// then it does
		// not exist within the database
		try {
			roleId = DatabaseSelector.getUserRoleID(userId, con);
		} catch (SQLException invalidUserId) {
			System.out.println("Invalid userID");
		}
		con.close();
		return roleId;
	}

	/**
	 * get a list of users' role
	 * @param roleId
	 * @return a list of userID of that role
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
	public static List<Integer> getUsersByRoleHelper(int roleId) throws SQLException {
		// create a list<integer to return usersByRole
		List<Integer> userIds = new ArrayList<>();
		// check if the inputed roleID is valid
//		if (ValidIdHelperFunctions.ValidRoleId(roleId, getRoleIds())) {
		try {
			if (getRoleIds().equals(roleId)) {
			Connection con = DatabaseDriverHelper.connectDataBase();
				ResultSet results = DatabaseSelector.getUsersByRole(roleId, con);
				// go through each row and users ID by role
				while (results.next()) {
					userIds.add(results.getInt("USERID"));
				}
				results.close();
			con.close();
			}
		} catch (ConnectionFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userIds;
	}

	/**
	 * check if there is anything wrong when login from main page
	 * @param username
	 * @param password
	 * @return true if nothing wrong on checking during login
	 * @throws SQLException
	 */
	public static boolean checkLoginInfo(String username, String password) throws SQLException {
		boolean validLoginInfo = false;
		// check if username is in db
		boolean usernameInDB = checkUsernameExist(username);
		int userActive = -1;
		int userID = -1;
		boolean correctPassword = false;
		// if user is not in db login fail
		if (usernameInDB) {
			try {
				userActive = getUserStatus(username);
				userID = getUserID(username);
				// login fail if user is not active
				if (userActive == 1) {
					correctPassword = checkLoginPassword(userID, password);
				} else {
					// throw exception
					System.out.println("user not active");
				}
				if (correctPassword) {
					validLoginInfo = true;
				}
			} catch(Exception e){
				System.out.print(e);
			}
		} else {
			// throw exception
			System.out.println("user not exist here");
		}
		return validLoginInfo;
	}
	
	/**
	 * get a user status (active or not)
	 * @param username
	 * @return integer 1 if user is active, 0 if inactive, -1 if error
	 * @throws SQLException
	 */
	public static int getUserStatus(String username) throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		ResultSet results = DatabaseSelector.getUsersStatus(con);
		List<Integer> actives = new ArrayList<Integer>();
		int active = -1;
		while(results.next()) {
			if (results.getString("username").equals(username)) {
				active = results.getInt("active");
			}
		}
		results.close();
		con.close();
		return active;
	}
	
	/**
	 * check if username exist in the db
	 * @param username
	 * @return true if username exist in db
	 * @throws SQLException
	 */
	public static boolean checkUsernameExist(String username) throws SQLException {
		List<String> usernames = new ArrayList<String>();
		usernames = getUsernames();
		return usernames.contains(username);
	}
	
	/**
	 * get a list of usernames
	 * @return a list of usernames
	 * @throws SQLException
	 */
	public static List<String> getUsernames() throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		ResultSet results = DatabaseSelector.getUsernames(con);
		// create a new List to add all the Roles into
		List<String> usernames = new ArrayList<String>();
		// checks to see if there is another row with the column to add another ID.
		// moves to the next row if it exists.
		while (results.next()) {
			// add the current row's ID
			usernames.add(results.getString("username"));
		}
		results.close();
		con.close();
		return usernames;
	}
	
	/**
	 * get userID of a username
	 * @param username
	 * @return userID of that username, -1 if error
	 * @throws SQLException
	 */
	public static int getUserID(String username) throws SQLException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		ResultSet results = DatabaseSelector.getUserIds(con);
		while (results.next()) {
			if(results.getString("username").equals(username)) {
				return results.getInt("userID");
			}
		}
		// throw exception
		System.out.println("Username not exist");
		con.close();
		return -1;
	}
	
	/**
	 * check if login password match or not
	 * @param userID
	 * @param password
	 * @return true if password matched
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException 
	 */
	public static boolean checkLoginPassword(int userID, String password) 
			throws SQLException, NoSuchAlgorithmException {
		return PasswordHelper.checkPassword(userID, password);
	}

	public static int newUserId() {
		Connection con = DatabaseDriverHelper.connectDataBase();
		int newUserID = 0;
		try {
			newUserID = DatabaseSelector.getTotalNumOfUsers(con);
		} catch(Exception e){
			System.out.print(e);
		}
		// return the next userID for new account.
		return newUserID + 1;
	}
	
	// testing SelectHelper
//	public static void main(String args[]) {
//		try {
//			Connection con = DatabaseDriverHelper.connectDataBase();
////			checkLoginInfo("harmony", "1234");
////			System.out.println(checkUsernameExist("harmony"));
////			System.out.println(getUserStatus("amy"));
////			System.out.println(getUserID("harmony"));
////			System.out.println(checkLoginPassword(3, "1234"));
//			System.out.println(DatabaseSelector.getUserRoleID(3, con));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
