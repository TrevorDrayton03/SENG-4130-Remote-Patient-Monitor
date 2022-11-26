package com.company;
import java.util.*;
import java.lang.*;


public class Temperature implements Data {
    private static Temperature temperature = new Temperature();
    private ArrayList<Double> tempData = new ArrayList<>();
    public static Temperature getInstance() {
        return temperature;
    }
    public Temperature() {
        getInstance();
    }
    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = tempData.iterator();
        return iterator;
    }
    public void add() {
        tempData.add(0, getRandomValue(34, 39));
    }
    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " TEMPERATURE");
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
