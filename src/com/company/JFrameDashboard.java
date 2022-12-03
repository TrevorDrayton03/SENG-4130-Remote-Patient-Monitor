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

    /**
     * Constructor for when the JFrameDashboard class is called, creates clinician's or patient's dashboard
     * @param user
     */
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

    /**
     * Clinician GUI information and frame creation
     */
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

    /**
     * Patient GUI information and frame creation
     */
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

    /**
     * Function need to run the clinicians dashboard
     */
    public static void runTheClinicianThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowClinicianGUI();
            }
        });
    }

    /**
     * Function needed to run the patient dashboard
     */
    public static void runThePatientThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowPatientGUI();
            }
        });
    }

    /**
     * Function needed to stop running a GUI dashboard
     */
    public static void stopRunningTheThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Platform.exit();
                    }
                });
            }
        });
    }
}