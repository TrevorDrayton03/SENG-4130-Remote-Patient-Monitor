package com.company;
import java.util.*;
import java.lang.*;


public class HeartRate implements Data {
    ArrayList<Double> heartRate = new ArrayList<Double>();

    public HeartRate() {
    }

    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = heartRate.iterator();
        return iterator;
    }

    public void add() {
        heartRate.add(0, getRandomValue(40, 140));
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
