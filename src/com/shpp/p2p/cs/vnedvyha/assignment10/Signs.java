package com.shpp.p2p.cs.vnedvyha.assignment10;
/** Class, which  */
public class Signs {

    /** Char-array of available signs */
    private static final char[] signs = {'^', '*',  '/', '+', '-'};
    /** Number of turns, in which mathematical operations should be done */
    public static final int ORDERS = 3;
    /** Does mathematical operations
     *  @param a first operand
     *  @param b second operand
     *  @param symbol sign */
    public static double operate(double a, double b, String symbol) {
        return switch (symbol) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "^" -> Math.pow(a, b);
            default -> 0;
        };
    }
    /** Designed for working inside of loops.
     *  Returns sign, according to turn of operations
     *  @param i turn of operation
     *  @return sign on this turn */
    public static int order(char i) {
        return switch (i) {
            case '^' -> 1;
            case '*', '/' -> 2;
            case '+', '-' -> 3;
            default -> 0;
        };
    }

    /** Checks, if char is sign from "signs"
     *  @param ch examining char
     *  @return result */
    public static boolean isSign(char ch) {
        for (char sign : signs) {
            if (ch == sign) return true;
        }
        return false;
    }
}
