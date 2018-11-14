package com.teqlip.gui.panels.teq;

import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseInsertHelper;
import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.email.AccountCreatedEmail;
import com.teqlip.email.ChangedPassEmail;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.EmailInterface;
import com.teqlip.exceptions.DatabaseInsertException;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class TEQCreateAccountPanel extends BodyPanel {
  
  private BoxLayout layout;
  
  public class ActionConsts {
    private final static String SUBMIT = "submit";
    private final static String CANCEL = "cancel";
  }

    public static final String[] ACCOUNT_FIELDS = {
        "Username",
        "Email"
    };
    public JTextField[] textFields = new JTextField[TEQCreateAccountPanel.ACCOUNT_FIELDS.length];
    
  public static final Pattern VALID_EMAIL_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);

    public TEQCreateAccountPanel(AppFrame main) {
      super(main);
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        
        JComponent fields = createFieldsPane();
        JComponent buttons = createButtonPane();
        
        add(fields);
        add(buttons);
    }
    
    public JComponent createFieldsPane() {
      JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
      
        for (int i = 0; i < TEQCreateAccountPanel.ACCOUNT_FIELDS.length; i++) {
            JLabel label = new JLabel(TEQCreateAccountPanel.ACCOUNT_FIELDS[i]);
            this.textFields[i] = new JTextField();
            p.add(label);
            p.add(this.textFields[i]);
        }

      return p;
    }
    
    public JComponent createButtonPane() {
      JPanel p = JGuiHelper.createPanelFlow();
      
      JButton submitBtn = JGuiHelper.createButton("Submit", this, ActionConsts.SUBMIT);
      JButton cancelBtn = JGuiHelper.createButton("Cancel", this, ActionConsts.CANCEL);
      
      p.add(submitBtn);
      p.add(cancelBtn);
      
      return p;
    }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    
    if (ActionConsts.SUBMIT.equals(cmd)) {
      String usernameInput = this.textFields[0].getText();
      String emailInput = this.textFields[1].getText();
      
      // replace these to value in field
      String firstName = "fn";
      String lastName = "ln";
      String middleName = "midname";
      RoleEnum role = RoleEnum.TEQLIP;
      String phoneNumber = "1234093876";
      int active = 1;
      
      boolean flag = false;
      // check if username exist, if yes, error
      if (!checkUserExist(usernameInput)) {
        flag = checkValidEmailHandler(emailInput);
      }
      if (flag) {
        // create account - default teqlip account/role
        addNewUserAccount(usernameInput, "password", firstName, lastName, middleName, role, emailInput, phoneNumber, active);
      }
      this.textFields[0].setText("");
      this.textFields[1].setText("");
    } else if (cmd.equals(ActionConsts.CANCEL)) {
        super.goToMenu(MenuOptions.MAIN_MENU);
    }
  }
  
  private boolean checkUserExist(String usernameInput) {
    try {
      if (DatabaseSelectHelper.checkUsernameExist(usernameInput)) {
        JOptionPane.showMessageDialog(null, "Username already exist", "Fail create account - username existed", JOptionPane.ERROR_MESSAGE);
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  private boolean checkValidEmailHandler(String emailInput) {
    if (checkValidEmail(emailInput)) {
      return true;
    }
    JOptionPane.showMessageDialog(null, "Not a valid email", "Fail create account - invalid email", JOptionPane.ERROR_MESSAGE);
    return false;
  }
  
  private boolean addNewUserAccount(String usernameInput, String password, String firstName, String lastName, String middleName, RoleEnum role,
      String emailInput, String phoneNumber, int active) {
    boolean success = false;
    try {
      int uid = DatabaseInsertHelper.createNewUserAccount(usernameInput, password, firstName, lastName, middleName, role, emailInput, phoneNumber, active);
      JOptionPane.showMessageDialog(null, "Account created with a default password: password", "created account", JOptionPane.INFORMATION_MESSAGE);
      // send email
      sendEmail(usernameInput, uid, "password");
      success = true;
    } catch (DatabaseInsertException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return success;
  }
  
  private void sendEmail(String username, int id, String password) {
    String emailAddress = DatabaseSelectHelper.getUserEmailHelper(id);
    EmailInterface email = new AccountCreatedEmail(username, emailAddress, password);
    
    EmailHandler.addEmail(email);
    EmailHandler.sendEmails();
  }
  
  private boolean checkValidEmail(String emailStr) {
    Matcher matcher = VALID_EMAIL_REGEX .matcher(emailStr);
    return matcher.find();
  }
}
