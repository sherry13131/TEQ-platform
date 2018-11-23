package com.teqlip.gui.panels;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.panels.org.OrgChangePasswordPanel;
import com.teqlip.gui.panels.org.OrgDownloadTemplatePanel;
import com.teqlip.gui.panels.org.OrgMainMenuPanel;
import com.teqlip.gui.panels.org.OrgUploadDataPanel;
import com.teqlip.gui.panels.teq.TEQCreateAccountPanel;
import com.teqlip.gui.panels.teq.TEQMainMenuPanel;
import com.teqlip.gui.panels.teq.TEQQueryPanel;
import com.teqlip.gui.panels.teq.TEQUploadTemplatePanel;
import com.teqlip.gui.panels.utsc.UTSCDataPanel;
import com.teqlip.gui.panels.utsc.UTSCMainMenuPanel;

@SuppressWarnings("serial")
public abstract class BodyPanel extends JPanel implements ActionListener {
	
	protected AppFrame main;
	
	public enum MenuOptions {
		MAIN_MENU,
		CREATE_ACCOUNT,
		UPLOAD_TEMPLATE,
		DOWNLOAD_TEMPLATE,
        ORG_MAIN_MENU,
        UTSC_MAIN_MENU,
        UPLOAD_DATA,
        QUERY,
        CHANGE_PASSWORD,
        VIEW_DATA
	}
	
	public BodyPanel(AppFrame main) {
		this.main = main;
	}
	
	public BodyPanel goToMenu(MenuOptions menu) {
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
				newMenu = new OrgDownloadTemplatePanel(main);
                break;
            case ORG_MAIN_MENU:
				newMenu = new OrgMainMenuPanel(main);
				break;
            case UTSC_MAIN_MENU:
				newMenu = new UTSCMainMenuPanel(main);
				break;
            case UPLOAD_DATA:
				newMenu = new OrgUploadDataPanel(main);
				break;
            case QUERY:
				newMenu = new TEQQueryPanel(main);
				break;
            case CHANGE_PASSWORD:
                newMenu = new OrgChangePasswordPanel(main);
                break;
            case VIEW_DATA:
                newMenu = new UTSCDataPanel(main);
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
