package com.company;

import javax.swing.*;


public class LoginFrame extends JFrame {

    private JPanel LoginScreen;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPasswordField passwordTextField;

    public LoginFrame(User user) {
        setContentPane(LoginScreen);
        setTitle("Login");
        setSize(450, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText(); // getPassword()

            boolean isLogin = user.doLogin(username, password);

            if (isLogin) {
                System.out.println("You logged in!");
            }
        });
    }
}
