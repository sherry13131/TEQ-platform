package com.teqlip.gui.frames;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
abstract class BaseFrame extends JFrame implements ActionListener {
	
	public BaseFrame(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultLookAndFeelDecorated(true);
	}
	
    public void packAndShow() {
    	pack();
    	setLocationRelativeTo(null);
    	setVisible(true);
    }
	
}
