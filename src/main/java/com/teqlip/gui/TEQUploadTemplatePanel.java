package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class TEQUploadTemplatePanel extends BasePanel {

    public TEQUploadTemplatePanel(JFrame frame, JPanel panel, String username) {
        this.frame = frame;
        this.panel = panel;
        this.username = username;
    }

    public void actionPerformed(ActionEvent ae) {
        this.close();
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Label");
        JTextField labelField = new JTextField();
        this.panel.add(label);
        this.panel.add(labelField);
        
        JLabel path = new JLabel("Path");
        JTextField pathField = new JTextField();
        this.panel.add(path);
        this.panel.add(pathField);

        JButton browse = new JButton("Browse...");
        browse.addActionListener(new TEQUploadDialogBox(this.frame, this.panel, this.username, pathField));
        this.panel.add(browse);

        JButton submit = new JButton("Upload");
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new TEQMainMenu(this.frame, this.panel, this.username));
        this.panel.add(submit);
        this.panel.add(cancel);

        this.frame.getContentPane().add(this.panel);
        this.restart();
    }

}
