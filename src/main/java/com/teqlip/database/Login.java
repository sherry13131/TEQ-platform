package com.teqlip.database;

import java.sql.*;

public class Login {
	public static void main(String[] args) {
		System.out.println(checkUserName("sean"));
		System.out.println(newUserID());
		System.out.println(getRoleID("utsc"));
		System.out.println(createAccount("sean", "password","Son","Nguyen","Hung","utsc","seannguyen@gmial","1289432",1));
	}
	
	/**
	 * Take a username and check if there exist the same username already in db.
	 * @param username used to check.
	 * @return Whether or not the username already taken and is in db.
	 */
	public static boolean checkUserName(String username) {
		boolean valid = true;	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
			Statement stmt=con.createStatement();
			String sql="SELECT * FROM user_login WHERE username='"+username+"'";
			ResultSet rs=stmt.executeQuery(sql);
			// if the result set is empty, then there is no duplicate
			while (rs.next()) {
				//System.out.println(rs.getString("username"));
				 valid = false;
			}
		} catch(Exception e){
			System.out.print(e);
			valid = false;
		}
		return valid;
	}
	
	/**
	 * Get the next userID, to add to database.
	 * @param NONE
	 * @return next userID for a new account.
	 */
	public static int newUserID() {
		int newUserID = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
			Statement stmt=con.createStatement();
			//select the row where userID is the largest in users
			String sql="SELECT * FROM users WHERE userID=(SELECT MAX(userID) FROM users)";
			ResultSet rs=stmt.executeQuery(sql);
			//get the largest userID in db
			while (rs.next()) {
				newUserID = rs.getInt("userID");
			}
		} catch(Exception e){
			System.out.print(e);
		}
		// return the next userID for new account.
		return newUserID + 1;
	}
	
	/**
	 * Given the role, return the associated RoleID
	 * @param role
	 * @return The associated roleID, -1 if role does not exist in db.
	 */
	public static Integer getRoleID(String role) {
		int roleID = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
			Statement stmt=con.createStatement();
			//select the row where userID is the largest in users
			String sql="SELECT * FROM roles WHERE role='"+role+"'";
			ResultSet rs=stmt.executeQuery(sql);
			//get the largest userID in db
			while (rs.next()) {
				roleID = rs.getInt("roleID");
			}
		} catch(Exception e){
			System.out.print(e);
		}
		return roleID;
	}
	
	
	
	/**
	 * Given the userName, password, firstName,lastName, middleName, role, email,phoneNumber, and activity,
	 * create a new account if there is no conflict with the given informations.
	 * @param userID, password, firstName, middleName, lastName, roleID, email,phoneNumber, and activity
	 * @return Whether or not the account is successfully created.
	 */
	public static boolean createAccount(String userName, String password, String firstName,String lastName, String middleName, String role, String email, String phoneNumber, Integer active) {
		boolean accountCreated = false;
		//get userID of this new account
		int userID = newUserID();
		// boolean checking userName
		boolean userNameValid = checkUserName(userName);
		// get roleID associated with the role of this user
		int roleID = getRoleID(role);
		// if the userId and role is valid then create account and add to db
		if (userNameValid && roleID != -1) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
				Statement stmt=con.createStatement();
				//add to users table
				String sql="INSERT INTO users(userID, firstName, lastName, middleName, roleID, email, phoneNumber) VALUES ('"+userID+"', '"+firstName+"', '"+lastName+"', '"+middleName+"', '"+roleID+"', '"+email+"', '"+phoneNumber+"')";
				stmt.execute(sql);
				//add to the user_login
				sql="INSERT INTO user_login(userID, userName, active) VALUES ('"+userID+"', '"+userName+"', '"+active+"')";
				stmt.execute(sql);
				//add to the user_password
				//TODO
				
				
				accountCreated = true;
			} catch(Exception e){
				System.out.print(e);
			}
			
		}
		
		return accountCreated;
	}
	
}
