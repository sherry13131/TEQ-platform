package com.teqlip.gui.frames;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.TEQMainMenuPanel;
import com.teqlip.database.Login;

@SuppressWarnings("serial")
public class LoginMenu extends BaseFrame {
	
	private final static String LOGIN = "login";
	private final static String HELP = "help";
	
	private BoxLayout layout;
	private Container container;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private Login db = new Login();
	
	public LoginMenu() {
		super("Welcome to TEQLIP!");
		
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
			if (db.checkUserName(userInput)) {
				JOptionPane.showMessageDialog(null, "User does not exist", "Fail Login - no such user", JOptionPane.ERROR_MESSAGE);
			}
			// if user exist, check password
			else if(db.checkLoginInfo(userInput, pwd)) {
				// security reason - set string pwd to empty string
				pwd = "";
				String role = db.getRoleString(db.getUserRoleID(userInput));
				this.dispose();
				
				// check user role - might need to use some design pattern here...
				if (role.equalsIgnoreCase("teqlip")) {
					AppFrame main = new AppFrame(userInput, "TEQ Project Staff");
					main.setBody(new TEQMainMenuPanel(main));
					
					main.packAndShow();
				} else if (role.equalsIgnoreCase("org")) {
					AppFrame main = new AppFrame(userInput, "Participating Organization Volunteer");
					main.setBody(new OrgMainMenuPanel(main));
					
					main.packAndShow();
				} else if (role.equalsIgnoreCase("utsc")) {
					AppFrame main = new AppFrame(userInput, "UTSC Project Staff");
					main.setBody(new UTSCMainMenuPanel(main));
					
					main.packAndShow();
				} else {
					JOptionPane.showMessageDialog(null, "Unknown error", "Fail Login - role not found", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect password", "Fail Login - wrong password", JOptionPane.ERROR_MESSAGE);
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
	
//	private static boolean isPasswordCorrect(char[] input) {
//		boolean isCorrect = true;
//		char[] correctPassword = "test".toCharArray();
//		
//		if (input.length != correctPassword.length) {
//			isCorrect = false;
//		} else {
//			isCorrect = Arrays.equals(input, correctPassword);
//		}
//		
//		// Zero out for security
//		Arrays.fill(correctPassword, '0');
//		
//		return isCorrect;
//	}
	
	
}
