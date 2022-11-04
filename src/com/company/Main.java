package com.company;

import javax.sound.sampled.Line;
import java.util.*;
import javafx.application.Application;

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
                hr.add();
                temp.add();
                breathingRate.add();
            }
        }, 1000, 1000);

        // launches the line chart sample
        Application.launch(AnimatedLineChart.class, args);
    }
}
