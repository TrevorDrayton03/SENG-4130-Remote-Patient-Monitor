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
    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = breathRateData.iterator();
        return iterator;
    }
    public void add() {
        breathRateData.add(0, getRandomValue(8, 20));
    }
    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " BREATHING RATE");
    }
    public Double getRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextDouble(max - min) + min;
    }
    public void addAndPrint() {
        add();
        print();
    }
}
