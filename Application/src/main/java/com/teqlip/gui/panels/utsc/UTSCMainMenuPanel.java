package com.teqlip.gui.panels.utsc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;

@SuppressWarnings("serial")
public class UTSCMainMenuPanel extends BodyPanel {

    public class ActionConsts {
        private static final String VIEW = "view";
        private static final String CHANGE_PASSWORD = "change password";
    }

    private BoxLayout layout;

    public UTSCMainMenuPanel(AppFrame main) {
    	super(main);
 
      	layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

    	JLabel msgs = createMessagePane();
		
    	JComponent optionsPane = createOptionsPane();
    	
    	add(msgs);
    	add(optionsPane);
    }

    public JLabel createMessagePane() {
    	String msg = "Welcome to TEQLIP, what would you like to do?";
    	JLabel msglbl = new JLabel(msg);
    	Font msgFont = msglbl.getFont();
    	Font newFont = new Font(msgFont.getName(), msgFont.getStyle(), 15);
    	msglbl.setFont(newFont);
    	return msglbl;
    }

    public JComponent createOptionsPane() {
    	JPanel p = JGuiHelper.createPanelFlow();
    	
    	JButton viewBtn = JGuiHelper.createButton("View Data", this, ActionConsts.VIEW);
    	JButton changePasswordBtn = JGuiHelper.createButton("Change Password", this, ActionConsts.CHANGE_PASSWORD);
    	p.add(viewBtn);
        p.add(changePasswordBtn);
    	
    	return p;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
        if (cmd.equals(ActionConsts.VIEW)) {
          super.goToMenu(MenuOptions.VIEW_DATA);
		} else if (cmd.equals(ActionConsts.CHANGE_PASSWORD)) {
          super.goToMenu(MenuOptions.CHANGE_PASSWORD);
        }
		
	}

}
