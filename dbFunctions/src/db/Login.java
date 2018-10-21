package db;

import java.sql.*;

public class Login {
	public static void main(String[] args) {
		System.out.println(checkUserName("sean"));
		System.out.println(newUserID());
		System.out.println(getRoleID("utsc"));
		System.out.println(createAccount("sean", "password","Son","Nguyen","Hung","utsc","seannguyen@gmial","1289432",1));
		System.out.println(checkLoginInfo("sean","password"));
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
	public static boolean createAccount(String username, String password, String firstName,String lastName, String middleName, String role, String email, String phoneNumber, Integer active) {
		boolean accountCreated = false;
		//get userID of this new account
		int userID = newUserID();
		// boolean checking userName
		boolean usernameValid = checkUserName(username);
		// get roleID associated with the role of this user
		int roleID = getRoleID(role);
		// if the userId and role is valid then create account and add to db
		if (usernameValid && roleID != -1) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
				Statement stmt=con.createStatement();
				//add to users table
				String sql="INSERT INTO users(userID, firstName, lastName, middleName, roleID, email, phoneNumber) VALUES ('"+userID+"', '"+firstName+"', '"+lastName+"', '"+middleName+"', '"+roleID+"', '"+email+"', '"+phoneNumber+"')";
				stmt.execute(sql);
				//add to the user_login
				sql="INSERT INTO user_login(userID, username, active) VALUES ('"+userID+"', '"+username+"', '"+active+"')";
				stmt.execute(sql);
				//add to the user_password
				sql="INSERT INTO user_password(userID, password) VALUES ('"+userID+"', '"+password+"')";
				stmt.execute(sql);
				accountCreated = true;
			} catch(Exception e){
				System.out.print(e);
			}
			
		}
		
		return accountCreated;
	}
	
	/**
	 * Given the username, password, login if the username and password match whats in db and the account is active.
	 * @param username, password
	 * @return Whether or not login is successful.
	 */
	public static boolean checkLoginInfo(String username, String password) {
		boolean validLoginInfo = false;
		// check if username is in db
		boolean usernameInDB = !checkUserName(username);
		int userActive = 0;
		int userID = -1;
		// if user is not in db login fail
		if (usernameInDB) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignmentdb", "root", "");
				Statement stmt=con.createStatement();
				//get the associated userID and activity
				String sql="SELECT * FROM user_login WHERE username='"+username+"'";
				ResultSet rs=stmt.executeQuery(sql);
				while (rs.next()) {
					userActive = rs.getInt("active");
					userID = rs.getInt("userID");
				}
				// login fail if user is not active
				if (userActive == 1) {
					sql="SELECT * FROM user_password WHERE userID='"+userID+"'";
					rs=stmt.executeQuery(sql);
					while (rs.next()) {
						// check password in database with the input password
						if (password.equals(rs.getString("password"))) {
							validLoginInfo = true;
						}
					}
				}
				
			} catch(Exception e){
				System.out.print(e);
			}
		}
		return validLoginInfo;
	}
}
