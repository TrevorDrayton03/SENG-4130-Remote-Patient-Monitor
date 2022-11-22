package com.company;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameDashboard extends JFrame {
    static JButton logoutButton = new JButton("Logout");
    static JFrame frame = new JFrame("Swing Application");
    public JFrameDashboard(User user) {
        runTheThing();
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

    public static void initAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFXPanel jfxPanel = new JFXPanel();
        frame.getContentPane().add(jfxPanel, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setLayout(new FlowLayout());
        frame.add(logoutButton);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                JavaFXDashboard liveDataFeedLineChart = JavaFXDashboard.getInstance();
                jfxPanel.setScene(liveDataFeedLineChart.chart.getScene());
                liveDataFeedLineChart.play();
            }
        });
    }

    public static void runTheThing() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
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
                        JavaFXDashboard animalLineChart = JavaFXDashboard.getInstance();
                        //animalLineChart.stop();
                        Platform.exit();
                    }
                });
            }
        });
    }
}