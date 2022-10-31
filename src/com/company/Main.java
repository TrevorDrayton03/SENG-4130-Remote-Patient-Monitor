package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FrontPage frontPage = new FrontPage();
        frontPage.initalizeFrame(frontPage.getFrontPagePanel());

        // initialize a timer
        Timer timer = new Timer();
        // initialize data objects
        HeartRate hr = new HeartRate();
        Temperature temp = new Temperature();
        BreathingRate breathingRate = new BreathingRate();

        // set a timer to run every second (creates a background thread that the timer uses)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // each data object is collecting new data
                hr.add();
                temp.add();
                breathingRate.add();
            }
        }, 1000, 1000);

    }
}
