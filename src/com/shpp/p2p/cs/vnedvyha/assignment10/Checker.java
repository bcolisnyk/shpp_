package com.shpp.p2p.cs.vnedvyha.assignment10;

/** Class for checking arguments */
public class Checker implements Errors {

    /** Main method, which calls check from 2 CheckerFormula, CheckerVariables */
    public static void check(String[] args) {
        СheckerFormula.check(args[0]);
        CheckerVariables.check(args);
    }

    /** Skips spaces from index in argument
     *  @param i index, from which skip starts
     *  @param arg line, which is examining
     *  @return index of next non-empty char */
    public static int skipSpaces(int i, String arg) {
        while (i < arg.length() && arg.charAt(i) == ' ') i++;
        return i;
    }

    /** Check number for formatting-errors
     *  @param i starting index in arg
     *  @param arg line, which is examining
     *  @return index of last symbol in number */
    public static int checkNumber(int i, String arg) {
        int dots = 0;
        do {
            if (arg.charAt(i) == '.') dots++;
            // checks, if there is too many dots in a digit
            if (dots > 1) throw new IllegalArgumentException(ERRORS[6]);
            i++;
        } while (i < arg.length() && (Character.isDigit(arg.charAt(i)) || arg.charAt(i) == '.'));
        return i;
    }
}
