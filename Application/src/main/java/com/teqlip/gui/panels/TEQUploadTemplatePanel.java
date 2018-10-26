package com.teqlip.gui.panels;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.sun.xml.internal.ws.api.Component;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

import javafx.scene.layout.BorderPane;

@SuppressWarnings("serial")
public class TEQUploadTemplatePanel extends BodyPanel {

	public class ActionConsts {
		private static final String BROWSE = "browse";
		private static final String UPLOAD = "upload";
		private static final String CANCEL = "cancel";
	}
	
	private BoxLayout layout;
	
    public TEQUploadTemplatePanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        
        JComponent filenamePane = createFilenamePane();
        JComponent pathPane = createPathPane();
        JComponent buttonsPane = createButtonPane();
        
        Dimension size = this.getSize();
        Dimension newSize = new Dimension((int) size.getWidth() / 3, (int) size.getHeight());
        
        int top=10;
        int left=10;
        int bottom=10;
        int right=10;
        Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
        this.setBorder(border);
        add(filenamePane);
        add(pathPane);
        add(buttonsPane);
    }

    public JComponent createFilenamePane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
    	JLabel filenamelbl = new JLabel("Name file as:");
    	JTextField textField = JGuiHelper.createTextField();
    	
    	p.add(filenamelbl);
    	p.add(textField);
    	
    	return p;
    }
    
    public JComponent createPathPane() {
    	// The parent panel will consist of the Path on top, then another panel below which contains the textfield
    	// and browse button horizontally
    	JPanel parent = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	parent.setAlignmentY(LEFT_ALIGNMENT);
    	
    	JLabel pathLbl = new JLabel("Path:");
    	pathLbl.setAlignmentX(LEFT_ALIGNMENT);
    	
    	JPanel child = JGuiHelper.createPanelBox(BoxLayout.LINE_AXIS);
    	JTextField textField = JGuiHelper.createTextField();
    	JButton browseBtn = JGuiHelper.createButton("Browse...", this, ActionConsts.BROWSE);
    	child.add(textField);
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
        	
        } else if (cmd.equals(ActionConsts.UPLOAD)) {
        	
        } else if (cmd.equals(ActionConsts.CANCEL)) {
        	main.setBody(new TEQMainMenuPanel(main));
        }
    }

}
