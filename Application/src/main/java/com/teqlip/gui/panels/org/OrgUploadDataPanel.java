package com.teqlip.gui.panels.org;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.io.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.database.*;
import com.teqlip.exceptions.*;

@SuppressWarnings("serial")
public class OrgUploadDataPanel extends BodyPanel {

	public class ActionConsts {
		private static final String BROWSE = "browse";
		private static final String UPLOAD = "upload";
		private static final String CANCEL = "cancel";
	}
	
	
	private BoxLayout layout;
	private JTextField pathField;
	
    public OrgUploadDataPanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        
        JComponent pathPane = createPathPane();
        JComponent buttonsPane = createButtonPane();
        
        add(pathPane);
        add(buttonsPane);
    }
    
    public JComponent createPathPane() {
    	// The parent panel will consist of the Path on top, then another panel below which contains the textfield
    	// and browse button horizontally
    	JPanel parent = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel pathLbl = new JLabel("Path:");
    	
    	JPanel child = JGuiHelper.createPanelBox(BoxLayout.LINE_AXIS);
    	this.pathField = JGuiHelper.createTextField();
    	JButton browseBtn = JGuiHelper.createButton("Browse...", this, ActionConsts.BROWSE);
    	child.add(this.pathField);
    	child.add(browseBtn);
    	
    	parent.add(pathLbl);
    	parent.add(child);
    	
    	return parent;
    }
    
    public JComponent createButtonPane() {
    	JPanel p = JGuiHelper.createPanelFlow();
    	
    	JButton submitBtn = JGuiHelper.createButton("Upload", this, ActionConsts.UPLOAD);
    	JButton cancelBtn = JGuiHelper.createButton("Cancel", this, ActionConsts.CANCEL);
    	
    	p.add(submitBtn);
    	p.add(cancelBtn);
    	
    	return p;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
    	String cmd = ae.getActionCommand();

        if (ActionConsts.BROWSE.equals(cmd)) {
        	OrgUploadDialogBox dialogBox = new OrgUploadDialogBox(this, this.pathField);
        	dialogBox.showOpenDialog();
        } else if (ActionConsts.UPLOAD.equals(cmd)) {
        	// TODO Connect to Database
          if (!pathField.getText().equals("")) {
//              tryAddFileData(pathField.getText());
              try {
                boolean success = DatabaseInsertHelper.insertTemplateDataHelper(pathField.getText());
                if (success) {
                  JOptionPane.showMessageDialog(null, "Data uploaded succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                  super.goToMenu(MenuOptions.ORG_MAIN_MENU);
                }
              } catch (SQLException e) {
                e.printStackTrace();
              } catch (ParseException e) {
                e.printStackTrace();
              }
               
          } else {
            JOptionPane.showMessageDialog(null, "Please choose the file to upload", "Need file", JOptionPane.ERROR_MESSAGE);
          }
        } else if (ActionConsts.CANCEL.equals(cmd)) {
        	super.goToMenu(MenuOptions.ORG_MAIN_MENU);
        }
    }

}
