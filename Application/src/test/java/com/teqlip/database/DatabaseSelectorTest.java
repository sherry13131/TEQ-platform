package com.teqlip.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.teqlip.exceptions.ConnectionFailedException;
import com.teqlip.exceptions.DatabaseInsertException;

public class DatabaseSelectorTest {
	
	static int user1, user2;
	
	@BeforeAll
	public static void setUp() throws DatabaseInsertException, SQLException {
		// insert test data row
		user1 = DatabaseInsertHelper.createNewUserAccount("test1", "1234", "fn", "ln", null, "utsc", "1234@asdf.com", "1232341234", 1);
		user2 = DatabaseInsertHelper.createNewUserAccount("test2", "abcd", "fn", "ln", null, "teqlip", "1234@asdf.com", "1232341234", 0);
	}
	
	@AfterAll
	public static void cleanUp() {
		// delete all the account created here
		DatabaseDeleteHelper.deleteAUserHelper(user1);
		DatabaseDeleteHelper.deleteAUserHelper(user2);
	}
	
    @Test
	@DisplayName("get all role ids")
	public void testGetRoleIds() throws SQLException, ConnectionFailedException {
    	List<Integer> roles = DatabaseSelectHelper.getRoleIds();
    	List<Integer> rightroles = new ArrayList<Integer>();
    	rightroles.add(1);
    	rightroles.add(2);
    	rightroles.add(3);
    	assertEquals(rightroles, roles);
	}
    
    @Test
    @DisplayName("test getting roleID of utsc")
    public void testGetIdUtsc() throws SQLException {
    	int id = DatabaseSelectHelper.getRoleId("utsc");
    	assertEquals(1, id);
    }
    
    @Test
    @DisplayName("test getting roleID of teqlip")
    public void testGetIdTeqlip() throws SQLException {
    	int id = DatabaseSelectHelper.getRoleId("teqlip");
    	assertEquals(2, id);
    }
    
    @Test
    @DisplayName("test getting roleID of org")
    public void testGetIdOrg() throws SQLException {
    	int id = DatabaseSelectHelper.getRoleId("org");
    	assertEquals(3, id);
    }
    
    @Test
    @DisplayName("test getting roleID of nonexist role")
    public void testGetIdteqlip() throws SQLException {
    	int id = DatabaseSelectHelper.getRoleId("hello");
    	assertEquals(-1, id);
    }
    
    @Test
	@DisplayName("get correct role name corresponding to role id")
	public void testGetRoleName() throws SQLException, ConnectionFailedException {
    	String role = DatabaseSelectHelper.getRoleName(1);
    	String rightrole = "utsc";
    	assertEquals(rightrole, role);
       	role = DatabaseSelectHelper.getRoleName(2);
    	rightrole = "teqlip";
    	assertEquals(rightrole, role);
       	role = DatabaseSelectHelper.getRoleName(3);
    	rightrole = "org";
    	assertEquals(rightrole, role);
    }
    
    @Test
    @DisplayName("test getting the role id of an exist user")
    public void testGetRoleIdOfUser() throws SQLException {
    	int rid = DatabaseSelectHelper.getUserRoleId(4);
    	assertEquals(3, rid);
    }
    
    @Test
    @DisplayName("test getting the role id of a nonexist user")
    public void testGetRoleIdOfNonUser() throws SQLException {
    	int rid = DatabaseSelectHelper.getUserRoleId(9999);
    	assertEquals(-1, rid);
    }
    
    @Test
    @DisplayName("test getting the user ids of a non exist roleid")
    public void testGetUserIdsByRoleId() throws SQLException {
    	List<Integer> uids = DatabaseSelectHelper.getUsersByRoleHelper(99);
    	assertEquals(new ArrayList<Integer>(), uids);
    }
    
    @Test
    @DisplayName("test checking correct login info")
    public void testCorrectLogin() throws SQLException {
    	boolean status = DatabaseSelectHelper.checkLoginInfo("test1", "1234");
    	assertTrue(status);
    }
    
    @Test
    @DisplayName("test checking wrong login info")
    public void testWrongLogin() throws SQLException {
    	boolean status = DatabaseSelectHelper.checkLoginInfo("test1", "12341234");
    	assertFalse(status);
    }
    
    @Test
    @DisplayName("test getting the active status")
    public void testGetStatusOfUser1() throws SQLException {
    	int status = DatabaseSelectHelper.getUserStatus("test1");
    	assertEquals(1, status);
    }
    
    @Test
    @DisplayName("test getting the inactive status")
    public void testGetStatusOfUser2() throws SQLException {
    	int status = DatabaseSelectHelper.getUserStatus("test2");
    	assertEquals(0, status);
    }
    
    @Test
    @DisplayName("test username that is not exist")
    public void testNewUsernameThatNotExist() throws SQLException {
    	boolean exist = DatabaseSelectHelper.checkUsernameExist("testing123");
    	assertFalse(exist);
    }
    
    @Test
    @DisplayName("test username that not exist")
    public void testNewUsernameThatExist() throws SQLException {
    	boolean exist = DatabaseSelectHelper.checkUsernameExist("test1");
    	assertTrue(exist);
    }
    
    @Test
    @DisplayName("test getting a list of usernames")
    public void testGetUsernames() throws SQLException {
    	List<String> usernames = DatabaseSelectHelper.getUsernames();
    	assertTrue(usernames.contains("test1"));
    	assertTrue(usernames.contains("test2"));
    }
    
    @Test
    @DisplayName("test getting userID of a username")
    public void testGetUseridOfUser() throws SQLException {
    	int id = DatabaseSelectHelper.getUserID("test1");
    	assertEquals(user1, id);
    }
    
    @Test
    @DisplayName("test getting userID of a non exist username")
    public void testGetUseridOfNonExistUser() throws SQLException {
    	int id = DatabaseSelectHelper.getUserID("hellotest");
    	assertEquals(-1, id);
    }
    
    @Test
    @DisplayName("test password match")
    public void testCorrectPassword() throws NoSuchAlgorithmException, SQLException {
    	boolean match = DatabaseSelectHelper.checkLoginPassword(user2, "abcd");
    	assertTrue(match);
    }
    
    @Test
    @DisplayName("test password not match")
    public void testWrongPassword() throws NoSuchAlgorithmException, SQLException {
    	boolean match = DatabaseSelectHelper.checkLoginPassword(user2, "awefawe");
    	assertFalse(match);
    }
    
    @Test
    @DisplayName("test userID exist")
    public void testExistUserID() {
    	boolean exist = DatabaseSelectHelper.getUserIdExist(user2);
    	assertTrue(exist);
    }
    
    @Test
    @DisplayName("test userID not exist")
    public void testNotExistUserID() {
    	boolean exist = DatabaseSelectHelper.getUserIdExist(user2 + 1);
    	assertFalse(exist);
    }
}