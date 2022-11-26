package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        User user = User.getInstance();
        HeartRate hr = HeartRate.getInstance();
        Temperature temp = Temperature.getInstance();
        BreathingRate breathingRate = BreathingRate.getInstance();
        FrontPage frontPage = new FrontPage(user);
        frontPage.initalizeFrame(frontPage.getFrontPagePanel());
        Timer timer = new Timer();
        // set a timer to run every second (creates a background thread that the timer uses)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // each data object is collecting new data
                try{
                    hr.add();
                } catch (Exception e) {
                    System.out.println("Exception at HR Data Collection: " + e);
                }
                try{
                    temp.add();
                } catch (Exception e) {
                    System.out.println("Exception at Temperature Data Collection: " + e);
                }
                try{
                    breathingRate.add();
                } catch (Exception e) {
                    System.out.println("Exception at BR Data Collection: " + e);
                }
            }
        }, 1000, 1000);
    }
}
