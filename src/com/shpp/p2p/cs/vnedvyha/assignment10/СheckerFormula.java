package com.shpp.p2p.cs.vnedvyha.assignment10;
/** Class, which checks formula for format-errors */
// Extends Checker for inner skipSpaces and checkNumber methods
public class СheckerFormula extends Checker implements Errors {
    /** Checks formula for format-errors */
    public static void check(String formula) throws IllegalArgumentException {
        formula = formula.trim();
        for (int i = 0; i < formula.length(); i++) {
            // could start with spaces
            i = skipSpaces(i, formula);
            if (Character.isLetter(formula.charAt(i))) {
                // skip possible spaces after letter
                i = skipSpaces(i+1,  formula);
                if (i == formula.length()) return;
                // nothing could be placed in last index after operand
                if (i == formula.length()-1) throw new IllegalArgumentException(ERRORS[13]);
                // after a letter should be sign
                if (!Signs.isSign(formula.charAt(i)))
                    throw new IllegalArgumentException(ERRORS[8]);
                continue;
            }
            if (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.') {
                // i = first empty space after number
                i = checkNumber(i, formula);
                i = skipSpaces(i, formula);

                if (i == formula.length()) return;
                // nothing could be placed in last index after operand
                if (i == formula.length()-1) throw new IllegalArgumentException(ERRORS[13]);
                // after a number should be sign
                if (!Signs.isSign(formula.charAt(i)))
                    throw new IllegalArgumentException(ERRORS[8]);
                continue;
            }
            if (formula.charAt(i) == '+' || formula.charAt(i) == '-') {
                // would work only if + or - is first and last symbol in formula
                if (i == formula.length()-1) throw new IllegalArgumentException(ERRORS[12]);

                i = skipSpaces(i + 1, formula);
                // after + or - can't be another sign
                if (Signs.isSign(formula.charAt(i))) {
                    throw new IllegalArgumentException(ERRORS[4]);
                } else --i;
                // decrement i, coz i is pointed at some symbol
                // next iteration of cycle will increase i, and this symbol won't be checked
            } else throw new IllegalArgumentException(ERRORS[11]);
        }
    }
}
