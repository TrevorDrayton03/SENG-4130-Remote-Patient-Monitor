package com.company;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameDashboard extends JFrame {
    static JButton logoutButton = new JButton("Logout");
    static JFrame frame;
    public JFrameDashboard(User user) {
        if(user.getUserType(user.getCurrentUser()).equals("clinician")) {
            frame = new JFrame("Clinician Dashboard");
            runTheClinicianThing();
        }
        else {
            frame = new JFrame("Patient Dashboard");
            runThePatientThing();
        }
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopRunningTheThing();
                new LoginFrame(user);
                frame.setVisible(false);
                dispose();
            }
        });
    }
    public static void initAndShowClinicianGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFXPanel jfxPanel = new JFXPanel();
        frame.getContentPane().add(jfxPanel, BorderLayout.CENTER);
        frame.setSize(1400, 500);
        frame.setLayout(new FlowLayout());
        frame.add(logoutButton);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ClinicianDisplay dashboard = ClinicianDisplay.getInstance();
                jfxPanel.setScene(dashboard.chart.getScene());
                dashboard.play();
            }
        });
    }
    public static void initAndShowPatientGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFXPanel jfxPanel = new JFXPanel();
        frame.getContentPane().add(jfxPanel, BorderLayout.CENTER);
        frame.setSize(1000, 450);
        frame.setLayout(new FlowLayout());
        frame.add(logoutButton);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                PatientDisplay dashboard = PatientDisplay.getInstance();
                jfxPanel.setScene(dashboard.chart.getScene());
                dashboard.play();
            }
        });
    }
    public static void runTheClinicianThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowClinicianGUI();
            }
        });
    }
    public static void runThePatientThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowPatientGUI();
            }
        });
    }
    public static void stopRunningTheThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //JavaFXDashboard dashboard = JavaFXDashboard.getInstance();
                        //dashboard.stop();
                        Platform.exit();
                    }
                });
            }
        });
    }
}