package com.shpp.p2p.cs.bcolisnyk.assignment11;

import java.util.HashMap;

public class InputParser {
    private String formula;
    private HashMap<String, Double> variables = new HashMap<>();

    InputParser(String[] args) {
        if (args.length > 0) {
            this.formula = args[0].replace(" ", "");
            this.formula = formula.replace(",", ".");
        }

        if (args.length > 1) {
            String[] varParts;

            for (int i = 1; i < args.length; i++) {
                args[i] = args[i].replace(" ", "");
                varParts = args[i].split("=");
                variables.put(varParts[0], Double.parseDouble(varParts[1]));
            }
        }
    }

    public String getFormula() {
        return this.formula;
    }

    public HashMap<String, Double> getVariables() {
        return this.variables;
    }
}
