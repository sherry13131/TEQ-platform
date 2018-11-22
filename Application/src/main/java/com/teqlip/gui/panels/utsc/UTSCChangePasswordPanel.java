package com.teqlip.gui.panels.utsc;

import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.database.DatabaseUpdateHelper;
import com.teqlip.database.PasswordHelper;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.EmailInterface;
import com.teqlip.email.type.ChangedPassEmail;
import com.teqlip.email.type.NoReplyEmail;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;

@SuppressWarnings("serial")
public class UTSCChangePasswordPanel extends BodyPanel {

  private BoxLayout layout;
  
  public class ActionConsts {
    private final static String SUBMIT = "submit";
    private final static String CANCEL = "cancel";
  }
  
  public static final String[] CHANGE_FIELDS = {
      "OldPassword",
      "NewPassword",
      "ConfirmNewPassword"
  };
  
  public JPasswordField[] textFields = new JPasswordField[UTSCChangePasswordPanel.CHANGE_FIELDS.length];
  
  public UTSCChangePasswordPanel(AppFrame main) {
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
    
    for (int i = 0; i < UTSCChangePasswordPanel.CHANGE_FIELDS.length; i++) {
        JLabel label = new JLabel(UTSCChangePasswordPanel.CHANGE_FIELDS[i]);
        this.textFields[i] = new JPasswordField();
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
    
    if (cmd.equals(ActionConsts.CANCEL)) {
      super.goToMenu(MenuOptions.UTSC_MAIN_MENU);
    } else if (cmd.equals(ActionConsts.SUBMIT)) {
      String oldPwd = this.textFields[0].getText();
      String newPwd = this.textFields[1].getText();
      String confirmPwd = this.textFields[2].getText();
      
      boolean matched = false;
      // check if the old Password match current password
      matched = checkMatchCurrentPassword(oldPwd);
      // check if new password match
      boolean confirmed = confirmMatchPassword(newPwd, confirmPwd);
      
      // change the password if both matched and confirmed
      if (matched && confirmed) {
        int uid = getUserID();
        String emailAddress = DatabaseSelectHelper.getUserEmailHelper(uid);
        PasswordHelper.updatePassword(uid, newPwd);
        JOptionPane.showMessageDialog(null, "Password is changed successfully", "Password changed", JOptionPane.INFORMATION_MESSAGE);
        // send email to user for notification
        sendEmail(emailAddress);
        super.goToMenu(MenuOptions.UTSC_MAIN_MENU);
      } else if (!matched) {
        JOptionPane.showMessageDialog(null, "The old password doesn't match the current password", "Incorrect password", JOptionPane.ERROR_MESSAGE);
      } else if (!confirmed) {
        JOptionPane.showMessageDialog(null, "The new password doesn't match each other", "Password not match", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  private boolean checkMatchCurrentPassword(String input) {
    int uid = getUserID();
    boolean matched = false;
    try {
      matched = DatabaseSelectHelper.checkLoginPassword(uid, input);
    } catch (NoSuchAlgorithmException | SQLException e) {
      e.printStackTrace();
    }
    
    if (matched) {
      return true;
    }
    return false;
  }

  private boolean confirmMatchPassword(String first, String second) {
    if (first.equals(second)) {
      return true;
    }
    return false;
  }
  
  private int getUserID() {
    String username = main.getUsername();
    try {
      return DatabaseSelectHelper.getUserID(username);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }
  
  private void sendEmail(String emailAddress) {
    EmailInterface email = new ChangedPassEmail(main.getUsername(), emailAddress);
    EmailHandler.addEmail(email);
    EmailHandler.sendEmails();
  }
}
