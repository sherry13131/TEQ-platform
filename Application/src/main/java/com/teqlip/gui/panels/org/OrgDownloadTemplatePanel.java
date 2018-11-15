package com.teqlip.gui.panels.org;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.io.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.database.*;
import com.teqlip.exceptions.*;

@SuppressWarnings("serial")
public class OrgDownloadTemplatePanel extends BodyPanel {

    public class ActionConsts {
		private static final String DOWNLOAD = "download";
		private static final String CANCEL = "cancel";
        private static final String GETTEMP = "get template";
	}

    private static final Dimension FILENAME_FIELD_DIMENSION = new Dimension(80, 30);

	private BoxLayout layout;
	private JComboBox filenameList;

    public OrgDownloadTemplatePanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        JComponent filenamePane = null;
        try {
            filenamePane = createFilenamePane();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComponent buttonsPane = createButtonPane();
        
        add(filenamePane);
        add(buttonsPane);
    }

    public JComponent createFilenamePane() throws SQLException {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel filenamelbl = new JLabel("Select template to download:");
    	List<String> templateList = DatabaseSelectHelper.getTemplatesName();
        String [] templateArray = new String[templateList.size()];
        for (int i = 0; i < templateList.size(); i++) {
            templateArray[i] = templateList.get(i);
        }
    	this.filenameList = JGuiHelper.createComboBox(templateArray, this, null);
    	this.filenameList.setPreferredSize(FILENAME_FIELD_DIMENSION);
    	
    	p.add(filenamelbl);
    	p.add(this.filenameList);
    	
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

        if (ActionConsts.DOWNLOAD.equals(cmd)) {
            String tempName = (String)this.filenameList.getSelectedItem();
            try {
                DatabaseSelectHelper.getTemplateFile(tempName);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Couldn't download template", "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ActionConsts.CANCEL.equals(cmd)) {
        	super.goToMenu(MenuOptions.ORG_MAIN_MENU);
        }
    }

}
