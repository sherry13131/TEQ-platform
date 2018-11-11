package com.teqlip.gui.panels.org;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;

@SuppressWarnings("serial")
public class OrgDownloadTemplatePanel extends BodyPanel {

    public class ActionConsts {
		private static final String DOWNLOAD = "download";
		private static final String CANCEL = "cancel";
	}

    private static final Dimension FILENAME_FIELD_DIMENSION = new Dimension(80, 30);

	private BoxLayout layout;
	private JComboBox filenameList;

    public OrgDownloadTemplatePanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        
        JComponent filenamePane = createFilenamePane();
        JComponent buttonsPane = createButtonPane();
        
        add(filenamePane);
        add(buttonsPane);
    }

    // TODO need method to get all templates from db

    public JComponent createFilenamePane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel filenamelbl = new JLabel("Select template to download:");
    	// this.filenameList = JGuiHelper.createComboBox(
    	// this.filenameList.setPreferredSize(FILENAME_FIELD_DIMENSION);
    	
    	p.add(filenamelbl);
    	// p.add(this.filenameList);
    	
    	return p;
    }

    public JComponent createButtonPane() {
    	JPanel p = JGuiHelper.createPanelFlow();
    	
    	JButton downloadBtn = JGuiHelper.createButton("Download", this, ActionConsts.DOWNLOAD);
    	JButton cancelBtn = JGuiHelper.createButton("Cancel", this, ActionConsts.CANCEL);
    	
    	p.add(downloadBtn);
    	p.add(cancelBtn);
    	
    	return p;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    	String cmd = ae.getActionCommand();

        if (cmd.equals(ActionConsts.DOWNLOAD)) {
        	// TODO Connect to Database
            // use getSelectdItem() from the JComboBox
        } else if (cmd.equals(ActionConsts.CANCEL)) {
        	super.goToMenu(MenuOptions.ORG_MAIN_MENU);
        }
    }

}
