package com.teqlip.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.teqlip.exceptions.ConnectionFailedException;
import com.teqlip.exceptions.DatabaseInsertException;

public class DatabaseSelectorTest {
	
	@BeforeAll
	public void setUp() throws DatabaseInsertException, SQLException {
		// insert test data row
		DatabaseInsertHelper.createNewUserAccount("test", "1234", "fn", "ln", "", "utsc", "1234@asdf.com", "1232341234", 1);
	}
	
	@AfterAll
	public void cleanUp() {
		// delete all the account created here
//		Connection con = DatabaseDriverHelper.connectDataBase();
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
//    @DisplayName("test checking wrong login info")
//    public void testCorrectLogin() throws DatabaseInsertException, SQLException {
//    	boolean status = DatabaseSelectHelper.checkLoginInfo("test", "12345");
//    	assertTrue(status);
//    }
//    
//    @Test
//    @DisplayName("test checking wrong login info")
//    public void testWrongLogin() {
//    	
//    }
}