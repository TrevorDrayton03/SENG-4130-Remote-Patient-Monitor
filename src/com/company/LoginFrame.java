package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends FrameTemplate {
    public JPanel getLoginScreen() {
        return LoginScreen;
    }
    public void setLoginScreen(JPanel loginScreen) {
        LoginScreen = loginScreen;
    }
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }
    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }
    public JButton getLoginButton() {
        return loginButton;
    }
    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }
    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }
    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }
    public JButton getRegisterButton() {
        return registerButton;
    }
    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }
    public JLabel getFrameLabel() {
        return frameLabel;
    }
    public void setFrameLabel(JLabel frameLabel) {
        this.frameLabel = frameLabel;
    }
    private JPanel LoginScreen;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JPasswordField passwordTextField;
    private JButton registerButton;
    private JLabel frameLabel;

    /**
     * Login page constructor to allow the user to login if they've entered valid data
     * or allow them to go to the registration page
     * @param user
     */
    public LoginFrame(User user) {
        initalizeFrame(LoginScreen);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText(); // getPassword().toString() returns a string like as follows: [C@4bf804c2

                boolean isLogin = user.doLogin(username, password);

                if (isLogin) {
                    user.setCurrentUser(username);
                    user.setCurrentUserType(username);
                    String userType = user.getUserType(username);
                    if(userType.equals("patient")) {
                        new JFrameDashboard(user);
                        setVisible(false);
                        dispose();
                    }
                    else if (userType.equals("clinician")) {
                        new JFrameDashboard(user);
                        setVisible(false);
                        dispose();
                    }
                    else {
                        System.out.println("User type is invalid");
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(user);
                setVisible(false);
                dispose();
            }
        });
    }
}
