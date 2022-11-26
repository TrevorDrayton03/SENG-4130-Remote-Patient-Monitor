package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends FrameTemplate {
    public JPanel getRegisterPanel() {
        return RegisterPanel;
    }
    public void setRegisterPanel(JPanel registerPanel) {
        RegisterPanel = registerPanel;
    }
    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }
    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
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
    private JPanel RegisterPanel;
    private JPasswordField passwordTextField;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel frameLabel;
    private JTextField userTypeTextField;

    public RegisterFrame(User user) {
        initalizeFrame(RegisterPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(user);
                setVisible(false);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(usernameTextField.getText().isBlank() || passwordTextField.getPassword().toString().isBlank() || usernameTextField.toString().isBlank() && (user.doesUserExist(usernameTextField.getText())))) {
                    if (user.doesUserExist(usernameTextField.getText())) {
                        JOptionPane.showMessageDialog(RegisterPanel, "Username taken.");
                    } else {
                        String username = usernameTextField.getText();
                        String password = passwordTextField.getText();
                        String userType = userTypeTextField.getText();
                        user.addUser(username, password);
                        user.addUserType(username, userType);
                        JOptionPane.showMessageDialog(RegisterPanel, "Account created");
                        new LoginFrame(user);
                        setVisible(false);
                    }
                } else if (!(usernameTextField.toString().equals("patient") || usernameTextField.toString().equals("clinician"))) {
                    JOptionPane.showMessageDialog(RegisterPanel, "Valid user types are 'patient' and 'clinician'");
                } else {
                    JOptionPane.showMessageDialog(RegisterPanel, "All fields required.");
                }
            }
        });
    }
}
