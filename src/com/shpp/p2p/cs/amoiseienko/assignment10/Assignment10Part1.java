package com.shpp.p2p.cs.amoiseienko.assignment10;

/*
 * File: Assignment10Part1.java
 * ---------------------
 * This program realize a simple calculator and supports five
 * actions: exponentiation, multiplication, division, adding, subtracting.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Assignment10Part1 {

    /**
     * Program receiving expression to calculate and variables definition
     * as an array of arguments in main method.
     */
    public static void main(String[] args) {

        if (args.length > 0) {

            /* First argument is always an expression to calculate */
            String expression = args[0].replaceAll(" ", "");
            expression = expression.replaceAll(",", ".");

            HashMap<String, Double> variables = null;
            /* Next arguments are a variables' definition if they exists */
            if (args.length > 1) {
                variables = strToMap(Arrays.copyOfRange(args, 1, args.length));
            }

            System.out.println(calculate(expression, variables));

        } else {
            System.out.println("There is nothing to calculate.");
        }
    }

    /**
     * This is a main method of expression processing.
     * @param expression receives an expression to calculate in String format
     * @param variables receives a list of variables with its values
     */
    private static double calculate(String expression, HashMap<String, Double> variables) {
        /* If variables list isn't empty, replacing variables in expression to it's values */
        if (variables != null) {
            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                expression = expression.replaceAll(entry.getKey(), entry.getValue().toString());
            }
        }
        /* Removing unnecessary "=" symbol */
        if (expression.endsWith("=")) {
            expression = expression.substring(0, expression.length() - 1);
        }
        /* Checking for right format of expression */
        validateExpression(expression);

        System.out.print(expression + "=");

        if (expression.startsWith("-") || expression.startsWith("+")) expression = "0" + expression;

        /* This loop simplifies an expression until it have an exponentiation */
        while (expression.contains("^")) {
            expression = simplify(expression, true);
        }
        /* This loop simplifies an expression until it have a multiplication or division */
        while (expression.contains("*") || expression.contains("/")) {
            expression = simplify(expression, false);
        }
        /* This loop simplifies a groups of addition and subtraction signs */
        while (expression.contains("++") || expression.contains("+-") ||
                expression.contains("-+") || expression.contains("--")) {
            expression = expression.replaceAll("\\+\\+|--", "\\+");
            expression = expression.replaceAll("\\+-|-\\+", "-");
        }

        return calculate(expression);
    }

    /**
     * This method validates expression format
     * @param expression receives an expression to validate
     * Method checks for undefined variables, for expression is not ends or starts with math operations
     * and for illegal math operations combination in expression.
     */
    private static void validateExpression(String expression) {
        if (expression.matches("(.*)[a-zA-Z](.*)")) {
            System.err.println("There is some undefined variables in expression");
            System.exit(1);
        } else if (expression.matches("(.*)(\\+\\*|\\*\\+|\\+/|/\\+|-\\*|-/|\\*/|/\\*)(.*)")) {
            System.err.println("There is something wrong with math operations in expression");
            System.exit(1);
        } else if (expression.startsWith("*") || expression.startsWith("/")) {
            System.err.println("Illegal start of expression");
            System.exit(1);
        } else if (expression.endsWith("*") || expression.endsWith("/") ||
                expression.endsWith("+") || expression.endsWith("-")) {
            System.err.println("Illegal end of expression");
            System.exit(1);
        }
    }

    /**
     * This is a main method of processing exponentiation, multiplication or division.
     * It processed one operation for one method call
     * @param expression receives an expression to simplify in String format
     * @param pow receives an information of what to process
     */
    private static String simplify(String expression, boolean pow) {

        int multIndex = expression.indexOf("*");
        multIndex = multIndex == -1 ? Integer.MAX_VALUE : multIndex;
        int divIndex = expression.indexOf("/");
        divIndex = divIndex == -1 ? Integer.MAX_VALUE : divIndex;
        /* Choosing an index of math operation which is closer to the beginning of expression */
        int index = Math.min(multIndex, divIndex);

        /* Dividing an expression on two parts by operation symbol index */
        String leftPart;
        String rightPart;
        if (pow) {
            leftPart = expression.substring(0, expression.lastIndexOf("^"));
            rightPart = expression.substring(expression.lastIndexOf("^") + 1);
        } else {
            leftPart = expression.substring(0, index);
            rightPart = expression.substring(index + 1);
        }

        /* Getting a left operand to calculate */
        double operand1;
        if (getOperationIndex(leftPart, true) != -1) {
            operand1 = Double.parseDouble(leftPart.substring(getOperationIndex(leftPart, true) + 1));
            leftPart = leftPart.substring(0, getOperationIndex(leftPart, true) + 1);
        } else {
            operand1 = Double.parseDouble(leftPart);
            leftPart = "";
        }

        /* Getting a right operand to calculate */
        double operand2;
        if (getOperationIndex(rightPart, false) != -1) {
            operand2 = Double.parseDouble(rightPart.substring(0, getOperationIndex(rightPart, false)));
            rightPart = rightPart.substring(getOperationIndex(rightPart, false));
        } else {
            operand2 = Double.parseDouble(rightPart);
            rightPart = "";
        }

        /* Returning a new expression with one operation done */
        if (pow) {
            return leftPart + Math.pow(operand1, operand2) + rightPart;
        } else if (expression.charAt(index) == '/') {
            if (operand2 == 0) {
                System.err.println("Error (division by zero)");
                System.exit(1);
            }
            return leftPart + (operand1 / operand2) + rightPart;
        } else {
            return leftPart + (operand1 * operand2) + rightPart;
        }
    }

    /**
     * This is a method of processing simple operations, such as addition and subtraction.
     * It processed whole expression for one method call
     * @param expression receives an expression to calculate in String format
     */
    private static double calculate(String expression) {

        /* Checking for expression with one number */
        if (getOperationIndex(expression, false) == -1) {
            return Double.parseDouble(expression);
        }

        double result = 0;
        char operation = '+';

        while (expression.length() > 0) {
            double number;
            /* Cutting a numbers of expression one by one till the end */
            if (getOperationIndex(expression, false) != -1) {
                number = Double.parseDouble(expression.substring(0, getOperationIndex(expression, false)));
                expression = expression.substring(getOperationIndex(expression, false));
            } else {
                number = Double.parseDouble(expression);
                expression = "";
            }

            /* Making an action defined in previous iteration */
            if (operation == '+') {
                result += number;
            } else {
                result -= number;
            }

            /* Choosing an operation for next action */
            if (!expression.isEmpty()) {
                operation = expression.charAt(0);
                expression = expression.substring(1);
            }
        }
        return result;
    }

    /**
     * This is a method of finding the nearest symbol of math operation
     * @param expression receives an expression to search in
     * @param fromTheEnd defines a finding direction: from the beginning or the end of expression
     */
    private static int getOperationIndex(String expression, boolean fromTheEnd) {
        if (expression.isEmpty()) return -1;
        if (fromTheEnd) {
            for (int i = expression.length() - 1; i >= 0; i--) {
                if (isOperationSymbol(expression.charAt(i)) &&
                        (i == 0 || expression.charAt(i - 1) != 'E')) return i;
            }
        } else {
            for (int i = 1; i < expression.length(); i++) {
                if (isOperationSymbol(expression.charAt(i)) &&
                        expression.charAt(i - 1) != 'E') return i;
            }
        }
        return -1;
    }

    /**
     * This is a method checks a symbols to match a math operation symbols
     * @param symbol receives a symbol to check
     */
    private static boolean isOperationSymbol(char symbol) {
        return "^*/+-".indexOf(symbol) != -1;
    }

    /**
     * This method returns a HashMap of variables with its double values from its String format
     * @param variables receive a String array of variables
     */
    private static HashMap<String, Double> strToMap(String[] variables) {
        HashMap<String, Double> variablesAsMap = new HashMap<>();
        for (String var : variables) {
            String variable = var.replaceAll(" ", "");
            variable = variable.replaceAll(",", ".");
            double value = Double.parseDouble(variable.substring(variable.indexOf("=") + 1));
            variable = variable.substring(0, variable.indexOf("="));
            variablesAsMap.put(variable, value);
        }
        return variablesAsMap;
    }
}