package com.shpp.p2p.cs.molkhovskiy.assignment10;

/*
 * This is the control class for the calculator (class Assignment10Part1). Here we can set
 *  a formula for calculation, in a given range and with a given step, specify the parameters
 *  of the argument used in the formula, and perform a series of calculations.
 *  In this case, the parsing of the formula will happen once.
 */
public class ManagementClass {
    public static final String FORMULA = "5-x^2";
    // My "trash-formula", calculation result is "=66.166666...":
    // public static final String FORMULA = "-100 /20-+-50 *+0,5+20+--2^-+2^--3^ -+-2--2*-3/ -4+2^--3-2-+-+56/--3";

    // argument range:
    public static final double X_MIN = -1;
    public static final double X_MAX = 1;
    // the number of points for which the calculation is made
    public static final int NBR_POINTS = 5;

    public static void main(String[] args) {

        for (int i = 0; i < NBR_POINTS; i++) {
            // step with which the value of the argument changes
            double dx = ((X_MAX - X_MIN) / (NBR_POINTS - 1));
            String argument = "x=" + (X_MIN + dx * i);
            String[] vars = {FORMULA, argument};
            // passing parameters to the calculator - to the main method,
            // as an array of strings
            Assignment10Part1.main(vars);
        }
    }
}
