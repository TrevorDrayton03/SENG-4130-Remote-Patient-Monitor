package com.company;

import java.util.Iterator;

public interface Data {
    Iterator<Double> createIterator();

    void add();

    void print();

    Double getRandomValue(int min, int max);

    public void addAndPrint();
}
