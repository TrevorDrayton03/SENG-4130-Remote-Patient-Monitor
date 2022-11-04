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

    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = heartRateData.iterator();
        return iterator;
    }

    public void add() {
        heartRateData.add(0, getRandomValue(40, 140));
    }

    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " HEART RATE");
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
