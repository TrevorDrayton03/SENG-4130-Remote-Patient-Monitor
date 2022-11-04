package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrontPage extends FrameTemplate {
    private JPanel FrontPagePanel;
    private JButton registerButton;
    private JButton loginButton;

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

    public JPanel getFrontPagePanel() {
        return FrontPagePanel;
    }

    public void setFrontPagePanel(JPanel frontPagePanel) {
        FrontPagePanel = frontPagePanel;
    }

    public FrontPage(User user) {
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
                new RegisterFrame(user);
                setVisible(false);
            }
        });
    }
}
