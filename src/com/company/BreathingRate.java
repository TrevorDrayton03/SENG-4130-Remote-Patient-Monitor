package com.company;

import java.util.*;
import java.lang.*;


public class BreathingRate implements Data {
    private static BreathingRate breathRate = new BreathingRate();
    ArrayList<Double> breathRateData = new ArrayList<Double>();
    public static BreathingRate getInstance() {
        return breathRate;
    }
    public BreathingRate() {
        getInstance();
    }

    /**
     *
     * @return A double iterator for the breathing rate of the patient
     */
    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = breathRateData.iterator();
        return iterator;
    }

    /**
     * Adding a random value from 8 to 20 to the arraylist that stores breathing rate data
     */
    public void add() {
        breathRateData.add(0, getRandomValue(8, 20));
    }

    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " BREATHING RATE");
    }
    /**
     *
     * @param min
     * @param max
     * @return A random value between the max and min numbers specified
     */
    public Double getRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextDouble(max - min) + min;
    }
    public void addAndPrint() {
        add();
        print();
    }
}
