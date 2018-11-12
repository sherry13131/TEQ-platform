package com.teqlip.gui.frames;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import com.teqlip.gui.DatabaseSelector;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.org.OrgMainMenuPanel;
import com.teqlip.gui.panels.teq.TEQMainMenuPanel;
import com.teqlip.gui.panels.utsc.UTSCMainMenuPanel;
import com.teqlip.database.Login;
import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.exceptions.ConnectionFailedException;

@SuppressWarnings("serial")
public class LoginMenu extends BaseFrame {
	
	private final static String LOGIN = "login";
	private final static String HELP = "help";
	
	private BoxLayout layout;
	private Container container;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginMenu() {
		super("Welcome to TEQLIP!");
		
		new DatabaseSelectHelper();
		container = getContentPane();
		layout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		JComponent usernamePane = createUsernameInput();
		JComponent passwordPane = createPasswordPane();
		JComponent buttonPane = createButtonPane();
		
		add(usernamePane);
		add(passwordPane);
		add(buttonPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (LOGIN.equals(cmd)) {
			// Process the password and what to do next.
			String userInput = usernameField.getText();
			char[] passInput = passwordField.getPassword();
			String pwd = String.copyValueOf(passInput);

			// if password correct
			try {
				if (!DatabaseSelectHelper.checkUsernameExist(userInput)) {
					JOptionPane.showMessageDialog(null, "User does not exist", "Fail Login - no such user", JOptionPane.ERROR_MESSAGE);
				}
				// if user exist, check password
				else if(DatabaseSelectHelper.checkLoginInfo(userInput, pwd)) {
					// security reason - set string pwd to empty string
					pwd = "";
					int userID = DatabaseSelectHelper.getUserID(userInput);
					int roleid = -1;
					RoleEnum roleEnum = null;

          roleid = DatabaseSelectHelper.getUserRoleId(userID);
					this.dispose();
					
					for (RoleEnum re : roleEnum.enumIteration()) {
					  if (re.getRoleId() == roleid) {
					    roleEnum = re.getRoleEnum();
					  }
					}
					if (roleEnum != null) {
					  roleEnum.changeMenu(userInput);
					} else {
						JOptionPane.showMessageDialog(null, "Unknown error", "Fail Login - role not found", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect password", "Fail Login - wrong password", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
			
			//Zero out the possible password, for security.
            Arrays.fill(passInput, '0');
		} else if (HELP.equals(cmd)) {
			JOptionPane.showMessageDialog(null, "The password is 'test'", "Help", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected JComponent createButtonPane() {
		JPanel p = new JPanel();
		JButton loginBtn = JGuiHelper.createButton("Login", this, LOGIN);
		JButton helpBtn = JGuiHelper.createButton("Help", this, HELP);
		
		p.add(loginBtn);
		p.add(helpBtn);
		
		return p;
	}
	
	protected JComponent createUsernameInput() {
		usernameField = new JTextField(20);
		return createLabelWithField("Username:", usernameField);
	}
	
	protected JComponent createPasswordPane() {
		passwordField = new JPasswordField(20);
		return createLabelWithField("Password:", passwordField);
	}
	
	private JComponent createLabelWithField(String labelText, JTextField textfield) {
		JPanel p = new JPanel(new FlowLayout());
		JLabel userlbl = new JLabel(labelText);
		
		p.add(userlbl);
		p.add(textfield);
		
		return p;
	}	
	
}
