package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class TEQMainMenuPanel extends BodyPanel {
	
	public class ActionConsts {
		private static final String CREATE = "create";
		private static final String UPLOAD = "upload";
		private static final String DOWNLOAD = "download";
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
    	
    	JButton createBtn = JGuiHelper.createButton("Create", this, ActionConsts.CREATE);
    	JButton uploadBtn = JGuiHelper.createButton("Upload", this, ActionConsts.UPLOAD);
    	JButton downloadBtn = JGuiHelper.createButton("Download", this, ActionConsts.DOWNLOAD);
    	
    	p.add(createBtn);
    	p.add(uploadBtn);
    	p.add(downloadBtn);
    	
    	return p;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals(ActionConsts.CREATE)) {
			main.setBody(new TEQCreateAccountPanel(main));
		} else if (cmd.equals(ActionConsts.UPLOAD)) {
			
		} else if (cmd.equals(ActionConsts.DOWNLOAD)) {
			
		}
		
	}
}