package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

abstract class BasePanel implements ActionListener {
    protected JFrame frame;
    protected JPanel panel = null;
    protected String username;

    protected void restart() {
       this.frame.pack();
       this.frame.revalidate();
       this.frame.repaint();
    }

    public void close() {
        this.frame.remove(this.panel);
        this.panel = null;
    }
  
}