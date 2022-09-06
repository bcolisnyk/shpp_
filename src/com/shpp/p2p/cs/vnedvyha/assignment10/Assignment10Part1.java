package com.shpp.p2p.cs.vnedvyha.assignment10;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Calculator, which works on String-array, which enters to main-function.
 */
public class Assignment10Part1 implements Errors {
    // 3+A 1. 3 + 2 = 5
            // 2. a + 3
    // 1.2*43 = A  A = 312312
    // 2.
    /**
     * Calculates the formula, which is incoming in args
     *
     * @param args formula and variables
     *             Prints out an error or result.
     */
    public static void main(String[] args) {
        try {
            Checker.check(args);
            // reads formulas.txt, parses it to HashMap of <String, ArrayList<String>>
            FileManager.read();
            // tries to find, if this formula was already parsed
            var parsedFormula = FileManager.searchFormula(args[0]);
            // parses variables
            var variables = ParserVariables.parse(args);
            if (parsedFormula == null) {
                // if there's no such parsed formula, we parse it now
                var formula = ParserFormula.parse(args[0]);
                // write down to formulas.txt
                FileManager.putFormula(args[0], formula);
                // print out result
                System.out.println(calculate(formula, variables));
            } else {
                System.out.println(calculate(parsedFormula, variables));
                System.out.println("I've used already parsed formula!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates by replacing all variables with their values,
     * then parse from ParserFormula is called
     *
     * @param formula   Array-list of parsed formula
     * @param variables Hash-map of variables and their values
     * @return result of operations
     */
    private static double calculate(ArrayList<String> formula, HashMap<Character, Double> variables) {
        // replaces all variables with doubles
        replace(formula, variables);
        // goes throw formula 3 times, each time does operations
        // first iteration - ^, second - /,*, third - -,+
        for (int i = 1; i <= Signs.ORDERS; i++) {
            for (int j = 0; j < formula.size(); j++) {
                var el = formula.get(j);
                // if this element is just a sign, and it's turn to do its operation - do it
                if (el.length() == 1 && i == Signs.order(el.charAt(0))) {
                    changeList(formula, j--);
                }
            }
        }
        return Double.parseDouble(formula.get(0));
    }

    /**
     * Changes the list, adding result of operation, deleting used args
     *
     * @param splitted splitted into ArrayList formula
     * @param i        index on which there's a sign, left and right elements are numbers
     */
    private static void changeList(ArrayList<String> splitted, int i) {
        double firstNumber = Double.parseDouble(splitted.get(i - 1));
        double secondNumber = Double.parseDouble(splitted.get(i + 1));
        double result = Signs.operate(firstNumber, secondNumber, splitted.get(i));
        splitted.add(i - 1, String.valueOf(result));
        // Deletes 3 used elements
        for (int j = 0; j < 3; j++) {
            splitted.remove(i);
        }
    }

    /**
     * Replaces variables with values from hashmap
     *
     * @param formula   Array-list of parsed formula
     * @param variables Hash-map of variables and their values
     */
    private static void replace(ArrayList<String> formula, HashMap<Character, Double> variables) {
        for (int i = 0; i < formula.size(); i++) {
            char symbol = formula.get(i).charAt(0);
            boolean variableFinded = false;
            // if first symbol in String is letter - tries to find this letter in hashmap
            if (Character.isLetter(symbol)) {
                for (var key : variables.keySet()) {
                    if (symbol == key) {
                        formula.set(i, String.valueOf(variables.get(symbol)));
                        variableFinded = true;
                        break;
                    }
                }
                if (!variableFinded) {
                    // If there's no such variable in variables hashmap
                    throw new IllegalArgumentException(ERRORS[14]);
                }
            }
        }
    }
}
