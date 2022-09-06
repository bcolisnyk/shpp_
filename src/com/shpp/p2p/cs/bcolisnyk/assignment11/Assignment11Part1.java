package com.shpp.p2p.cs.bcolisnyk.assignment11;

import java.util.HashMap;

public class Assignment11Part1 {
    public static HashMap<String, Double> variables;
    public static String formula;

    public static void main(String[] args) {

        InputParser ip = new InputParser(args);
        variables = ip.getVariables();
        formula = ip.getFormula();

        FormulaParser fp = new FormulaParser(formula);
        fp.getFormula();
    }
}
