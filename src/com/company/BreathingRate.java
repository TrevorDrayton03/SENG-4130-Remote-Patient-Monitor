package com.company;

import java.util.*;
import java.lang.*;


public class BreathingRate implements Data {
    ArrayList<Double> breathRate = new ArrayList<Double>();

    public BreathingRate() {
    }

    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = breathRate.iterator();
        return iterator;
    }

    public void add() {
        breathRate.add(0, getRandomValue(8, 20));
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
