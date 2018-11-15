package com.teqlip.Role;

import java.sql.SQLException;
import java.util.ArrayList;

import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.panels.org.OrgMainMenuPanel;
import com.teqlip.gui.panels.teq.TEQMainMenuPanel;
import com.teqlip.gui.panels.utsc.UTSCMainMenuPanel;

public enum RoleEnum {
    UTSC {
      public String getString() {
        return "utsc";
      }
      public void changeMenu(String userInput) {
        AppFrame main = new AppFrame(userInput, "UTSC Project Staff");
        main.setBody(new UTSCMainMenuPanel(main));

        main.packAndShow();
      }
      public int getRoleId() throws SQLException {
        return DatabaseSelectHelper.getRoleId(UTSC);
      }
      public RoleEnum getRoleEnum() {
        return UTSC;
      }
    },
    TEQLIP {
      public String getString() {
        return "teqlip";
      }
      public void changeMenu(String userInput) {
        AppFrame main = new AppFrame(userInput, "TEQ Project Staff");
        main.setBody(new TEQMainMenuPanel(main));

        main.packAndShow();
      }
      public int getRoleId() throws SQLException {
        return DatabaseSelectHelper.getRoleId(TEQLIP);
      }
      public RoleEnum getRoleEnum() {
        return TEQLIP;
      }
    },
    ORG {
      public String getString() {
        return "org";
      }
      public void changeMenu(String userInput) {
        AppFrame main = new AppFrame(userInput, "Participating Organization Volunteer");
        main.setBody(new OrgMainMenuPanel(main));

        main.packAndShow();
      }
      public int getRoleId() throws SQLException {
        return DatabaseSelectHelper.getRoleId(ORG);
      }
      public RoleEnum getRoleEnum() {
        return ORG;
      }
    };
  
    public static ArrayList<RoleEnum> enumIteration() {  
      RoleEnum[] roles = RoleEnum.values(); 
      ArrayList<RoleEnum> allRoleEnum = new ArrayList<RoleEnum>();
      for (RoleEnum role : roles) {
        allRoleEnum.add(role);  
      }
      return allRoleEnum;  
    }
    
    public abstract String getString();
    public abstract void changeMenu(String userInput);
    public abstract int getRoleId() throws SQLException;
    public abstract RoleEnum getRoleEnum();
}
