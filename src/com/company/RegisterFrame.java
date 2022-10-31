package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends FrameTemplate{
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

    public RegisterFrame(){
        initalizeFrame(RegisterPanel);
        User user = new User();
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
                if(!(usernameTextField.getText().isBlank() || passwordTextField.getPassword().toString().isBlank())) {
                    user.addUser(usernameTextField.getText(), passwordTextField.getPassword().toString());
                    JOptionPane.showMessageDialog(RegisterPanel, "Account created");
                    new LoginFrame();
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(RegisterPanel,"Username or Password is invalid please try again");
                }
            }
        });
    }
}
