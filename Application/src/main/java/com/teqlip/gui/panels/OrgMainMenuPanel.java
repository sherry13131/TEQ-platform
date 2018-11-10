package com.teqlip.gui.panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class OrgMainMenuPanel extends BodyPanel {

    public class ActionConsts {
        private static final String DOWNLOAD = "download";
        private static final String UPLOAD = "upload";
    }

    private BoxLayout layout;

    public OrgMainMenuPanel(AppFrame main) {
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
    	
    	JButton downloadBtn = JGuiHelper.createButton("Download Files", this, ActionConsts.DOWNLOAD);
    	JButton uploadBtn = JGuiHelper.createButton("Upload Data", this, ActionConsts.UPLOAD);
    	
    	p.add(downloadBtn);
        p.add(uploadBtn);
    	
    	return p;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
        if (cmd.equals(ActionConsts.DOWNLOAD)) {
			super.goToMenu(MenuOptions.DOWNLOAD_TEMPLATE);
		} else if (cmd.equals(ActionConsts.UPLOAD)) {
            super.goToMenu(MenuOptions.UPLOAD_DATA);
        }
		
	}

}
