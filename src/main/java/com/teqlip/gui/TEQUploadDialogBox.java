package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class TEQUploadDialogBox extends BasePanel implements ActionListener {

    private JTextField field;

    public TEQUploadDialogBox(JFrame frame, JPanel panel, String username, JTextField field) {
        this.frame = frame;
        this.panel = panel;
        this.username = username;
        this.field = field;
    }    

    public void actionPerformed(ActionEvent ae) {
        JFileChooser c = new JFileChooser();
        int rVal = c.showOpenDialog(this.panel);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            this.field.setText(c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName());
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            this.field.setText("");
        }
    }  
}
