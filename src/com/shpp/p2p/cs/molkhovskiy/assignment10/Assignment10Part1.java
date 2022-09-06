package com.shpp.p2p.cs.molkhovskiy.assignment10;
/*
 * This program is a calculator. More precisely, a simplified version of the calculator
 * that supports the following operands: "+", "-", "*", "/" and "^". The program does not
 * support brackets, cyrillic, and other characters, except for the specified operands.
 * When entering decimals, both dots and commas are supported. When entering an argument,
 * it must be written equals ("=") in the formula. The argument name must not start with
 * a number. Spaces are allowed in formulas.
 * The formula must not end with an arithmetic operation sign. You should also avoid
 * combinations of characters that do not make sense: "/*", "-^", "+*", "---" etc.
 * while such combinations are supported: "+-", "--", "*-", "^-" ...
 *
 * The calculator has been tested on a public resource https://www.wolframalpha.com
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Assignment10Part1 {
    /*
     * These are static variables that store the formula and the results of its parsing
     * carried out during the last program call. If the same formula (but with different
     * arguments) enters the main while the program is running, then re-parsing is not
     * performed - the previous result is used.
     */
    // previous formula
    static String baseFormula;
    // array of variables and numbers from the last formula
    static String[] baseVar;
    // list of   symbols from the last formula
    static ArrayList<Character> baseSigns;

    public static void main(String[] args) {
        // As input, the program (in the main method) receives a formula
        // for calculation and arguments (arguments are not required if
        // there are no literal variables in the formula) in the form of
        // an array of strings.
        if (args != null && args.length != 0
                && args[0] != null && !args[0].equals("")) {
            // If the arguments are received, then they are parsed and
            // written to the map, where the argument name is the key
            HashMap<String, Double> arguments = new HashMap<>();
            if (args.length > 1) {
                readArguments(args, arguments);
            }
            // we parse only for an expression that does not match the
            // formula from the previous example
            if (args[0].equals(baseFormula)) {
                System.out.println("Without parsing");
            } else { // parsing:
                String formula = args[0];
                baseFormula = args[0];
                //bring the formula to a canonical form, or to a form convenient for parsing.
                // This means removing all spaces and grouping the arithmetic signs in such
                // a way that there are two arguments around each sign (it is important).
                formula = makeCanonicalForm(formula);
                //now it's easy to create an array of variables and numbers
                baseVar = formula.split("[-+*/^#]");
                // and list of arithmetic symbols
                baseSigns = getSigns(formula);
            }
            // rewrite the arithmetic signs in the working list, from which they can be deleted
            // after performing the corresponding operation
            ArrayList<Character> signs = new ArrayList<>(baseSigns);
            // create a list of numeric values
            ArrayList<Double> numbers = new ArrayList<>(getNumbers(baseVar, arguments));

            // do the calculations:
            double result = calculate(numbers, signs);

            System.out.println(arguments);
            System.out.println(args[0] + " = " + result);
            System.out.println();

        } else {
            System.out.println("No input data");
        }
    }

    // read, check and parse arguments
    private static void readArguments(String[] args, HashMap<String, Double> arguments) {
        for (int i = 1; i < args.length; i++) {
            // remove spaces
            args[i] = deleteSpaces(args[i]);
            // check if there are non-latin letters
            checkLettersFormat(args[i]);
            // check if there are characters other than "="
            checkSymbols(args[i]);
            args[i] = args[i].replace(",", ".");
            // separating the variable name and its value
            String[] parameter = args[i].split("=");
            // we form a map of the formula parameters to be changed
            try {
                arguments.put(parameter[0], Double.parseDouble(parameter[1]));
            } catch (Exception e) {
                System.out.println("Non-numeric argument input...");
                System.exit(0);
            }
        }
    }

    // check characters other than letters, numbers and "="
    private static void checkSymbols(String arg) {
        if (arg == null || arg.equals("") || !Character.isLetter(arg.charAt(0))
        ) {
            System.out.println("Invalid format of argument");
            System.exit(0);
        }
        for (int i = 0; i < arg.length(); i++) {
            if (!Character.isLetter(arg.charAt(i)) &&
                    !Character.isDigit(arg.charAt(i)) &&
                    arg.charAt(i) != '=' &&
                    arg.charAt(i) != '.' &&
                    arg.charAt(i) != ',' &&
                    arg.charAt(i) != '-') {
                System.out.println("Invalid format of argument");
                System.exit(0);
            }
        }
    }

    // bring the expression to the canonical form
    private static String makeCanonicalForm(String formula) {
        // remove all spaces in an expression
        formula = deleteSpaces(formula);
        // check if there are meaningless combinations of arithmetic signs
        checkSignsFormat(formula);
        // check if there is cyrillic
        checkLettersFormat(formula);
        // check characters other than letters, numbers and operands
        checkOtherSymbols(formula);
        // the last character must be either a letter or a number
        checkLastSymbol(formula);
        // for the takeOutMinus() method to work correctly,
        // it is necessary that there are no "hanging" minuses,
        // so the formula will always start with 0+ or 0-
        formula = setFirstSymbol(formula);
        // reduce all sequences of arithmetic signs to a single
        formula = convertToOneSign(formula);
        // in connection with the principle of a single sign,
        // we introduce # to denote a negative degree
        formula = convertExponent(formula);
        // we take out a minus for operations *- and /-
        formula = takeOutMinus(formula);
        return formula;
    }

    // in fact, the most important method that performs calculations
    // after calculating the result, we remove the symbol of
    // the operation from the list, and replace the pair of
    // numbers on which the operation was performed with the
    // result, the length of the list is reduced by 1
    private static double calculate(ArrayList<Double> numbers, ArrayList<Character> arithmeticSigns) {
        // exponentiation will be performed from right to left
        for (int i = arithmeticSigns.size() - 1; i > 0; i--) {
            if (arithmeticSigns.get(i) == '^' || arithmeticSigns.get(i) == '#') {
                if (arithmeticSigns.get(i) == '^') {
                    numbers.set(i, Math.pow(numbers.get(i), numbers.get(i + 1)));
                    numbers.remove(i + 1);
                    arithmeticSigns.remove(i);
                } else if (arithmeticSigns.get(i) == '#') {
                    numbers.set(i, Math.pow(numbers.get(i), -numbers.get(i + 1)));
                    numbers.remove(i + 1);
                    arithmeticSigns.remove(i);
                }
            }
        }
        //the next in priority are multiplication and division, they are performed sequentially
        // from left to right (division by zero handled by interrupt)
        for (int i = 0; i < arithmeticSigns.size(); i++) {
            if (arithmeticSigns.get(i) == '*' || arithmeticSigns.get(i) == '/') {
                if (arithmeticSigns.get(i) == '*') {
                    numbers.set(i, numbers.get(i) * numbers.get(i + 1));
                    numbers.remove(i + 1);
                    arithmeticSigns.remove(i);
                } else if (arithmeticSigns.get(i) == '/') {
                    if (numbers.get(i + 1) != 0) {
                        numbers.set(i, numbers.get(i) / numbers.get(i + 1));
                        numbers.remove(i + 1);
                        arithmeticSigns.remove(i);
                    } else {
                        System.out.println("Division by zero");
                        System.exit(0);
                    }
                }
                //since the list has become shorter by one, it is necessary
                // to go back one position so as not to skip the action
                i--;
            }
        }
        // last-priority addition and subtraction operations
        for (int i = 0; i < arithmeticSigns.size(); i++) {
            if (arithmeticSigns.get(i) == '+' || arithmeticSigns.get(i) == '-') {
                if (arithmeticSigns.get(i) == '+') {
                    numbers.set(i, numbers.get(i) + numbers.get(i + 1));
                    numbers.remove(i + 1);
                    arithmeticSigns.remove(i);
                } else if (arithmeticSigns.get(i) == '-') {
                    numbers.set(i, numbers.get(i) - numbers.get(i + 1));
                    numbers.remove(i + 1);
                    arithmeticSigns.remove(i);
                }
                //since the list has become shorter by one, it is necessary
                // to go back one position so as not to skip the action
                i--;
            }
        }
        return numbers.get(0);
    }

    // method for converting arguments from String format to numeric format
    private static ArrayList<Double> getNumbers(String[] variables, HashMap<String, Double> arguments) {
        ArrayList<Double> numbers = new ArrayList<>();
        for (String variable : variables) {
            // if the first character is a digit, then translate the string to a double,
            // if a symbol, then look for a double in the map by the key equal to the variable
            // in the format string
            if (Character.isDigit(variable.charAt(0)))
                try {
                    numbers.add(Double.parseDouble(variable));
                } catch (Exception e) {
                    System.out.println("Non-numeric element input...");
                    System.exit(0);
                }
                // if there is no variable in the map, then we exit the corresponding message with an interrupt
            else if (arguments.containsKey(variable)) {
                numbers.add(arguments.get(variable));
            } else {
                System.out.println("The specified argument is missing...");
                System.exit(0);
            }
        }
        return numbers;
    }

    // method for creating a list of arithmetic signs
    private static ArrayList<Character> getSigns(String formula) {
        ArrayList<Character> arithmeticSigns = new ArrayList<>();

        for (int i = 0; i < formula.length(); i++) {
            if (formula.charAt(i) == '+' || formula.charAt(i) == '-' ||
                    formula.charAt(i) == '*' || formula.charAt(i) == '/' ||
                    formula.charAt(i) == '^' || formula.charAt(i) == '#') {
                arithmeticSigns.add(formula.charAt(i));
            }
        }
        return arithmeticSigns;
    }

    // the method checks the formula for completeness:
    // the last character must be either a digit or a letter
    private static void checkLastSymbol(String formula) {
        if (!Character.isLetter(formula.charAt(formula.length() - 1)) &&
                !Character.isDigit(formula.charAt(formula.length() - 1))) {
            System.out.println("Invalid format");
            System.exit(0);
        }
    }

    // we give the signs associated with exponentiation to one sign,
    // enter the symbol '#' for a negative degree
    private static String convertExponent(String formula) {
        formula = formula.replace("^-", "#");
        formula = formula.replace("^+", "^");
        return formula;
    }

    // method reduces sequences of arithmetic laws to one single
    // change comma to dot
    private static String convertToOneSign(String formula) {
        while (formula.contains("+-") || formula.contains("-+") ||
                formula.contains("--") || formula.contains("++")) {
            formula = formula.replace("+-", "-");
            formula = formula.replace("-+", "-");
            formula = formula.replace("--", "+");
            formula = formula.replace("++", "+");
        }
        formula = formula.replace("*+", "*");
        formula = formula.replace("/+", "/");
        formula = formula.replace(",", ".");
        return formula;
    }

    // the method handles multiplication and division by a number with a unary minus
    // the minus sign is taken out by changing the sign closest to the left to the opposite one
    private static String takeOutMinus(String formula) {
        for (int i = 0; i < formula.length(); i++) {
            if ((formula.charAt(i) == '*' && formula.charAt(i + 1) == '-') ||
                    (formula.charAt(i) == '/' && formula.charAt(i + 1) == '-')) {
                // we change the character to a space so that it does not interfere
                // if there are several operations of multiplication or division by
                // a number with a unary minus in a row
                formula = changeSymbol(formula, i + 1, ' ');
                for (int j = i; j > 0; j--) {
                    if (formula.charAt(j) == '+') {
                        // change plus to minus
                        formula = changeSymbol(formula, j, '-');
                        break;
                    }
                    if (formula.charAt(j) == '-') {
                        // change minus to plus
                        formula = changeSymbol(formula, j, '+');
                        break;
                    }
                }
            }
        }
        // don't forget to remove the spaces
        formula = deleteSpaces(formula);
        return formula;
    }

    // method to replace a character in a string
    private static String changeSymbol(String formula, int i, char c) {
        char[] formulaNew = formula.toCharArray();
        formulaNew[i] = c;
        return new String(formulaNew);
    }

    // check and set the first character in the formula
    private static String setFirstSymbol(String formula) {
        // formula cannot start with arithmetic signs other than plus and minus
        if (formula.charAt(0) == '*' || formula.charAt(0) == '/' ||
                formula.charAt(0) == '^') {
            System.out.println("Invalid format");
            System.exit(0);
        }
        // the formula will always start with 0+ or 0-
        if (formula.charAt(0) == '-') {
            formula = "0" + formula;
        } else formula = "0+" + formula;
        return formula;
    }

    // find unacceptable combinations of arithmetic signs
    private static void checkSignsFormat(String formula) {
        if (formula.contains("++") ||
                formula.contains("+/") ||
                formula.contains("+*") ||
                formula.contains("+^") ||

                formula.contains("-/") ||
                formula.contains("-^") ||
                formula.contains("-*") ||

                formula.contains("//") ||
                formula.contains("/*") ||
                formula.contains("/^") ||

                formula.contains("**") ||
                formula.contains("*/") ||
                formula.contains("*^") ||

                formula.contains("^*") ||
                formula.contains("^/") ||
                formula.contains("^^") ||

                formula.contains("---")) {
            System.out.println("Invalid format");
            System.exit(0);
        }
    }

    // check if the characters used in the expression are within
    // the block of characters BASIC_LATIN (exclude cyrillic).
    private static void checkLettersFormat(String formula) {
        for (int i = 0; i < formula.length(); i++) {
            if (!Character.UnicodeBlock.of(formula.charAt(i)).
                    equals(Character.UnicodeBlock.BASIC_LATIN)) {
                System.out.println("Invalid format");
                System.exit(0);
            }
        }
    }

    // check if there are other characters in the expression besides
    // letters, numbers, arithmetic signs
    private static void checkOtherSymbols(String formula) {
        for (int i = 0; i < formula.length(); i++) {
            if (!Character.isLetter(formula.charAt(i)) &&
                    !Character.isDigit(formula.charAt(i)) &&
                    formula.charAt(i) != '+' &&
                    formula.charAt(i) != '-' &&
                    formula.charAt(i) != '*' &&
                    formula.charAt(i) != '/' &&
                    formula.charAt(i) != '^' &&
                    formula.charAt(i) != '.' &&
                    formula.charAt(i) != ',') {
                System.out.println("Invalid format");
                System.exit(0);
            }
        }
    }

    // remove all spaces in an expression
    private static String deleteSpaces(String formula) {
        formula = formula.replaceAll("\\s", "");
        return formula;
    }
}

