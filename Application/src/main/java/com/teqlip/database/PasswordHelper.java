package com.teqlip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHelper {
	
	/**
	 * check the login password of a user
	 * @param userID
	 * @param password
	 * @return true if password matched
	 * @throws SQLException
	 */
	public static boolean checkPassword2(int userID, String password) throws SQLException {
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
	
	public static boolean checkPassword(int userID, String password) throws SQLException, NoSuchAlgorithmException {
		Connection con = DatabaseDriverHelper.connectDataBase();
		Statement stmt = con.createStatement();
		String sql="select password from user_password where userID=" + userID ;
		String rightPassword = "";
		ResultSet rs=stmt.executeQuery(sql);
		if (rs.next()) {
			// check password in database with the input password
			rightPassword = rs.getString("password");
		} else {
			//throw exception
			System.out.println("no such userID");
			return false;
		}
		// check password
		String hashInputPassword = passwordHash(password);
		if (rightPassword.equals(hashInputPassword)) {
			return true;
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

	public static String passwordHash(String password) throws NoSuchAlgorithmException {
		String securePassword = getHashedPassword1(password);
		securePassword = getHashedPassword384(password);
		return securePassword;
	}
	
	 private static String getHashedPassword384(String passwordToHash) {
	   	 MessageDigest md;
	   	 try {
	        md = MessageDigest.getInstance("SHA-384");
	        md.update(passwordToHash.getBytes("UTF-8"));
	        byte[] mb = md.digest();
	        String out = "";
	        for (int i = 0; i < mb.length; i++) {
	            byte temp = mb[i];
	            String s = Integer.toHexString(new Byte(temp));
	            while (s.length() < 2) {
	                s = "0" + s;
	            }
	            s = s.substring(s.length() - 2);
	            out += s;
	        }
	        return out;

	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    }
	    return null;
    }

	 private static String getHashedPassword1(String passwordToHash) {
    	 MessageDigest md;
    	 try {
	        md = MessageDigest.getInstance("SHA-1");
	        md.update(passwordToHash.getBytes("UTF-8"));
	        byte[] mb = md.digest();
	        String out = "";
	        for (int i = 0; i < mb.length; i++) {
	            byte temp = mb[i];
	            String s = Integer.toHexString(new Byte(temp));
	            while (s.length() < 2) {
	                s = "0" + s;
	            }
	            s = s.substring(s.length() - 2);
	            out += s;
	        }
	        return out;

	    } catch (Exception e) {
	        System.out.println("ERROR: " + e.getMessage());
	    }
	    return null;
    }	
}
