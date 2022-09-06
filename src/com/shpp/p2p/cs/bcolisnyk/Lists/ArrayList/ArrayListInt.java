package com.shpp.p2p.cs.bcolisnyk.Lists.ArrayList;


public class ArrayListInt {
    private int[] array;
    private int size;
    private int currentSize;
    private static int addSize = 150;

    ArrayListInt() {
        this.size = addSize;
        this.array = new int[size];
        this.currentSize = 0;
    }

    public void add(int element) {
        if (currentSize >= size) {
            int newSize = size + addSize;
            int[] newArray = new int[newSize];

            for (int i = 0; i < size; i++) {
                array[i] = newArray[i];
            }
            array = newArray;
        }

        array[currentSize] = element;
        currentSize++;
    }

    public int get(int index) {
        return array[index];
    }

    public int length() {
        return currentSize;
    }

    public String toString() {
        String result = String.valueOf(array[0]);

        for (int i = 1; i < currentSize; i++) {
            result += ", " + array[i];
        }

        return "[ " + result + " ]";
    }

}
