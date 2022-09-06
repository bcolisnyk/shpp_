package com.shpp.p2p.cs.bcolisnyk.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

public class Assignment11Part1_0 {
    // String with math formula
    private static String formula;
    // HashMap with variables from formula and value of this
    private static HashMap<String, Double> variables = new HashMap<>();
    // array with auxiliary symbols
    private static String[] symbolsArr;
    private static String auxSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // HashMap with auxiliary symbols and formulas of this symbols
    private static HashMap<String, String> symbolFormulas = new HashMap<String, String>();
    // HashMap with auxiliary symbols and value of this symbols
    private static HashMap<String, Double> symbolValue = new HashMap<String, Double>();
    // ArrayList with math action of parsed formula
    private static ArrayList<String> actions = new ArrayList<>();
    // amount of used auxiliary symbols
    private static int amountSymbols = 0;

    public static void main(String[] args) {
        double result = 0;
        parseInputData(args);
        fillAuxiliaryArr();
        formatFormula();
        parseOnAction();

        result = calculateConst();
        if (actions.size() > 0) {
            result = calculateWithVars();
            System.out.println(result);
        } else {
            System.out.println(result);
        }
        actions.clear();
        variables.clear();
        symbolFormulas.clear();
        symbolValue.clear();
        amountSymbols = 0;


    }


    /**
     * This method parse input data from args array,
     * args[0] - math formula, parsed in String formula
     * args[1 - ...] - variables from this formula, parsed in HashMap variables
     *
     * @param args - input data with formula and variables
     */
    private static void parseInputData(String[] args) {
        if (args.length > 0) {
            formula = args[0].replace(" ", "");
            formula = formula.replace(",", ".");

            if (args.length > 1) {
                String[] vars;
                for (int i = 1; i < args.length; i++) {
                    vars = args[i].replace(" ", "").split("=");
                    variables.put(vars[0], Double.parseDouble(vars[1]));
                }
            }

            for (int i = 0; i < formula.length(); i++) {
                if (!isMathSign(formula.charAt(i)) && Character.isDigit(formula.charAt(i)) &&
                        Character.isLowerCase(formula.charAt(i))) {
                    System.out.println("Incorrect input data, pls, change formula and use only lowercase variables ");
                    return;
                }
            }
        }
    }

    private static void fillAuxiliaryArr() {
        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        symbolsArr = new String[symbols.length()];
        for (int i = 0; i < symbols.length(); i++) {
            symbolsArr[i] = Character.toString(symbols.charAt(i));
        }
    }

    private static void formatFormula() {


        //clean formula
        formula = formula.replace("^+", "^");
        formula = formula.replace("--", "+");
        formula = formula.replace("/+", "/");
        formula = formula.replace("*+", "*");
        formula = formula.replace("(-", "(0-");

        // change math function of new sign
        formula = formula.replace("sin", "!");
        formula = formula.replace("cos", "@");
        formula = formula.replace("tan", "#");
        formula = formula.replace("atan", "$");
        formula = formula.replace("log10", "%");
        formula = formula.replace("log2", "&");
        formula = formula.replace("sqrt", "№");

        // Учитываем унарный знак у ЧИСЕЛ
        String s;
        for (int i = 0; i < formula.length(); i++) {
            if (isSpecialSign(formula.charAt(i)) && isSpecialSign(formula.charAt(i + 1)) &&
                    Character.isDigit(formula.charAt(i + 2))) {
                s = formula.substring(i + 1, nextSign(formula, i + 1));
                formula = formula.substring(0, i + 1) + symbolsArr[amountSymbols]
                        + formula.substring(nextSign(formula, i + 1));
                symbolValue.put(symbolsArr[amountSymbols], Double.parseDouble(s));
                amountSymbols++;
            }

            if (formula.charAt(i) == '(' && formula.charAt(i + 1) == '-' &&
                    Character.isDigit(formula.charAt(i + 2))) {
                s = formula.substring(i + 1, nextSign(formula, i + 1));
                formula = formula.substring(0, i + 1) + symbolsArr[amountSymbols]
                        + formula.substring(nextSign(formula, i + 1));
                symbolValue.put(symbolsArr[amountSymbols], Double.parseDouble(s));
                amountSymbols++;
            }
        }

        if (formula.charAt(0) == '-') {
            formula = "0" + formula;
        }

        formula = formula.replace("(-", "(0-");
        formula = formula.replace("^-", "|");
        formula = formula.replace("/-", "~");
        formula = formula.replace("*-", "`");


    }

    private static void parseOnAction() {
        String mainString = formula;
        String subString;
        int[] index = new int[7];
        int min = -1;
        int max = -1;

        while (true) {
            /**parse () */
            if (countSigns(mainString) < 2) {
                actions.add(mainString);
                break;
            }

            subString = mainString;
            while (true) {
                if (countSigns(subString) == 1) {
                    break;
                }

                /** parse ( )*/
                index[0] = -1;
                for (int i = 0; i < subString.length(); i++) {
                    if (subString.charAt(i) == '(') index[0] = i;
                }

                if (index[0] != -1) {
                    index[1] = subString.indexOf(')', index[0]);
                    if (countSigns(subString.substring(index[0], index[1])) > 0) {
                        subString = subString.substring(index[0] + 1, index[1]);
                    } else {
                        // если внутри скобок нет действий - удалить скобки
                        mainString = mainString.substring(0, +index[0]) + mainString.substring(index[0] + 1,
                                index[1]) + mainString.substring(index[1] + 1);
                        subString = subString.substring(0, +index[0]) + subString.substring(index[0] + 1,
                                index[1]) + subString.substring(index[1] + 1);
                    }
                    continue;
                }

                /** parse math function*/
                index[0] = subString.indexOf('!');
                index[1] = subString.indexOf('@');
                index[2] = subString.indexOf('#');
                index[3] = subString.indexOf('$');
                index[4] = subString.indexOf('%');
                index[5] = subString.indexOf('&');
                index[6] = subString.indexOf('№');

                for (int i = 0; i < index.length; i++) {
                    if (index[i] != -1) {
                        min = index[i];
                        break;
                    }
                }
                if (min != -1) {
                    for (int i = 0; i < index.length; i++) {
                        if (index[i] < min && index[i] != -1)
                            min = index[i];
                    }
                    subString = subString.substring(min, nextSign(subString, min));
                    min = -1;
                    continue;
                }

                /** parse pow */
                index[0] = subString.indexOf('^');
                index[1] = subString.indexOf('|');
                if (index[0] != -1 || index[1] != -1) {
                    max = Math.max(index[0], index[1]);
                    subString = subString.substring(prevSign(subString, max), nextSign(subString, max));
                    max = -1;
                    continue;
                }
                /** parse '*' '/' */
                index[0] = subString.indexOf('*');
                index[1] = subString.indexOf('/');
                index[2] = subString.indexOf('~');
                index[3] = subString.indexOf('`');

                for (int i = 0; i < 4; i++) {
                    if (index[i] != -1) {
                        min = index[i];
                        break;
                    }
                }

                if (min != -1) {
                    for (int i = 0; i < 4; i++) {
                        if (index[i] < min && index[i] != -1)
                            min = index[i];
                    }
                    subString = subString.substring(prevSign(subString, min), nextSign(subString, min));
                    min = -1;
                    continue;
                }

                /** parse '+' '-' */
                index[0] = subString.indexOf('+');
                index[1] = subString.indexOf('-');

                if (index[0] != -1 || index[1] != -1) {
                    if (index[0] == -1 && index[1] != -1)
                        min = index[1];
                    if (index[0] != -1 && index[1] == -1)
                        min = index[0];

                    if (index[0] != -1 && index[1] != -1) {
                        if (index[0] < index[1]) min = index[0];
                        else min = index[1];
                    }
                    subString = subString.substring(prevSign(subString, min), nextSign(subString, min));
                    min = -1;
                }

            }
            //add action in ArrayList
            actions.add(subString);
            //change action on symbol from array
            mainString = mainString.replace(subString, symbolsArr[amountSymbols]);
            //put action in HashMap
            symbolFormulas.put(subString, symbolsArr[amountSymbols]);
            amountSymbols++;
        }

    }

    private static double calculateConst() {
        double result = 0;
        char sign;
        double d1 = 0;
        double d2 = 0;
        String subString = "";
        ArrayList<String> deleteAction = new ArrayList<>();


        for (String action : actions) {
            sign = getActionSign(action);

            if (action.indexOf(sign) == 0) {
                if (Character.isLowerCase(action.charAt(1))) continue;

                if (Character.isDigit(action.charAt(1))) {
                    d1 = Double.parseDouble(action.substring(1));
                } else if (Character.isUpperCase(action.charAt(1)) &&
                        symbolValue.get(action.substring(1)) != null) {
                    d1 = symbolValue.get(action.substring(1));
                } else continue;

            } else {
                if (Character.isLowerCase(action.charAt(0))) continue;
                subString = action.substring(0, action.indexOf(sign));

                if (Character.isDigit(action.charAt(0))) {
                    d1 = Double.parseDouble(subString);
                } else if (Character.isUpperCase(action.charAt(0)) &&
                        symbolValue.get(subString) != null) {
                    d1 = symbolValue.get(subString);
                } else continue;

                subString = action.substring(action.indexOf(sign) + 1);
                if (Character.isDigit(action.charAt(action.indexOf(sign) + 1))) {
                    d2 = Double.parseDouble(subString);
                } else if (Character.isUpperCase(action.charAt(action.indexOf(sign) + 1)) &&
                        symbolValue.get(subString) != null) {
                    d2 = symbolValue.get(subString);
                } else continue;
            }

            result = calculateAction(d1, d2, sign);
            if (symbolFormulas.get(action) != null) {
                symbolValue.put(symbolFormulas.get(action), result);
            }
            deleteAction.add(action);

        }

        while (deleteAction.size() > 0) {
            actions.remove(deleteAction.get(0));
            deleteAction.remove(0);
        }

        return result;
    }

    private static double calculateAction(double d1, double d2, char sign) {
        double result = 0;

        switch (sign) {
            case '+':
                result = d1 + d2;
                break;

            case '-':
                result = d1 - d2;
                break;

            case '*':
                result = d1 * d2;
                break;

            case '/':
                result = d1 / d2;
                break;

            case '^':
                result = Math.pow(d1, d2);
                break;

            case '|':
                result = Math.pow(d1, -d2);
                break;

            case '~':
                result = d1 / -d2;
                break;

            case '`':
                result = d1 * -d2;
                break;

            case '!':
                result = Math.sin(d1);
                break;

            case '@':
                result = Math.cos(d1);
                break;

            case '#':
                result = Math.tan(d1);
                break;

            case '№':
                result = Math.sqrt(d1);
                break;

            case '$':
                result = Math.atan(d1);
                break;

            case '%':
                result = Math.log10(d1);
                break;

            case '&':
                result = log2(d1);
                break;

            default:
                System.out.println("unknown action");
                return 0;
        }

        return result;
    }

    private static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private static double calculateWithVars() {

        System.out.println(actions);
        char sign = ' ';
        double d1 = 0;
        double d2 = 0;
        double result = 0;

        for (String action : actions) {
            sign = getActionSign(action);

            if (action.indexOf(sign) == 0) {
                if (Character.isDigit(action.charAt(1))) {
                    d1 = Double.parseDouble(action.substring(1));
                } else if (Character.isUpperCase(action.charAt(1))) {
                    d1 = symbolValue.get(action.substring(1));
                } else try {
                    d1 = variables.get(action.substring(1));
                } catch (Exception e) {
                    System.out.println("Variables from your formula isn`t found");
                    return 0;
                }
            } else {

                if (Character.isDigit(action.charAt(0))) {
                    d1 = Double.parseDouble(action.substring(0, action.indexOf(sign)));
                } else if (Character.isUpperCase(action.charAt(0))) {
                    d1 = symbolValue.get(action.substring(0, action.indexOf(sign)));
                } else try {
                    d1 = variables.get(action.substring(0, action.indexOf(sign)));
                } catch (Exception e) {
                    System.out.println("Variables from your formula isn`t found");
                    return 0;
                }

                if (Character.isDigit(action.charAt(action.indexOf(sign) + 1))) {
                    d2 = Double.parseDouble(action.substring(action.indexOf(sign) + 1));
                } else if (Character.isUpperCase(action.charAt(action.indexOf(sign) + 1))) {
                    d2 = symbolValue.get(action.substring(action.indexOf(sign) + 1));
                } else try {
                    d2 = variables.get(action.substring(action.indexOf(sign) + 1));
                } catch (Exception e) {
                    System.out.println("Variables from your formula isn`t found");
                    return 0;
                }
            }

            result = calculateAction(d1, d2, sign);
            if (symbolFormulas.get(action) != null) {
                symbolValue.put(symbolFormulas.get(action), result);
            }
        }
        return result;
    }

    private static char getActionSign(String s) {
        char ch = ' ';
        for (int i = 0; i < s.length(); i++) {
            if (isSpecialSign(s.charAt(i))) {
                ch = s.charAt(i);
                return ch;
            }
        }

        return ch;
    }

    private static int countSigns(String s) {
        int amount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isSpecialSign(s.charAt(i))) {
                amount++;
            }
        }
        return amount;
    }

    /**
     * This method check is char math sign
     *
     * @param ch - char with symbol
     * @return if ch - math sign - true,
     * else - false
     */
    private static boolean isSpecialSign(char ch) {
        return ch == '-' || ch == '+' || ch == '/' || ch == '*' || ch == '^'
                || ch == '!' || ch == '@' || ch == '#' || ch == '№' || ch == '$'
                || ch == '%' || ch == '&' || ch == '|' || ch == '~' || ch == '`';
    }

    private static boolean isMathSign(char ch) {
        return ch == '-' || ch == '+' || ch == '/' || ch == '*' || ch == '^';
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
            if (isSpecialSign(s.charAt(i))) {
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
            if (isSpecialSign(s.charAt(i))) {
                res = i;
                break;
            }
        }
        return res;
    }
}
