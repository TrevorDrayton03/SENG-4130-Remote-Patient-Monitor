package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends FrameTemplate {

    private JPanel LoginScreen;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPasswordField passwordTextField;
    private JButton registerButton;

    public LoginFrame() {
        initalizeFrame(LoginScreen);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getPassword().toString();
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
