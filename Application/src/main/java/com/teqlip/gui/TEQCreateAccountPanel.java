package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class TEQCreateAccountPanel extends BasePanel {

    public static final String[] ACCOUNT_FIELDS = {
        "Username",
        "Email"
    };
    public JTextField[] textFields = new JTextField[this.ACCOUNT_FIELDS.length];

    public TEQCreateAccountPanel(JFrame frame, JPanel panel, String username) {
        this.frame = frame;
        this.panel = panel;
        this.username = username;
    }

    public void actionPerformed(ActionEvent ae) {
        this.close();
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < this.ACCOUNT_FIELDS.length; i++) {
            JLabel label = new JLabel(this.ACCOUNT_FIELDS[i]);
            this.textFields[i] = new JTextField();
            this.panel.add(label);
            this.panel.add(this.textFields[i]);
        }

        JButton submit = new JButton("Submit");
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new TEQMainMenu(this.frame, this.panel, this.username));
        this.panel.add(submit);
        this.panel.add(cancel);

        this.frame.getContentPane().add(this.panel);
        this.restart();
    }

}