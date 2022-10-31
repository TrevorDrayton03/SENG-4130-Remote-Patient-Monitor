package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage extends FrameTemplate{
    private JButton loginButton;
    private JPanel FrontPagePanel;
    private JButton registerButton;

    public FrontPage(){
        initalizeFrame(FrontPagePanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
                setVisible(false);
            }
        });
    }
}
