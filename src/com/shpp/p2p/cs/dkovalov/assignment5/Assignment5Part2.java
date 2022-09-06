package com.shpp.p2p.cs.dkovalov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        while (true) {
            String number1 = readLine("Enter first number: ");
            String number2 = readLine("Enter second number: ");
            println(number1 + " + " + number2 + " = " + addNumericStrings(number1, number2));
            println();
        }
    }

    /**
     *this method calculate sum 2 numbers with method "calculate column"
     * @param number1 The first number.
     * @param number2 the second number.
     * @return sum of numbers
     */
    private String addNumericStrings(String number1, String number2) {
        String sum;
        if (longestNum(number1, number2) == number1.length()) {
            sum = number1;
        } else {
            sum = number2;
        }
        int num1, num2, result, rememberNum = 0;
        String resultSumStr;

        for (int i = 1; i <= sum.length(); i++) {
            if (i <= number1.length()) {
                num1 = number1.charAt(number1.length() - i) - '0';
            } else {
                num1 = 0;
            }
            if (i <= number2.length()) {
                num2 = number2.charAt(number2.length() - i) - '0';
            } else {
                num2 = 0;
            }

            if (rememberNum == 1) {
                result = num1 + num2 + rememberNum;
                rememberNum = 0;
            } else {
                result = num1 + num2;
            }
            if (result >= 10) {
                rememberNum = 1;
                if (sum.length() == longestNum(number1, number2)) {
                    sum = "0".concat(sum);
                }
            }

            resultSumStr = Integer.toString(result);
            StringBuilder convertSting = new StringBuilder(sum);
            convertSting.setCharAt(sum.length() - i, resultSumStr.charAt(resultSumStr.length() - 1));
            sum = convertSting.toString();
        }

        if (sum.charAt(0) == '0' && sum.length() > 1) {
            StringBuilder checkNull = new StringBuilder(sum);
            checkNull.delete(0, 1);
            sum = checkNull.toString();
            }
        return sum;
    }

    /**
     *this method return longest number
     * @param num1 first number user
     * @param num2 second number user
     * @return Longest number of the two
     */
    private int longestNum(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return num2.length();
        }
        else {
            return num1.length();
        }
    }
}
