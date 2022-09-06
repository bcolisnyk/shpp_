package com.shpp.p2p.cs.vnedvyha.assignment10;

import java.util.HashMap;
/** Class, which parses variables */
public class ParserVariables {
    /** Parses variables from String-array to Hashmap with keys-letters and values in double */
    public static HashMap<Character, Double> parse(String[] args) {
        var parsed = new HashMap<Character, Double>();
        // checking all arguments from second
        for (int i = 1; i < args.length; i++) {
            char ch = ' ';
            // goes throw one specific argument, finds a letter, remembers it
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isLetter(args[i].charAt(j))) {
                    ch = args[i].charAt(j);
                    break;
                }
            }
            // puts in HashMap letter and double-value of part after '=' in arg
            parsed.put(ch, Double.parseDouble(args[i].substring(args[i].indexOf('=') + 1)));
        }
        return parsed;
    }
}
