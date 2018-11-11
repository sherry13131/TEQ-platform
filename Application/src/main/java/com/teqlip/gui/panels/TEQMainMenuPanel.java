package com.teqlip.gui.panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class TEQMainMenuPanel extends BodyPanel {
	
	public class ActionConsts {
		private static final String CREATE = "create";
		private static final String UPLOAD = "upload";
        private static final String QUERY = "query";
	}
	
	
	private BoxLayout layout;
    
    public TEQMainMenuPanel(AppFrame main) {
    	super(main);
 
      	layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(layout);

    	JLabel msgs = createMessagePane();
		
    	// Create the flowlayout panel for creating account, uploading document, downloading document
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
    	
    	JButton createBtn = JGuiHelper.createButton("Create/Manage Accounts", this, ActionConsts.CREATE);
    	JButton uploadBtn = JGuiHelper.createButton("Upload/Manage Files", this, ActionConsts.UPLOAD);
    	JButton queryBtn = JGuiHelper.createButton("Query", this, ActionConsts.QUERY);
    	
    	p.add(createBtn);
    	p.add(uploadBtn);
        p.add(queryBtn);
    	
    	return p;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals(ActionConsts.CREATE)) {
			super.goToMenu(MenuOptions.CREATE_ACCOUNT);
		} else if (cmd.equals(ActionConsts.UPLOAD)) {
			super.goToMenu(MenuOptions.UPLOAD_TEMPLATE);
		} else if (cmd.equals(ActionConsts.QUERY)) {
			super.goToMenu(MenuOptions.QUERY);
		}
		
	}
}
