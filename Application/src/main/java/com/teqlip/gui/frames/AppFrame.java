package com.teqlip.gui.frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class AppFrame extends BaseFrame implements ActionListener {
	private static final String LOGOUT = "logout";
	
    protected JPanel bodyPanel = null;
    protected String username;
    protected String userRole;
    
    private BoxLayout layout;
    private Container container;
    
    public AppFrame(String username, String userRole) {
    	super("TEQLIP");
		setMinimumSize(new Dimension(800, 400));
    	
    	this.username = username;
    	this.userRole = userRole;
    	
    	container = getContentPane();
    	layout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
		setLayout(layout);
		
		// This will be the name and logout button that's present on every screen
		JComponent logoutPane = createLogoutPane();
		
		// This will be the body of the app that changes. We will create a border for anesthetics
        int top=10;
        int left=10;
        int bottom=10;
        int right=10;
        Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		
		this.bodyPanel = new JPanel();
		this.bodyPanel.setBorder(border);
		
		add(logoutPane);
		add(this.bodyPanel);
    }
    
    public void setBody(JPanel newBody) {
    	this.bodyPanel.removeAll();
    	this.bodyPanel.add(newBody);
    	this.revalidate();
    	this.repaint();
    }

    protected void restart() {
       this.pack();
       this.revalidate();
       this.repaint();
    }

    public void close() {
        this.remove(this.bodyPanel);
        this.bodyPanel = null;
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