package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class MainFrame {

	public static JFrame initJFrame() {
		JFrame frame = new JFrame("GUI");
		frame.getContentPane().setLayout(new FlowLayout());
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultLookAndFeelDecorated(true);
		frame.setMinimumSize(new Dimension(1920, 1080));
		return frame;
	}

	public static void main(String[] args) {

		LoginMenu login = new LoginMenu();
		login.packCenterAndShow();

		if (login.isAuthenticated()) {
			JFrame frame = initJFrame();
			frame.pack();
			frame.setVisible(true);
			TEQMainMenu mainMenu = new TEQMainMenu(frame, null, login.getUsername());
			mainMenu.restart();
		}

	}

}