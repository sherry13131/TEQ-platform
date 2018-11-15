package com.teqlip.gui.panels.teq;

import java.awt.HeadlessException;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseInsertHelper;
import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.database.Login;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.NewAccountEmail;
import com.teqlip.email.NoReplyEmail;
import com.teqlip.exceptions.DatabaseInsertException;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class TEQCreateAccountPanel extends BodyPanel {
	
	private BoxLayout layout;
	
	private Login db = new Login();
	
	public class ActionConsts {
		private final static String SUBMIT = "submit";
		private final static String CANCEL = "cancel";
	}

    public static final String[] ACCOUNT_FIELDS = {
        "Username",
        "Email",
        "First Name",
        "Middle Name",
        "Last Name",
        "Role (ORG, TEQLIP, UTSC)",
        "Phone Number"
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
			String firstName = this.textFields[2].getText();
			String lastName = this.textFields[4].getText();
			String middleName = this.textFields[3].getText();
			String phoneNumber = this.textFields[6].getText();
			int active = 1;
			// set after checking input
			RoleEnum role = null;
			String roleName = this.textFields[5].getText();
			
			// check if username exist, if yes, error
			// then chec if role is valid, if not error
			try {
				if (DatabaseSelectHelper.checkUsernameExist(usernameInput)) {
					JOptionPane.showMessageDialog(null, "Username already exist", "Fail create account - username existed", JOptionPane.ERROR_MESSAGE);
				} else {
					if(!checkValidEmail(emailInput)) {
						JOptionPane.showMessageDialog(null, "Not a valid email", "Fail create account - invalid email", JOptionPane.ERROR_MESSAGE);	
					}else if(!checkValidRole(roleName)) {
						JOptionPane.showMessageDialog(null, "Not a valid role", "Fail create account - invalid role", JOptionPane.ERROR_MESSAGE);
					}else {
						// get the role the user chose
						role = getRoleEnumGivenName(roleName);
						// create a random 8 letter password
						String password = getRandomPassword(8);
						// create account
						DatabaseInsertHelper.createNewUserAccount(usernameInput, password, firstName, lastName, middleName, role, emailInput, phoneNumber, active);
						// sent login credential to email
						NoReplyEmail email = new NewAccountEmail(firstName, usernameInput, password, emailInput);
						EmailHandler.addEmail(email);
						EmailHandler.sendEmails();
						JOptionPane.showMessageDialog(null, "Account created, credentials are sent to the email listed", "created account", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} catch (HeadlessException | SQLException | DatabaseInsertException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.textFields[0].setText("");
			this.textFields[1].setText("");
			this.textFields[2].setText("");
			this.textFields[3].setText("");
			this.textFields[4].setText("");
			this.textFields[5].setText("");
			this.textFields[6].setText("");
		} else if (cmd.equals(ActionConsts.CANCEL)) {
        	super.goToMenu(MenuOptions.MAIN_MENU);
        }
	}

	public static boolean checkValidEmail(String emailStr) {
	        Matcher matcher = VALID_EMAIL_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	
	public static boolean checkValidRole(String role) {
		for (RoleEnum roleValid : RoleEnum.values()) {
	        if (roleValid.name().equals(role.toUpperCase())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static RoleEnum getRoleEnumGivenName(String role) {
		for (RoleEnum roleValid : RoleEnum.values()) {
	        if (roleValid.name().equals(role.toUpperCase())) {
	            return roleValid;
	        }
	    }
	    return null;
	}
	
	public static String getRandomPassword(int length) {
		String alphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String password = "";
		while (password.length() < length){
			int randomNumber = (int)(Math.random() * alphaNum.length());
			password += alphaNum.charAt(randomNumber);
		}
		return password;
	}
}
