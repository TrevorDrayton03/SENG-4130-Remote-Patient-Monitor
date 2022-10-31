package com.company;
import java.util.*;
import java.lang.*;


public class Temperature implements Data {
    ArrayList<Double> temperature = new ArrayList<Double>();

    public Temperature() {
    }

    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = temperature.iterator();
        return iterator;
    }

    public void add() {
        temperature.add(0, getRandomValue(34, 39));
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
