package com.company;

import java.util.Iterator;

public interface Data {
    /**
     *
     * @return A double iterator for the data that needs to be iterated through
     */
    Iterator<Double> createIterator();

    /**
     * Add a new value to the arraylist of the data
     */
    void add();
    void print(); //used for testing purposes
    /**
     *
     * @param min
     * @param max
     * @return A random value between the max and min
     */
    Double getRandomValue(int min, int max);
    public void addAndPrint(); //used for testing

}
