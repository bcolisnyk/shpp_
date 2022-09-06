package com.shpp.p2p.cs.bcolisnyk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {
    /**
     * this program print in console numbers raised to a power
     */
    public void run() {
        println(raiseToPower(2.0, 3));
        println(raiseToPower(0, 3));
        println(raiseToPower(2.0, 0));
        println(raiseToPower(2.0, -2));
    }

    /**
     * this method raise any number to power
     * parameters: base - number raised to a power
     * exponent - the degree to which they raise base*/
    private double raiseToPower(double base, int exponent) {
        double result;
        // any number in to the power of 0 = 1
        if (exponent == 0) return 1;

        if (exponent > 0) {
            return result = countIfPositivePowerOfNumber(base, exponent);
        } else {
            return result = countIfNegativePowerOfNumber(base, exponent);
        }
    }
    /*
    * if power of number < 0 ,
    * number is inversely proportional to this number in positive power */
    private double countIfNegativePowerOfNumber(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < -exponent; i++) {
            result = result * base;
        }
        result = 1 / result;
        return result;
    }
    /*
    * count number in positive power of number*/
    private double countIfPositivePowerOfNumber(double base, int exponent) {
        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result = result * base;
        }
        return result;
    }

}