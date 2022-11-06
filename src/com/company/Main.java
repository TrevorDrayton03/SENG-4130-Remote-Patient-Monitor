package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        // initialize singletons: user, heart rate, temperature, breathing rate
        User user = User.getInstance();
        HeartRate hr = HeartRate.getInstance();
        Temperature temp = Temperature.getInstance();
        BreathingRate breathingRate = BreathingRate.getInstance();

        // initialize the front page frame and pass it the user (for the purpose of lab8, we can assume we go straight to the lab8 frame)
        FrontPage frontPage = new FrontPage(user);
        frontPage.initalizeFrame(frontPage.getFrontPagePanel());

        // initialize timer object
        Timer timer = new Timer();

        // schedule the timer object to run every second (creates a background thread that the timer runs on)
        // the timer simulates the collection of new heart rate, temperature, and breathing rate data
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
