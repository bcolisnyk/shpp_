package com.shpp.p2p.cs.vnedvyha.assignment10;

import java.util.ArrayList;
/** Class, which parses formula */
public class ParserFormula {
    /** Parses String formula into ArrayList
     *  @param formula string, which is to be parsed
     *  @return array-list of parsed formula*/
    public static ArrayList<String> parse(String formula) {
        var parsed = new ArrayList<String>();
        // for adding additional minuses and pluses to number
        String sign = "";
        formula = formula.replaceAll(" ", "");
        for (int i = 0; i < formula.length(); i++) {

            if (Character.isLetter(formula.charAt(i))) {
                parsed.add(formula.substring(i, i+1));
                continue;
            }

            if (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.') {
                // goes throw all length of number with possible dot
                int startIndex = i;
                while (i < formula.length() && (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.')){
                    i++;
                }
                // add this number with previously finded sign (either empty or with '-', '+') to arraylist
                parsed.add(sign + formula.substring(startIndex, i--));
                sign = "";
                continue;
            }

            if (Signs.isSign(formula.charAt(i))) {
                // if starts with sign or previous symbol was sign
                // should add this sign to next number
                if (i == 0 || Signs.isSign(formula.charAt(i-1))) {
                    sign = formula.substring(i, i+1);
                    continue;
                }
                parsed.add(formula.substring(i, i+1));
            }
        }
        return parsed;
    }
}
