package com.teqlip.gui.panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.sql.*;

public class TEQUploadDialogBox implements ActionListener {

	private JPanel panel;
    private JTextField field;
    
    private JFileChooser c;
    private int rVal;

    public TEQUploadDialogBox(JPanel panel, JTextField field) {
        this.panel = panel;
        this.field = field;
        
        this.c = new JFileChooser();
        this.rVal = c.showOpenDialog(this.panel);
    }    

    public void actionPerformed(ActionEvent ae) {
        if (rVal == JFileChooser.APPROVE_OPTION) {
            this.field.setText(c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName());
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            this.field.setText("");
        }
    }  
}
