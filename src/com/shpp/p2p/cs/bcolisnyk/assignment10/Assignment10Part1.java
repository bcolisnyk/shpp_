package com.shpp.p2p.cs.bcolisnyk.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Assignment10Part1 {
    // String with math formula
    private static String formula;
    // HashMap with variables from formula and value of this
    private static HashMap<String, Double> variables = new HashMap<>();
    // array with auxiliary symbols
    private static String[] symbolsArr;
    // HashMap with auxiliary symbols and formulas of this symbols
    private static HashMap<String, String> symbolFormulas = new HashMap<String, String>();
    // HashMap with auxiliary symbols and value of this symbols
    private static HashMap<String, Double> symbolValue = new HashMap<String, Double>();
    // ArrayList with math action of parsed formula
    private static ArrayList<String> actions = new ArrayList<>();

    /**
     * This program get formula and variables from String[] args,
     * parse this formula in String, and parse variables in HM <String, Double>,
     * format sing in formula, parse this formula on math actions, and calculate value
     * <p>
     * args[0] - formula
     * args[1 - ...] = variables
     */
    public static void main(String[] args) {
        // check formula on correct format
        formula = args[0];
        formula = formula.replace(" ", "");
        formula = formula.replace(",", ".");
        System.out.println(" Formula : " + formula);
        for (int i = 0; i < formula.length(); i++) {
            //check on only lowercase variables
            if (Character.isUpperCase(formula.charAt(i))) {
                System.out.println("Sorry, use only lower case in formula, change it");
                System.exit(0);
            }
            //check on incorrect symbols
            if (!isMathSign(formula.charAt(i)) && !Character.isDigit(formula.charAt(i)) &&
                    !Character.isLetter(formula.charAt(i)) && !(formula.charAt(i) == '.')) {
                System.out.println("Incorrect symbols in formula");
                System.exit(0);
            }
        }

        // put variables in HashMap
        String[] varParts;
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                varParts = args[i].split("=");
                variables.put(varParts[0], Double.parseDouble(varParts[1]));
            }

            // Check keys for lower case
            Set<String> setKeys = variables.keySet();
            for (String key : setKeys) {
                if (Character.isUpperCase(key.charAt(0))) {
                    System.out.println("Please, enter only lower case variables ");
                    System.exit(0);
                }
            }
        }


        // format String with formula
        if (formula.charAt(0) == '-') {
            formula = "0" + formula;
        }
        //format raise to pow in formula
        formula = formula.replace("^-", "&"); // raising to a negative exponent

        // while raising a negative number to even power
        // this number will be positive
        int powIndex = formula.indexOf('^');
        int checkNextIndex;
        if (prevSign(formula, powIndex) != 0) {
            while (powIndex != -1) {
                if (formula.charAt(prevSign(formula, powIndex) - 1) == '-') {
                    double d1 = 0;
                    if (Character.isDigit(formula.charAt(powIndex + 1))) {
                        d1 = Double.parseDouble(formula.substring(powIndex + 1, nextSign(formula, powIndex)));
                    } else {
                        try {
                            d1 = variables.get(Character.toString(formula.charAt(powIndex + 1)));
                        } catch (Exception e) {
                            System.out.println("Variables from your HashMap isn`t found");
                            System.exit(0);
                        }
                    }
                    if (d1 % 2 == 0) {
                        formula = formula.substring(0, prevSign(formula, powIndex)) + '-' + formula.substring(prevSign(formula, powIndex));
                    }
                }
                powIndex = formula.indexOf('^', powIndex);
                checkNextIndex = formula.indexOf('^', powIndex + 1);
                if (checkNextIndex == -1) break;
                powIndex = checkNextIndex;
            }
        }

        powIndex = formula.indexOf('&');
        if (prevSign(formula, powIndex) != 0) {
            while (powIndex != -1) {
                if (formula.charAt(prevSign(formula, powIndex) - 1) == '-') {
                    double d1 = 0;
                    if (Character.isDigit(formula.charAt(powIndex + 1))) {
                        d1 = Double.parseDouble(formula.substring(powIndex + 1, nextSign(formula, powIndex)));
                    } else {
                        try {
                            d1 = variables.get(Character.toString(formula.charAt(powIndex + 1)));
                        } catch (Exception e) {
                            System.out.println("Variables from your HashMap isn`t found");
                            System.exit(0);
                        }
                    }
                    if (d1 % 2 == 0) {
                        formula = formula.substring(0, prevSign(formula, powIndex)) + '-' + formula.substring(prevSign(formula, powIndex));
                    }
                }
                powIndex = formula.indexOf('&', powIndex);
                checkNextIndex = formula.indexOf('&', powIndex + 1);
                if (checkNextIndex == -1) break;
                powIndex = checkNextIndex;
            }
        }

        //format signs in formula
        formula = formula.replace("--", "+");
        formula = formula.replace("++", "+");
        formula = formula.replace("*-", "#"); // multiplication by a negative number
        formula = formula.replace("/-", "$"); // division by a negative number

        System.out.println(" Formated formula : " + formula);

        // fill array with auxiliary symbols for parsing formula
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        symbolsArr = new String[symbols.length()];
        for (int i = 0; i < symbols.length(); i++) {
            symbolsArr[i] = Character.toString(symbols.charAt(i));
        }

        //parse formula, calculate and show results
        parseFormula();
        double result = calculate();
        System.out.println(" Variables : " + variables);
        System.out.println(" Actions : " + actions);
        System.out.println(" Formulas of auxiliary symbols : " + symbolFormulas);
        System.out.println(" Numbers of auxiliary symbols : " + symbolValue);
        System.out.println(" Result : " + result);
    }

    /**
     * This method calculate value of formula.
     * For calculating, you need have :
     * - String formula - formatted string with formula
     * - HashMap <String, Double> - hashmap with value of variables in formula
     * - ArrayList actions - arraylist with parsed math actions
     * - HashMap <String, String> - hashmap with auxiliary symbols and auxiliary formulas of this symbol
     * - HashMap <Double, Double> - hashmap with auxiliary symbols and value of this symbols
     *
     * @return number value of formula
     */
    private static double calculate() {
        double res = 0;
        int signIndex; //index of math sing in action
        double d1;     //value of first variable
        double d2;     //value of second variable

        // calculate all actions
        for (String s : actions) {
            //signIndex with sign char
            signIndex = getIndexSign(s);
            //find number value of first Double
            if (Character.isDigit(s.charAt(0))) {
                d1 = Double.parseDouble(s.substring(0, signIndex));
            } else if (Character.isUpperCase(s.charAt(0))) {
                d1 = symbolValue.get(String.valueOf(s.charAt(0)));
            } else
                try {
                    d1 = variables.get(String.valueOf(s.charAt(0)));
                } catch (Exception exception) {
                    System.out.println("Variables from your HashMap isn`t found");
                    res = 0;
                    break;
                }

            //find number value of second double
            if (Character.isDigit(s.charAt(signIndex + 1))) {
                d2 = Double.parseDouble(s.substring(signIndex + 1, s.length()));
            } else if (Character.isUpperCase(s.charAt(signIndex + 1))) {
                d2 = symbolValue.get(String.valueOf(s.charAt(signIndex + 1)));
            } else
                try {
                    d2 = variables.get(String.valueOf(s.charAt(signIndex + 1)));
                } catch (Exception exception) {
                    System.out.println("Variables from your HashMap isn`t found");
                    res = 0;
                    break;
                }

            //calculate number value of action
            if (s.charAt(signIndex) == '$') {
                res = -(d1 / d2);
            }

            if (s.charAt(signIndex) == '#') {
                res = -(d1 * d2);
            }

            if (s.charAt(signIndex) == '&') {
                res = Math.pow(d1, -d2);
            }

            if (s.charAt(signIndex) == '^') {
                res = Math.pow(d1, d2);
            }

            if (s.charAt(signIndex) == '*') {
                res = d1 * d2;
            }

            if (s.charAt(signIndex) == '/') {
                res = d1 / d2;
            }

            if (s.charAt(signIndex) == '-') {
                res = d1 - d2;
            }

            if (s.charAt(signIndex) == '+') {
                res = d1 + d2;
            }

            //add number value of symbol in HashMap
            if (symbolFormulas.get(s) != null) {
                symbolValue.put(symbolFormulas.get(s), res);
            }
        }

        return res;
    }

    /**
     * This method parse formula on math actions
     * All actions put in ArrayList, and change this action in formula on
     * auxiliary symbol. This symbol put in HashMap with formula of symbol
     */
    private static void parseFormula() {
        //add two auxiliary String for parsing on actions
        String mainString = formula;
        String subString;
        //array with signs index
        int[] index = new int[4];
        //auxiliary int for find minimal index
        int min = -1;
        //count of active auxiliary symbols
        int activeSign = 0;

        while (true) {
            //change String while count of math actions will not equal 1
            if (countSigns(mainString) == 1) {
                actions.add(mainString);
                break;
            }

            subString = mainString;
            while (true) {
                //change current first math action of formula
                if (countSigns(subString) == 1) {
                    break;
                }
                // exponentiation precedence - from right to left,
                // find first action exponentiation
                index[0] = -1;
                for (int i = mainString.length() - 1; i > 0; i--) {
                    if (mainString.charAt(i) == '&' || mainString.charAt(i) == '^') {
                        index[0] = i;
                        break;
                    }
                }
                // if we find exponentiation - change substring and go find next action
                if (index[0] != -1) {
                    subString = changeString(subString, index[0]);
                    continue;
                }

                // precedence of multiplication and division - from left to right
                // find minimal multiplication and division sign, and change substring
                index[0] = mainString.indexOf('*');
                index[1] = mainString.indexOf('#');
                index[2] = mainString.indexOf('/');
                index[3] = mainString.indexOf('$');
                if (index[0] != -1) min = index[0];
                if (index[1] != -1) min = index[1];
                if (index[2] != -1) min = index[2];
                if (index[3] != -1) min = index[3];

                for (int i = 0; i < index.length; i++) {
                    if (index[i] < min && index[i] != -1)
                        min = index[i];
                }
                if (min != -1) {
                    subString = changeString(subString, min);
                    min = -1;
                    continue;
                }

                // priority add and subtract - from left to right,
                // find minimal sign and change substring
                index[0] = mainString.indexOf('+');
                index[1] = mainString.indexOf('-');
                if (index[0] == -1 && index[1] != -1)
                    min = index[1];
                if (index[0] != -1 && index[1] == -1)
                    min = index[0];

                if (index[0] != -1 && index[1] != -1) {
                    if (index[0] < index[1]) min = index[0];
                    else min = index[1];
                }
                subString = changeString(subString, min);
            }
            //add action in ArrayList
            actions.add(subString);
            //change action on symbol from array
            mainString = mainString.replace(subString, symbolsArr[activeSign]);
            //put action in HashMap
            symbolFormulas.put(subString, symbolsArr[activeSign]);
            activeSign++;

        }
    }

    /**
     * This method cut math action from formula,
     * from previous math sign to next math sign.
     *
     * @param s     - String with main formula
     * @param index - Index of current math sign
     * @return changed string
     */
    private static String changeString(String s, int index) {
        return s.substring(
                prevSign(s, index),
                nextSign(s, index));
    }

    /**
     * Get index of first math sign in formula
     * if sign isn`t found - return -1
     *
     * @param s - String with formula
     * @return index of first sign
     */
    private static int getIndexSign(String s) {
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            if (isFormatedSign(s.charAt(i))) {
                res = i;
                break;
            }
        }

        return res;
    }

    /**
     * Find index of previous math sign,
     * if sign isn`t found - return 0
     *
     * @param s     - String with formula
     * @param index - index of current math sign
     * @return index of previous math sign
     */
    private static int prevSign(String s, int index) {
        int res = 0;
        for (int i = index - 1; i >= 0; i--) {
            if (isFormatedSign(s.charAt(i))) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

    /**
     * Find index of next math sign,
     * if sign isn`t found - return last index of formula
     *
     * @param s     - String with formula
     * @param index - index of current math sign
     * @return index of next math sign
     */
    private static int nextSign(String s, int index) {
        int res = s.length();
        for (int i = index + 1; i < s.length(); i++) {
            if (isFormatedSign(s.charAt(i))) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * Counts quantity of math signs in formula
     *
     * @param s - String with formula
     * @return - quantity of sings in formula
     */
    private static int countSigns(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isFormatedSign(s.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method check is char math sing and new formatted sings,
     *
     * @param ch - char with symbol
     * @return if ch - math sign, or formated sign  - true
     * else - false
     */
    private static boolean isFormatedSign(char ch) {
        return ch == '-' || ch == '+' || ch == '/' || ch == '*' || ch == '^' || ch == '#' || ch == '&' ||
                ch == '$';
    }

    /**
     * This method check is char math sign
     *
     * @param ch - char with symbol
     * @return if ch - math sign - true,
     * else - false
     */
    private static boolean isMathSign(char ch) {
        return ch == '-' || ch == '+' || ch == '/' || ch == '*' || ch == '^';
    }
}
