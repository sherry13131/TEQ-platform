package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class LoginMenu extends BasePanel {

    public LoginMenu(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent ae) {
        if (this.panel != null) {
            this.close();
        } else {
            this.frame.getContentPane().removeAll();
            this.restart();
        }
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        this.panel.add(new JLabel("Username"));
        JTextField usernameField = new JTextField();
        this.panel.add(usernameField);

        this.panel.add(new JLabel("Password"));
        JTextField passwordField = new JTextField();
        this.panel.add(passwordField);

        JButton login = new JButton("Login");
        login.addActionListener(new TEQMainMenu(this.frame, this.panel, this.username));
        this.panel.add(login);

        this.frame.getContentPane().add(this.panel);
        this.restart();
    }

}
