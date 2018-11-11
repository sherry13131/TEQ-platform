package com.teqlip.gui.panels;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class TEQUploadTemplatePanel extends BodyPanel {

	public class ActionConsts {
		private static final String BROWSE = "browse";
		private static final String UPLOAD = "upload";
		private static final String CANCEL = "cancel";
	}
	
	private static final Dimension FILENAME_FIELD_DIMENSION = new Dimension(80, 30);
	
	private BoxLayout layout;
	private JTextField filenameField;
	private JTextField pathField;
	
    public TEQUploadTemplatePanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        
        JComponent filenamePane = createFilenamePane();
        JComponent pathPane = createPathPane();
        JComponent buttonsPane = createButtonPane();
        
        add(filenamePane);
        add(pathPane);
        add(buttonsPane);
    }

    public JComponent createFilenamePane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel filenamelbl = new JLabel("Name file as:");
    	this.filenameField = JGuiHelper.createTextField();
    	this.filenameField.setPreferredSize(FILENAME_FIELD_DIMENSION);
    	
    	p.add(filenamelbl);
    	p.add(this.filenameField);
    	
    	return p;
    }
    
    public JComponent createPathPane() {
    	// The parent panel will consist of the Path on top, then another panel below which contains 
    	// the textfield and browse button horizontally
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

        if (cmd.equals(ActionConsts.BROWSE)) {
        	TEQUploadDialogBox dialogBox = new TEQUploadDialogBox(this);
        	dialogBox.showOpenDialog();
        	
        	this.filenameField.setText(dialogBox.getFileName());
        	this.pathField.setText(dialogBox.getPath());
        } else if (cmd.equals(ActionConsts.UPLOAD)) {
        	// TODO Connect to Database
        } else if (cmd.equals(ActionConsts.CANCEL)) {
        	super.goToMenu(MenuOptions.MAIN_MENU);
        }
    }

}
