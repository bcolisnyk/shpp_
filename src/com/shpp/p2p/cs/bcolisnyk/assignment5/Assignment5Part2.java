package com.shpp.p2p.cs.bcolisnyk.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        String result = "";
        // reverse 2 strings
        String str1 = reverse(n1);
        String str2 = reverse(n2);

        int num1; // variable for number from first string
        int num2; // variable for number from second string
        int sum;  // variable for sum first number and second
        int remainder = 0;

        // algorithm if first string > second string
        if (n1.length() > n2.length()) {
            for (int i = 0; i < n2.length(); i++) {
                //get number from symbol of string
                num1 = str1.charAt(i) - '0';
                num2 = str2.charAt(i) - '0';
                // sum of number
                // if sum > 10, move ten to the next digit
                sum = num1 + num2 + remainder;
                if (sum >= 10) {
                    remainder = 1;
                    sum -= 10;
                } else remainder = 0;
                // get symbol from sum and add it in string
                result += (char) (sum + '0');
            }
            // sum the rest of the string
            for (int i = n2.length(); i < n1.length(); i++) {
                num1 = str1.charAt(i) - '0';
                sum = num1 + remainder;
                if (sum >= 10) {
                    remainder = 1;
                    sum -= 10;
                } else remainder = 0;
                // get symbol from sum and add it in string
                result += (char) (sum + '0');
            }
        }

        // algorithm if first string < second string
        if (n1.length() < n2.length()) {
            for (int i = 0; i < n1.length(); i++) {
                //get number from symbol of string
                num1 = str1.charAt(i) - '0';
                num2 = str2.charAt(i) - '0';
                // sum of number
                // if sum > 10, move ten to the next digit
                sum = num1 + num2 + remainder;
                if (sum >= 10) {
                    remainder = 1;
                    sum -= 10;
                } else remainder = 0;
                // get symbol from sum and add it in string
                result += (char) (sum + '0');
            }
            // sum the rest of the string
            for (int i = n1.length(); i < n2.length(); i++) {
                num2 = str2.charAt(i) - '0';
                sum = num2 + remainder;
                if (sum >= 10) {
                    remainder = 1;
                    sum -= 10;
                } else remainder = 0;
                // get symbol from sum and add it in string
                result += (char) (sum + '0');
            }
        }

        // algorithm if first string == second string
        if (n1.length() == n2.length()) {
            for (int i = 0; i < n2.length(); i++) {
                //get number from symbol of string
                num1 = str1.charAt(i) - '0';
                num2 = str2.charAt(i) - '0';
                // sum of number
                // if sum > 10, move ten to the next digit
                sum = num1 + num2 + remainder;
                if (sum >= 10) {
                    remainder = 1;
                    sum -= 10;
                } else remainder = 0;
                // get symbol from sum and add it in string
                result += (char) (sum + '0');
            }
        }

        result = reverse(result);
        return result;
    }

    /*Given a string, returns the reverse of that string.*/
    private String reverse(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            result = text.charAt(i) + result;
        }
        return result;
    }
}