package com.teqlip.gui.panels;

import java.awt.event.*;
import javax.swing.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class TEQCreateAccountPanel extends BodyPanel {
	
	private BoxLayout layout;
	
	public class ActionConsts {
		private final static String SUBMIT = "submit";
		private final static String CANCEL = "cancel";
	}

    public static final String[] ACCOUNT_FIELDS = {
        "Username",
        "Email"
    };
    public JTextField[] textFields = new JTextField[TEQCreateAccountPanel.ACCOUNT_FIELDS.length];

    public TEQCreateAccountPanel(AppFrame main) {
    	super(main);
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        
        JComponent fields = createFieldsPane();
        JComponent buttons = createButtonPane();
        
        add(fields);
        add(buttons);
    }
    
    public JComponent createFieldsPane() {
    	JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    	
        for (int i = 0; i < TEQCreateAccountPanel.ACCOUNT_FIELDS.length; i++) {
            JLabel label = new JLabel(TEQCreateAccountPanel.ACCOUNT_FIELDS[i]);
            this.textFields[i] = new JTextField();
            p.add(label);
            p.add(this.textFields[i]);
        }

    	return p;
    }
    
    public JComponent createButtonPane() {
    	JPanel p = JGuiHelper.createPanelFlow();
    	
    	JButton submitBtn = JGuiHelper.createButton("Submit", this, ActionConsts.SUBMIT);
    	JButton cancelBtn = JGuiHelper.createButton("Cancel", this, ActionConsts.CANCEL);
    	
    	p.add(submitBtn);
    	p.add(cancelBtn);
    	
    	return p;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd.equals(ActionConsts.SUBMIT)) {
			
		} else if (cmd.equals(ActionConsts.CANCEL)) {
			super.changeTo(new TEQMainMenuPanel(main));
		}
	}
}