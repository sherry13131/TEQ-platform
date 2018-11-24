package com.teqlip.gui.panels.teq;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.database.*;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.EmailInterface;
import com.teqlip.email.type.AccountDeletedEmail;
import com.teqlip.email.type.ChangedPassEmail;

@SuppressWarnings("serial")
public class TEQDeleteAccountPanel extends BodyPanel {
	
	public class ActionConsts {
		private static final String SUBMIT = "submit";
        private static final String CANCEL = "cancel";
	}
	
    private JTextField usernameField;
    private static final Dimension FIELD_DIMENSION = new Dimension(80, 30);
	
	private BoxLayout layout;
    
    public TEQDeleteAccountPanel(AppFrame main) {
    	super(main);
 
      	layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		super.setLayout(layout);

    	JComponent usernamePane = createUsernamePane();
        JComponent buttonsPane = createButtonPane();
        
        add(usernamePane);
        add(buttonsPane);
    }
    
    public JComponent createUsernamePane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel usernamelbl = new JLabel("Username:");
    	this.usernameField = JGuiHelper.createTextField();
    	this.usernameField.setPreferredSize(FIELD_DIMENSION);
    	
    	p.add(usernamelbl);
    	p.add(this.usernameField);
    	
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
			super.goToMenu(MenuOptions.MAIN_MENU);
		} else if (cmd.equals(ActionConsts.SUBMIT)) {
      try {
		    int id = DatabaseSelectHelper.getUserID(this.usernameField.getText());
        if(id > 0) {
          sendEmail();
          DatabaseDeleteHelper.deleteAUserHelper(id);
          JOptionPane.showMessageDialog(null, "Account removed, user has been notified through email", "removed account", JOptionPane.INFORMATION_MESSAGE);
          super.goToMenu(MenuOptions.MAIN_MENU);
        } else {
          JOptionPane.showMessageDialog(null, "User does not exist", "Fail remove account - invalid user", JOptionPane.ERROR_MESSAGE);
          usernameField.setText("");
        }
      } catch (SQLException ex) {
          ex.printStackTrace();
      }
		}
		
	}
	
	public void sendEmail() {
	  String emailAddress = DatabaseSelectHelper.getUserEmailHelper(this.usernameField.getText());
	  EmailInterface email = new AccountDeletedEmail(main.getUsername(), emailAddress);
    EmailHandler.addEmail(email);
    EmailHandler.sendEmails();
	}
}
