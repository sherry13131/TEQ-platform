package com.teqlip.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoginTest {

  @Test
  @DisplayName("check a vaild user name")
  void testForVaildUserName() {
    assertTrue(Login.checkUserName("Eric"));
  }

  @Test
  @DisplayName("check an invaild user name")
  void testForInvaildUserName() {
    Login.checkUserName("Violet");
    assertFalse(Login.checkUserName("Violet"));
  }

  @Test
  @DisplayName("get the first user id")
  void testForFirstUserID() {
    assertEquals(1, Login.newUserID());
  }

  @Test
  @DisplayName("get the second user id")
  void testForSecondUserID() {
    Login.newUserID();
    assertEquals(2, Login.newUserID());
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
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 0);
    assertFalse(Login.checkLoginInfo("admin", "test"));
  }
  
  @Test
  @DisplayName("log in a vaild account ok and pass is not ok")
  void testForLogInValidAccountWithBadPassAndOKAccount() {
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 1);
    assertFalse(Login.checkLoginInfo("admin", "bad"));
  }
  
  @Test
  @DisplayName("log in an none existing account")
  void testForLogInAccountDNE() {
    assertFalse(Login.checkLoginInfo("admin", "test"));
  }
  
  @Test
  @DisplayName("get a user id for a account")
  void testForGetUIDForAccount() {
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 1);
    assertEquals(1, Login.getUserID("admin"));
  }
  
  @Test
  @DisplayName("get a user id for an none existing account")
  void testForGetUIDForDNEAccount() {
    assertEquals(-1, Login.getUserID("admin"));
  }
  
  @Test
  @DisplayName("get a user status for an active account")
  void testForGetUSForActiveAccount() {
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 1);
    assertEquals(1,Login.getUserStatus("admin"));
  }
  
  @Test
  @DisplayName("get a user status for an inactive account")
  void testForGetUSForInactiveAccount() {
    Login.createAccount("admin", "test", "", "", "", "teqlip", "", "", 0);
    assertEquals(0,Login.getUserStatus("admin"));
  }
  
  @Test
  @DisplayName("get a user status for an none existing account")
  void testForGetUSForDNEAccount() {
    assertEquals(0,Login.getUserStatus("admin"));
  }
}
