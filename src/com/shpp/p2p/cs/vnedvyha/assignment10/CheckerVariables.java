package com.shpp.p2p.cs.vnedvyha.assignment10;
/** Class, which checks variables for format-errors
 *  Right formatting is with letter on left-side, equals and right-side,
 *  which should be parseable by Double.valueOf(String) */
// Extends Checker for inner skipSpaces and checkNumber methods
public class CheckerVariables extends Checker implements Errors {

    /** Main method of class, repeatedly calls checkVariable */
    /** @param args Array of strings */
    public static void check(String[] args) throws IllegalArgumentException {
        for (int i = 1; i < args.length; i++) {
            checkVariable(args[i]);
        }
    }
    /** Checks single line
     * @param arg - single line from arguments */
    private static void checkVariable(String arg) throws IllegalArgumentException {
        // argument should have an equals in it
        if (!arg.contains("=")) throw new IllegalArgumentException(ERRORS[0]);
        arg = arg.trim();
        int i = checkToEquals(arg);
        checkAfterEquals(i, arg);
    }
    /** Checks part before equals
     *  @param arg single line from arguments
     *  @return index, on which checker has stopped */
    private static int checkToEquals(String arg) {
        int i = 0;
        // check, that letter is first
        if (Character.isLetter(arg.charAt(i))) {
            i = skipSpaces(++i, arg);
            // check, that after letter there is equals
            if (i < arg.length() && arg.charAt(i) != '=')
                throw new IllegalArgumentException(ERRORS[1]);

        } else throw new IllegalArgumentException(ERRORS[2]);

        return i;
    }
    /** Checks part after equals
     *  @param i index, on which checker has stopped
     *  @param arg single line from arguments*/
    private static void checkAfterEquals(int i, String arg) {
        i = skipSpaces(++i,  arg);
        if (i == arg.length()) throw new IllegalArgumentException(ERRORS[3]);
        // if there are two signs in a row
        if ((arg.charAt(i) == '-' || arg.charAt(i) == '+')) {
            i = skipSpaces(++i,  arg);
            if ((arg.charAt(i) == '-' || arg.charAt(i) == '+')) throw new IllegalArgumentException(ERRORS[4]);
        }
        // right after dot should be digit
        if (arg.charAt(i) == '.') {
            i++;
            if (!Character.isDigit(arg.charAt(i))) throw new IllegalArgumentException(ERRORS[10]);
        }
        if (Character.isDigit(arg.charAt(i))) {
            i = checkNumber(i, arg);
            if (i == arg.length()) return;
        }
        i = skipSpaces(++i, arg);
        // after number argument should be empty
        if ((arg.charAt(i)) != ' ') throw new IllegalArgumentException(ERRORS[7]);
    }
}
