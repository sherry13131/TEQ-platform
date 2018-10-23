package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class TEQMainMenu extends BasePanel {

    public TEQMainMenu(JFrame frame, JPanel panel, String username) {
        this.frame = frame;
        this.panel = panel;
        this.username = username;
    }

    public void actionPerformed(ActionEvent ae) {
        this.close();
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        JLabel titleRole = new JLabel("Logged in as " + this.username + ", a TEQLIP project staff");
        this.panel.add(titleRole);
        JButton createAccount = new JButton("Create Account");
        createAccount.addActionListener(new TEQCreateAccountPanel(this.frame, this.panel, this.username));
        this.panel.add(createAccount);
        JButton logout = new JButton("Logout");
        logout.addActionListener(new LoginMenu(this.frame, this.panel));
        this.panel.add(logout);
        this.frame.getContentPane().add(this.panel);
        this.restart();
    }

}
