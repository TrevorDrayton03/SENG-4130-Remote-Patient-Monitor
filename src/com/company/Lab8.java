package com.company;

import javax.swing.*;
import java.awt.*;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Lab8 extends FrameTemplate {
    private JPanel Lab8Panel;
    private String userType;

    public Lab8(String userType) {
        initalizeFrame(Lab8Panel); //setVisible(True) calls the paint method
        this.userType = userType;
        if (userType.equals("patient")) {
            this.setTitle("Patient");
        } else if (userType.equals("clinician")) {
            this.setTitle("Clinician");
        }
    }

    @Override
    public void paint(Graphics graphics) {
        if (this.userType.equals("patient")) {
            ImageIcon img = new ImageIcon("images/patient.jpg");
            Image i = img.getImage();
            super.paint(graphics);
            graphics.drawImage(i, 0, 0, this);
        } else if (this.userType.equals("clinician")) {
            ImageIcon img = new ImageIcon("images/clinician.png");
            Image i = img.getImage();
            super.paint(graphics);
            graphics.drawImage(i, 0, 0, this);
        }
        else{
            System.out.println("user type failed");
        }
    }
}
