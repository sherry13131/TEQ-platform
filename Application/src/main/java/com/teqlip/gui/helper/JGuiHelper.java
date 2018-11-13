package com.teqlip.gui.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JGuiHelper {

	public static JLabel createLabel(String text, Rectangle placement) {
		JLabel label = (JLabel) createComponent(JGui.LABEL, placement);
		label.setText(text);
		return label;
		
	}
	
	// Note, it seems useless but it could help later by making everything a single font
	public static JComponent createComponent(JGui item, Rectangle placement) {
		switch (item) {
			case LABEL:
				JLabel label = new JLabel();
				label.setBounds(placement);
				return label;
			case TEXT_FIELD:
				JTextField userText = new JTextField(20);
				userText.setBounds(placement);
				return userText;
			case PASSWORD_FIELD:
				JPasswordField passwordText = new JPasswordField(50);
				passwordText.setBounds(placement);
				return passwordText;
			case BUTTON:
				JButton button = new JButton("");
				button.setBounds(placement);
				return button;
		}
		return null;
	}
	
	public static JButton createButton(String text, ActionListener listener, String actionCommand) {
		JButton b = new JButton(text);
		b.setAlignmentX(Component.LEFT_ALIGNMENT);
		b.addActionListener(listener);
		b.setActionCommand(actionCommand);
		return b;
	}
	
	public static JPanel createPanel() {
		JPanel p = new JPanel();
		p.setAlignmentX(Component.LEFT_ALIGNMENT);
		return p;
	}
	
	public static JPanel createPanelBox(int layout_axis) {
		JPanel p = new JPanel();
		BoxLayout pLayout = new BoxLayout(p, layout_axis);
		p.setLayout(pLayout);
		p.setAlignmentX(Component.LEFT_ALIGNMENT);
		return p;
	}
	
	public static JPanel createPanelFlow() {
		JPanel p = new JPanel(new FlowLayout());
		p.setAlignmentX(Component.LEFT_ALIGNMENT);
		return p;
	}
	
	public static JTextField createTextField() {
		JTextField textField = new JTextField(20);
		textField.setMaximumSize(new Dimension(160, 25));
		return textField;
	}

    public static JComboBox createComboBox(String[] options, ActionListener listener, String actionCommand) {
        JComboBox comboBox = new JComboBox(options);
        comboBox.addActionListener(listener);
		comboBox.setActionCommand(actionCommand);
        return comboBox;
    }
}
