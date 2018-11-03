package com.teqlip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelector {
	
	/**
	 * get all the roles.
	 * 
	 * @param connection
	 *            the connection.
	 * @return a ResultSet containing all rows of the roles table.
	 * @throws SQLException
	 *             thrown if an SQLException occurs.
	 */
	protected static ResultSet getRoles(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM roles;");
		return results;
	}

	/**
	 * get the role with id id.
	 * 
	 * @param id
	 *            the id of the role
	 * @param connection
	 *            the database connection
	 * @return a String containing the role.
	 * @throws SQLException
	 *             thrown when something goes wrong with query.
	 */
	protected static String getRole(int id, Connection con) throws SQLException {
		String sql = "SELECT role FROM roles WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		String role = results.getString("role");
		results.close();
		return role;
	}

	/**
	 * get the role of the given user.
	 * 
	 * @param userId
	 *            the id of the user.
	 * @param connection
	 *            the connection to the database.
	 * @return the roleId for the user.
	 * @throws SQLException
	 *             thrown if something goes wrong with the query.
	 */
	protected static int getUserRoleID(int userId, Connection con) throws SQLException {
		String sql = "SELECT roleID FROM users WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		int userRoleID = results.getInt("roleID");
		results.close();
		return userRoleID;
	}

	/**
	 * get the role of the given user.
	 * 
	 * @param userId
	 *            the id of the user.
	 * @param connection
	 *            the connection to the database.
	 * @return the roleId for the user.
	 * @throws SQLException
	 *             thrown if something goes wrong with the query.
	 */
	protected static ResultSet getUsersByRole(int roleId, Connection con) throws SQLException {
		String sql = "SELECT userID FROM users WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, roleId);
		return preparedStatement.executeQuery();
	}

	/**
	 * Return all users from the database.
	 * 
	 * @param connection
	 *            the connection to the database.
	 * @return a results set of all rows in the table.
	 * @throws SQLException
	 *             thrown if there is an issue.
	 */
	protected static ResultSet getUsersDetails(Connection con) throws SQLException {
		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}

	/**
	 * find all the details about a given user.
	 * 
	 * @param userId
	 *            the id of the user.
	 * @param connection
	 *            a connection to the database.
	 * @return a result set with the details of the user.
	 * @throws SQLException
	 *             thrown when something goes wrong with query.
	 */
	protected static ResultSet getUserDetails(int userId, Connection con) throws SQLException {
		String sql = "SELECT * FROM users WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		return preparedStatement.executeQuery();
	}
	
	public static ResultSet getUsernames(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT username FROM user_login;");
		return results;
	}
	
	public static ResultSet getUsername(String username, Connection con) throws SQLException {
		String sql = "SELECT username FROM user_login where username = ?;";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, username);
//		System.out.println(results.getString("username"));
		return preparedStatement.executeQuery();
	}
	
	public static ResultSet getUsersStatus(Connection con) throws SQLException {
		//get the associated userID and activity
		Statement stmt = con.createStatement();
		String sql="SELECT username, active FROM user_login";
		ResultSet result = stmt.executeQuery(sql);
		return result;
	}
	
	public static ResultSet getUserIds(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql="SELECT userID, username FROM user_login";
		ResultSet result=stmt.executeQuery(sql);
		return result;
	}

	/**
	 * get the hashed version of the password.
	 * 
	 * @param userId
	 *            the user's id.
	 * @param connection
	 *            the database connection.
	 * @return the hashed password to be checked against given password.
	 * @throws SQLException
	 *             if a database issue occurs.
	 */
	protected static String getPassword(int userId, Connection con) throws SQLException {
		String sql = "SELECT password FROM user_password WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		ResultSet results = preparedStatement.executeQuery();
		String hashPassword = results.getString("password");
		results.close();
		return hashPassword;
	}

}