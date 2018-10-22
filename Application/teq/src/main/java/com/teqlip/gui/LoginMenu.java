package com.teqlip.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginMenu extends JFrame implements ActionListener {
	
	private final static String LOGIN = "login";
	private final static String HELP = "help";
	
	private BoxLayout layout;
	private Container container;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	private boolean authenticated = false;
	
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
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (LOGIN.equals(cmd)) {
			// Process the password and what to do next.
			char[] input = passwordField.getPassword();
			if (isPasswordCorrect(input)) {
				this.authenticated = true;
				dispose();
			} else {
				// Do something else
			}
			
			//Zero out the possible password, for security.
            Arrays.fill(input, '0');
		} else if (HELP.equals(cmd)) {
			// Do something for help
		}
	}
	
	public boolean isAuthenticated() {
		return this.authenticated;
	}
	
	protected JComponent createButtonPane() {
		JPanel p = new JPanel();
		JButton loginBtn = new JButton("Login");
		JButton helpBtn = new JButton("Help");
		
		loginBtn.addActionListener(this);
		loginBtn.setActionCommand(LOGIN);
		
		helpBtn.addActionListener(this);
		helpBtn.setActionCommand(HELP);
		
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
	
	public String getUsername() {
		return this.usernameField.getText();
	}
	
	public void packCenterAndShow() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private static boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		char[] correctPassword = "MACROHARD".toCharArray();
		
		if (input.length != correctPassword.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(input, correctPassword);
		}
		
		// Zero out for security
		Arrays.fill(correctPassword, '0');
		
		return isCorrect;
	}
}
