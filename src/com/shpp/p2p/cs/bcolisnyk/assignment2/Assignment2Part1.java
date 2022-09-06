package com.shpp.p2p.cs.bcolisnyk.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {
    /*
     * in this method user enters the coefficient of quadratic equation
     * if coefficients are correct, program calculates roots
     * else program gives an error
     */
    public void run() {
        // Enter coefficients
        double a = readDouble(" Please enter a : ");
        double b = readDouble(" Please enter b : ");
        double c = readDouble(" Please enter c : ");
        // if coefficients aren't correct, program gives error
        // else program calculate roots
        if (a == 0)
            println("Sorry, it isn't quadratic equation :( ");
        else calculateRoots(a, b, c);

    }
    /*
     * this method calculates roots of quadratic equation with using discriminant
     * if discriminant less than zero program gives error
     * if discriminant is zero program calculate 1 root
     * if discriminant above zero program calculate 2 roots
     */
    private void calculateRoots(double a, double b, double c) {

        double Discriminant = b * b - (4 * a * c);
        if (Discriminant < 0) {
            println("There are no real roots");
        } else if (Discriminant == 0) {
            double root = -b / (2 * a);
            println("There is one root : " + root);
        } else {
            double root1 = (-b + Math.sqrt(Discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(Discriminant)) / (2 * a);
            println("There are two roots : " + root1 + " and " + root2);

        }
    }
}