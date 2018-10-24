package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class AppFrame extends BaseFrame implements ActionListener {
	private static final String LOGOUT = "logout";
	
    protected JPanel panel = null;
    protected String username;
    protected String userRole;
    
    private BoxLayout layout;
    private Container container;

    public AppFrame(String username, String userRole) {
    	this(username, userRole, new JPanel());
    }
    
    public AppFrame(String username, String userRole, JPanel body) {
    	super("TEQLIP");
		setMinimumSize(new Dimension(800, 400));
    	
    	this.username = username;
    	this.userRole = userRole;
    	
    	container = getContentPane();
    	layout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		// This will be the name and logout button that's present on every screen
		JComponent logoutPane = createLogoutPane();
		
		// This will be the body of the app that changes
		this.panel = body;
		
		add(logoutPane);
		add(this.panel);
    }
    
    protected void setBody(JPanel newBody) {
    	this.remove(this.panel);
    	this.panel = newBody;
    	this.add(this.panel);
    	this.revalidate();
    	this.repaint();
    }

    protected void restart() {
       this.pack();
       this.revalidate();
       this.repaint();
    }
    
    public void UIUpdate() {
    	if (this.isVisible()) {
    		this.revalidate();
    		this.repaint();
    	} else {
    		this.pack();
        	this.setLocationRelativeTo(null);
        	this.setVisible(true);
    	}
    }

    public void close() {
        this.remove(this.panel);
        this.panel = null;
    }
    
    private JComponent createLogoutPane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.X_AXIS);
    	
    	JButton logoutBtn = JGuiHelper.createButton("Logout", this, LOGOUT);
    	logoutBtn.setPreferredSize(new Dimension(80, 5));
    	
    	JLabel username = new JLabel(this.username + ", " + this.userRole);
    	
    	p.add(Box.createHorizontalGlue());
    	p.add(username);
    	p.add(Box.createRigidArea(new Dimension(10, 0)));
    	p.add(logoutBtn);
    	p.add(Box.createRigidArea(new Dimension(10, 0)));
    	
    	return p;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (LOGOUT.equals(cmd)) {
			this.dispose();
			LoginMenu login = new LoginMenu();
			login.packAndShow();
		}
	}
  
}