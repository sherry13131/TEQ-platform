package com.teqlip.gui.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.teqlip.gui.frames.AppFrame;

@SuppressWarnings("serial")
abstract class BodyPanel extends JPanel implements ActionListener {
	
	protected AppFrame main;
	
	public enum MenuOptions {
		MAIN_MENU,
		CREATE_ACCOUNT,
		UPLOAD_TEMPLATE,
		DOWNLOAD_TEMPLATE,
	}
	
	public BodyPanel(AppFrame main) {
		this.main = main;
	}
	
	protected BodyPanel goToMenu(MenuOptions menu) {
		BodyPanel newMenu = null;
		
		switch (menu) {
			case MAIN_MENU:
				newMenu = new TEQMainMenuPanel(main);
				break;
			case CREATE_ACCOUNT:
				newMenu = new TEQCreateAccountPanel(main);
				break;
			case UPLOAD_TEMPLATE:
				newMenu = new TEQUploadTemplatePanel(main);
				break;
			case DOWNLOAD_TEMPLATE:
				return null;
		}
		
		this.main.setBody(newMenu);
		return newMenu;
	}
	
	@Override
	public void setLayout(LayoutManager layout) {
		super.setLayout(layout);
		super.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
}