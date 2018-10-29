package com.teqlip.gui.panels;

import javax.swing.*;

public class TEQUploadDialogBox {

	private JPanel panel;
	private String filename;
	private String path;

	private JFileChooser fileChooser;
	private int rVal;

	public TEQUploadDialogBox(JPanel panel) {
		this.panel = panel;
		this.fileChooser = new JFileChooser();
	}

	public void showOpenDialog() {
		this.rVal = fileChooser.showOpenDialog(this.panel);
		
		if (rVal == JFileChooser.APPROVE_OPTION) {
			this.filename = fileChooser.getSelectedFile().getName();
			this.path = fileChooser.getSelectedFile().getPath(); 
		} else if (rVal == JFileChooser.CANCEL_OPTION) {
			this.filename = "";
		}
	}
	
	public String getFileName() {
		return this.filename;
	}
	
	public String getPath() {
		return this.path;
	}
}
