package com.company;
import java.util.*;
import java.lang.*;


public class HeartRate implements Data {
    private static HeartRate heartRate = new HeartRate();
    ArrayList<Double> heartRateData = new ArrayList<Double>();
    public static HeartRate getInstance() {
        return heartRate;
    }
    public HeartRate() {
        getInstance();
    }
    /**
     *
     * @return A double iterator for the heart rate of the patient
     */
    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = heartRateData.iterator();
        return iterator;
    }
    /**
     * Adding a random value from 40 to 140 to the arraylist that stores breathing rate data
     */
    public void add() {
        heartRateData.add(0, getRandomValue(40, 140));
    }
    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " HEART RATE");
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
