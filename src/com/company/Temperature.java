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
    /**
     *
     * @return A double iterator for the breathing rate of the patient
     */
    public Iterator<Double> createIterator() {
        Iterator<Double> iterator = tempData.iterator();
        return iterator;
    }
    /**
     * Adding a random value from 34 to 39 to the arraylist that stores breathing rate data
     */
    public void add() {
        tempData.add(0, getRandomValue(34, 39));
    }

    public void print() {
        Iterator<Double> iterator = createIterator();
        System.out.println(iterator.next() + " TEMPERATURE");
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
