package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame {

    private JPanel LoginScreen;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    public LoginFrame() {
        setContentPane(LoginScreen);
        setTitle("Login");
        setSize(450, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = usernameTextField.getText();
            }
        });
    }
}
