package com.shpp.p2p.cs.bcolisnyk.Lists.ArrayList;

public class Run {
    public static void main(String[] args) {
        ArrayListInt result = new ArrayListInt();
        result.add(1);
        result.add(2);
        result.add(3);
        System.out.println(result);
        for (int i = 0; i < result.length(); i++) {
            System.out.println(result.get(i));
        }
    }
}
