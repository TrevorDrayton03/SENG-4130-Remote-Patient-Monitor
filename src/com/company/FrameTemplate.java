package com.company;

import javax.swing.*;

public abstract class FrameTemplate extends JFrame {
    protected final void initalizeFrame(JPanel PanelName){
        setContentPane(PanelName);
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

}
