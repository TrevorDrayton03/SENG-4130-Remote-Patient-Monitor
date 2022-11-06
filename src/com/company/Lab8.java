package com.company;

import javax.swing.*;
import java.awt.*;

public class Lab8 extends FrameTemplate {
    private JPanel Lab8Panel;
    private String userType;

    public Lab8(String userType) {
        // initializes the frame using template method
        initalizeFrame(Lab8Panel); //setVisible(True) calls the paint method

        // assigns this frame's user type from the userType argument
        this.userType = userType;

        // sets title based on user type
        if (userType.equals("patient")) {
            this.setTitle("Patient");
        } else if (userType.equals("clinician")) {
            this.setTitle("Clinician");
        }
    }

    @Override
    public void paint(Graphics graphics) {
        // based on user type, the paint method is loading different images and drawing them
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
