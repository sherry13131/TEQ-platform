package com.teqlip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSelector {
	
	/**
	 * get all the roles
	 * @param connection db connection
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
	 * get the role name by roleID
	 * @param id
	 * @param connection db connection
	 * @return String - role name
	 * @throws SQLException
	 *             thrown when something goes wrong with query.
	 */
	protected static String getRole(int roleID, Connection con) throws SQLException {
		String sql = "SELECT role FROM roles WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, roleID);
		ResultSet results = preparedStatement.executeQuery();
		results.next();
		String role = results.getString("role");
		results.close();
		return role;
	}

	/**
	 * get the user's role by userID
	 * @param userId
	 * @param connection db connection
	 * @return the roleID of that user
	 * @throws SQLException
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
	 * get a result set of userID with a specific roleID
	 * @param roleId
	 * @param connection db connection
	 * @return a result set of userID
	 * @throws SQLException
	 */
	protected static ResultSet getUsersByRole(int roleId, Connection con) throws SQLException {
		String sql = "SELECT userID FROM users WHERE roleID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, roleId);
		return preparedStatement.executeQuery();
	}

	/**
	 * Return all users' infomation from the database
	 * @param connection db connection
	 * @return a results set of all rows in the table users
	 * @throws SQLException
	 */
	protected static ResultSet getUsersDetails(Connection con) throws SQLException {
		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		return preparedStatement.executeQuery();
	}

	/**
	 * find all the details about a given user
	 * @param userId
	 * @param connection db connection
	 * @return a result set with the details of the user
	 * @throws SQLException
	 */
	protected static ResultSet getUserDetails(int userId, Connection con) throws SQLException {
		String sql = "SELECT * FROM users WHERE userID = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, userId);
		return preparedStatement.executeQuery();
	}
	
	/**
	 * get a result set of all usernames
	 * @param con
	 * @return a result set of all usernames
	 * @throws SQLException
	 */
	public static ResultSet getUsernames(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT username FROM user_login;");
		return results;
	}
	
	/**
	 * get a username by username
	 * @param username
	 * @param con
	 * @return a username if found
	 * @throws SQLException
	 */
	public static ResultSet getUsername(String username, Connection con) throws SQLException {
		String sql = "SELECT username FROM user_login where username = ?;";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, username);
		return preparedStatement.executeQuery();
	}
	
	/**
	 * get all users status (active)
	 * @param con
	 * @return a result set with username and active columns 
	 * @throws SQLException
	 */
	public static ResultSet getUsersStatus(Connection con) throws SQLException {
		//get the associated userID and activity
		Statement stmt = con.createStatement();
		String sql="SELECT username, active FROM user_login";
		ResultSet result = stmt.executeQuery(sql);
		return result;
	}
	
	/**
	 * get all userIDs with username
	 * @param con
	 * @return a result set with userID and username columns
	 * @throws SQLException
	 */
	public static ResultSet getUserIds(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql="SELECT userID, username FROM user_login";
		ResultSet result=stmt.executeQuery(sql);
		return result;
	}

	/**
	 * get the hashed password
	 * @param userId
	 * @param connection db connection
	 * @return the hashed password
	 * @throws SQLException
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

	/**
	 * get the total number of users (with login account) in db
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public static int getTotalNumOfUsers(Connection con) throws SQLException {
		int newUserID = -1;
		Statement stmt = con.createStatement();
		String sql="SELECT count(*) found FROM users;";
		ResultSet rs=stmt.executeQuery(sql);
		//get the largest userID in db
		while (rs.next()) {
			newUserID = rs.getInt("found");
		}
		return newUserID;
	}
	
	/**
	 * get the templates in db
	 * @param con
	 * @return 
	 * @throws SQLException
	 */
	public static ResultSet getTemplates(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet results = stmt.executeQuery("SELECT * FROM Template;");
		return results;
	}

    public static ResultSet queryData(Connection con, String query) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query + ";");
        return rs;
    }
}
