package com.teqlip.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginTest {

  Login sys = new Login();
  
  @Test
  @DisplayName("check a vaild user name")
  void testForVaildUserName() {
    assertTrue(Login.checkUserName("Good"));
  }

  @Test
  @DisplayName("check an invaild user name")
  void testForInvaildUserName() {
    Login.createAccount("Bad", "test", "", "", "", "teqlip", "", "", 1);
    assertFalse(Login.checkUserName("Bad"));
  }

  @Test
  @DisplayName("get the next user id")
  void testForGetUserID() {
    int p = Login.newUserID();
    Login.createAccount("admin8", "test", "", "", "", "teqlip", "", "", 1);
    assertEquals(p + 1, Login.newUserID());
  }

  @Test
  @DisplayName("get role id for TEQLIP")
  void testForRoleIDTEQLIP() {
    assertEquals(2, (int) Login.getRoleID("teqlip"));
  }

  @Test
  @DisplayName("get role id for participating organization")
  void testForRoleIDPO() {
    assertEquals(3, (int) Login.getRoleID("org"));
  }

  @Test
  @DisplayName("get role id for UTSC")
  void testForRoleIDUTSC() {
    assertEquals(1, (int) Login.getRoleID("utsc"));
  }

  @Test
  @DisplayName("create a valid account")
  void testForCreateValidAccount() {
    assertTrue(Login.createAccount("Mirrors77", "123456", "Eric", "Liu", "NONE",
        "utsc", "abc@mail.uoft.ca", "987654321", 1));
  }

  @Test
  @DisplayName("create an invalid account")
  void testForCreateInvalidAccount() {
    Login.createAccount("Miimaa", "qwerdf", "Violet", "Du", "77", "org",
        "987654321", "abc@def", 0);
    assertFalse(Login.createAccount("Miimaa", "7777", "Yue", "Liu", "99",
        "teplip", "a1b2c3", "acb@def", 1));
  }
  
  @Test
  @DisplayName("log in a vaild account ok pass ok")
  void testForLogInValidAccountWithOKPassAndAccount() {
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 1);
    assertTrue(Login.checkLoginInfo("admin", "test"));
  }
  
  @Test
  @DisplayName("log in an invaild account ok pass ok")
  void testForLogInInvalidAccountWithOKPassAndAccount() {
    Login.createAccount("admin1", "test", "", "", "", "teqlip", "", "", 0);
    assertFalse(Login.checkLoginInfo("admin1", "test"));
  }
  
  @Test
  @DisplayName("log in a vaild account ok and pass is not ok")
  void testForLogInValidAccountWithBadPassAndOKAccount() {
    Login.createAccount("admin2", "test", "", "", "", "teqlip", "", "", 1);
    assertFalse(Login.checkLoginInfo("admin2", "bad"));
  }
  
  @Test
  @DisplayName("log in an none existing account")
  void testForLogInAccountDNE() {
    assertFalse(Login.checkLoginInfo("admin7", "test"));
  }
  
  @Test
  @DisplayName("get a user id for a account")
  void testForGetUIDForAccount() {
    int p = Login.newUserID();
    Login.createAccount("admin3", "test", "", "", "", "teqlip", "", "", 1);
    assertEquals(p, Login.getUserID("admin3"));
  }
  
  @Test
  @DisplayName("get a user id for an none existing account")
  void testForGetUIDForDNEAccount() {
    assertEquals(-1, Login.getUserID("admin8"));
  }
  
  @Test
  @DisplayName("get a user status for an active account")
  void testForGetUSForActiveAccount() {
    Login.createAccount("admin4", "test", "", "", "", "teqlip", "", "", 1);
    assertEquals(1,Login.getUserStatus("admin4"));
  }
  
  @Test
  @DisplayName("get a user status for an inactive account")
  void testForGetUSForInactiveAccount() {
    Login.createAccount("admin5", "test", "", "", "", "teqlip", "", "", 0);
    assertEquals(0,Login.getUserStatus("admin5"));
  }
  
  @Test
  @DisplayName("get a user status for an none existing account")
  void testForGetUSForDNEAccount() {
    assertEquals(0,Login.getUserStatus("admin6"));
  }
  
  @Test
  @DisplayName("get role for teqlip")
  void testForGetRoleTEQ() {
    assertEquals("teqlip", Login.getRoleString(2));
  }
  
  @Test
  @DisplayName("get role for utsc")
  void testForGetRoleUTSC() {
    assertEquals("utsc", Login.getRoleString(1));
  }
  
  @Test
  @DisplayName("get role for org")
  void testForGetRoleOrg() {
    assertEquals("org", Login.getRoleString(3));
  }
  
}
