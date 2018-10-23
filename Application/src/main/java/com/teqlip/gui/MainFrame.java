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
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    public static void main(String[] args) {
        // JFrame frame  = initJFrame();
        
        LoginMenu2 login = new LoginMenu2();
        login.pack();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        
/*        JButton start = new JButton("Start");
        start.addActionListener(new LoginMenu(frame, null));
        frame.add(start);
        frame.pack();*/
        // frame.setVisible(true);
    }

}