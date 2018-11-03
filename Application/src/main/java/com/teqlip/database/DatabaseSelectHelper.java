package com.teqlip.database;

import com.teqlip.database.DatabaseSelector;
import com.teqlip.exceptions.ConnectionFailedException;
//import com.teqlip.database.ValidIdHelperFunctions;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database.
 * TODO: The given code is there to aide you in building your methods.  You don't have
 * TODO: to keep the exact code that is given (for example, DELETE the System.out.println())
 */
public class DatabaseSelectHelper extends DatabaseSelector {
	
	/**
	 * @return list of all role IDs
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
	 * @param roleId
	 * @return
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
	public static String getRoleName(int roleId) throws SQLException, ConnectionFailedException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		// make a role string to empty if the role doesn't exist
		String role = "";
		// if the roleID exists within the list of role id's get the the role name
//		if (ValidIdHelperFunctions.ValidRoleId(roleId, getRoleIds())) {
		if (getRoleIds().contains(roleId)) {
			role = DatabaseSelector.getRole(roleId, con);
			con.close();
		}
		return role;
	}

	/**
	 * @param userId
	 * @return
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
			System.out.println(userId);
			System.out.println(roleId);
			System.out.println("sth wrong in getUserRoleId()");
		}
		con.close();
		return roleId;
	}

	/**
	 * @param roleId
	 * @return
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
	 * @return
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
//	public static List<User> getUsersDetailsHelper() throws SQLException {
//		List<User> users = new ArrayList<User>();
////		Connection connection = DatabaseDriverHelper.connectDataBase();
//		ResultSet results = DatabaseSelector.getUsersDetails();
//		while (results.next()) {
//			User user = null;
//			String name = results.getString("NAME");
//			// get the age of the person at the current row and column
//			int age = results.getInt("AGE");
//			// get the address of the person at the current row and column
//			String address = (results.getString("ADDRESS"));
//			// add the user the array list.
//			// get the roleID in order to see the role name to see which type of User
//			// object we should create to add the list of all users.
//			int roleId = getUserRoleId(results.getInt("ID"));
//			// change the role to an uppercase to compare with the enum constant value
//			String role = getRoleName(roleId).toUpperCase();
//			// get the user's ID
//			// get the the name of the person at the current row in the name column
//			// check which role the user and create the user subclass according to their
//			// role name
//			if (roleId != 0) {
//				if (Roles.ADMIN.toString().equals(role)) {
//					user = new Admin(results.getInt("ID"), name, age, address);
//				} else if (Roles.EMPLOYEE.toString().equals(role)) {
//					user = new Employee(results.getInt("ID"), name, age, address);
//				} else {
//					user = new Customer(results.getInt("ID"), name, age, address);
//				}
//			}
//			users.add(user);
//		}
//		results.close();
//		connection.close();
//		return users;
//	}

	/**
	 * returns the user after giving in its userId
	 * 
	 * @param userId
	 * @return user
	 * @throws SQLException
	 * @throws ConnectionFailedException
	 */
//	public static User getUserDetails(int userId) throws SQLException, ConnectionFailedException {
//		User user = null;
//		Connection connection = DatabaseDriverHelper.connectDataBase();
//		ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
//		while (results.next()) {
//			// check if the the row's current UserId is the same as the inputed userId
//			if (results.getInt("ID") == userId) {
//				// find out the role of the the given user
//				String role = getRoleName(getUserRoleId(userId));
//				// if the roleId is that of an admin then create an admin object
//				if (Roles.ADMIN.toString().equals(role.toUpperCase())) {
//					user = new Admin(userId, results.getString("NAME"), (results.getInt("AGE")),
//							(results.getString("ADDRESS")));
//					results.close();
//					// if the roleId is that of an Employee then create an Employee object
//				} else if (Roles.EMPLOYEE.toString().equals(role.toUpperCase())) {
//					user = new Employee(userId, results.getString("NAME"), (results.getInt("AGE")),
//							(results.getString("ADDRESS")));
//					results.close();
//				} else {
//					// if the roleId is that of an Customer then create an Customer object
//					user = new Customer(userId, results.getString("NAME"), (results.getInt("AGE")),
//							(results.getString("ADDRESS")));
//					results.close();
//				}
//			}
//		}
//		connection.close();
//		return user;
//	}

	/**
	 * @param userId
	 * @return the Hashed password of the user
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
	
	public static boolean checkLoginInfo(String username, String password) throws SQLException {
		boolean validLoginInfo = false;
		// check if username is in db
		boolean usernameInDB = checkUsernameExist(username);
		System.out.println(usernameInDB);
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
	
	public static boolean checkUsernameExist(String username) throws SQLException {
		List<String> usernames = new ArrayList<String>();
		usernames = getUsernames();
//		 checks to see if there is another row with the column to add another ID.
//		 moves to the next row if it exists.

//		System.out.println(usernames);
//		System.out.println(usernames.contains(username));
		return usernames.contains(username);
	}
	
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
	
	public static boolean checkLoginPassword(int userID, String password) throws SQLException {
		return PasswordHelper.checkPassword(userID, password);
	}
	
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
