package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends FrameTemplate{
    private JPanel RegisterPanel;
    private JPasswordField passwordTextField;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JButton registerButton;

    public RegisterFrame(){
        initalizeFrame(RegisterPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                setVisible(false);
            }
        });
    }
}
