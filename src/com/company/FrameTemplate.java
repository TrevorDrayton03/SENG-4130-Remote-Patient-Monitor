package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class FrameTemplate extends JFrame {
    protected final void initalizeFrame(JPanel PanelName) {
        setContentPane(PanelName);
        setSize(400, 400);
        setVisible(true);
        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
