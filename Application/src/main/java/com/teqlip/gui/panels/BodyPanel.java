package com.teqlip.gui.panels;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
abstract class BodyPanel extends JPanel implements ActionListener {
	
	protected AppFrame main;
	
	public BodyPanel(AppFrame main) {
		this.main = main;
	}
	
	public void changeTo(BodyPanel body) {
		this.main.setBody(body);
	}
	
	@Override
	public void setLayout(LayoutManager layout) {
		super.setLayout(layout);
		super.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
}