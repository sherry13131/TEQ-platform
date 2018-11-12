package com.teqlip.gui;

import com.teqlip.email.ChangedPassEmail;
import com.teqlip.email.EmailHandler;
import com.teqlip.email.EmailInterface;
import com.teqlip.email.NoReplyEmail;
import com.teqlip.gui.frames.*;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.gui.panels.teq.TEQMainMenuPanel;

public class MainFrame {

	public static void main(String[] args) {

		runLoginMenu();

		// runMainMenu();

		// runEmail();
	}

	// These just make it easier to test
	private static void runLoginMenu() {
		LoginMenu login = new LoginMenu();
		login.packAndShow();
	}

	private static void runMainMenu() {
		AppFrame main = new AppFrame("Bob", "YEET");
		main.packAndShow();
		TEQMainMenuPanel body = new TEQMainMenuPanel(main);
		body.goToMenu(MenuOptions.MAIN_MENU);
	}
	
	private static void runEmail() {
		EmailInterface email = new NoReplyEmail("ay yo its me", "anthony.leung.canada@gmail.com", "waddup");
		EmailInterface email2 = new ChangedPassEmail("pat meheiny", "anthony.leung.canada@gmail.com");
		
		EmailHandler.addEmail(email);
		EmailHandler.addEmail(email2);
		EmailHandler.sendEmails();
	}
}
