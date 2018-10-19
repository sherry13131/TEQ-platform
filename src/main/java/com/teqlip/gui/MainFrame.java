package com.teqlip.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class MainFrame {

    private static JFrame initJFrame() {
        JFrame frame = new JFrame("GUI");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setMinimumSize(new Dimension(1920, 1080));
        return frame;
    }

    public static void main(String[] args) {
        JFrame frame  = initJFrame();
        JButton start = new JButton("Start");
        start.addActionListener(new LoginMenu(frame, null));
        frame.add(start);
        frame.pack();
        frame.setVisible(true);
    }

}
