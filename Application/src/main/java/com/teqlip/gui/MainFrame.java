package com.teqlip.gui;

import java.awt.*;
import javax.swing.*;

import com.teqlip.gui.frames.LoginMenu;

public class MainFrame {

	// Not need anymore. Could be useful in the future. - anthony
	/*
	 * public static JFrame initJFrame() { JFrame frame = new JFrame("GUI");
	 * frame.getContentPane().setLayout(new FlowLayout()); frame.setSize(1920,
	 * 1080); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * frame.setDefaultLookAndFeelDecorated(true); frame.setMinimumSize(new
	 * Dimension(1920, 1080)); return frame; }
	 */

	public static void main(String[] args) {

		LoginMenu login = new LoginMenu();
		login.packAndShow();

		// TEQMainMenu test = new TEQMainMenu();
		// test.packAndShow();
		//
	}

}