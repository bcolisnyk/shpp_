package com.shpp.p2p.cs.bcolisnyk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    /**
     * in this program user enters any other valid number
     * if this number is even, program divide by 2 this number
     * if this number is odd, program multiply by 3 this number and adds 1
     * program will run while will not result 1
     */
    public void run() {
        //enter user's number
        int number = readInt("Enter a number: ");

        //if users number == 1 or 0, ask for another number
        while (checkEgdeCases(number)) {
            number = tryOtherNumber(number);
        }

        // if number is even, number = n / 2
        // if number is add, number = 3n + 1
        while (number != 1) {
            if (checkEvenNumber(number)) {
                println(number + " is even so I take half : " + (number / 2));
                number = number / 2;
            } else {
                println(number + " is odd so I make 3n + 1 : " + (3 * number + 1));
                number = 3 * number + 1;
            }
        }
    }

    /* if user enters incorrect number
    * ask for another number */
    private int tryOtherNumber(int number) {
        println(" Sorry, but try other number (");
        return number = readInt("Enter a number : ");
    }

    /* check users number for edge cases*/
    private boolean checkEgdeCases(int number) {
        return number == 1 || number == 0;
    }

    /* check number for even */
    private boolean checkEvenNumber(int number) {
        return number % 2 == 0;
    }

}