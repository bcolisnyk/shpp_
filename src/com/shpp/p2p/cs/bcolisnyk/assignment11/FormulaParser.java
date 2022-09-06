package com.shpp.p2p.cs.bcolisnyk.assignment11;

import java.util.ArrayList;
import java.util.Objects;

import com.shpp.p2p.cs.bcolisnyk.assignment11.Variable;

import javax.swing.*;

public class FormulaParser {
    private ArrayList<Variable> variables = new ArrayList<>();
    private int varsAmount = 0;
    private String formula;
    private ArrayList<String> action;
    private int amountActions = 0;
    private char[] mathSigns = {'+', '-', '*', '/', '^'};
    private char[] specialSigns = {'`', '~', '|', '!', '@', '#', '$', '%', '&', '№', ')'};

    FormulaParser(String formula) {
        this.formula = formula;
        formatFormula();
    }

    private void formatFormula() {
        formula = formula.replace("^+", "^");
        formula = formula.replace("--", "+");
        formula = formula.replace("/+", "/");
        formula = formula.replace("*+", "*");

        formula = formula.replace("sin", "!");
        formula = formula.replace("cos", "@");
        formula = formula.replace("tan", "#");
        formula = formula.replace("atan", "$");
        formula = formula.replace("log10", "%");
        formula = formula.replace("log2", "&");
        formula = formula.replace("sqrt", "№");


        //TODO: учитывать унарный знак
        // Пусть парсит даже переменные, а потом уже в классе Variable находит значения ??
        // Если сразу main формула - разобраться со скобками

        for (int i = 1; i < formula.length(); i++) {
            if (formula.charAt(i) == '-' && isSign(formula.charAt(i - 1)) || isSpecialSign(formula.charAt(i - 1))) {
                if (Character.isDigit(formula.charAt(i + 1))) {
                    variables.add(new Variable(formula.substring(i, nextSign(formula, i)), amountActions));
                    variables.get(variables.size() - 1)
                            .setValue(Double.parseDouble(formula.substring(i, nextSign(formula, i))));
                    formula = formula.substring(0, i) +
                            variables.get(variables.size() - 1).getName() +
                            formula.substring(nextSign(formula, i));
                }
            }
        }

        System.out.println(variables);
        if (formula.charAt(0) == '-') {
            formula = "0" + formula;
        }

        formula = formula.replace("(-", "(0-");
        formula = formula.replace("^-", "|");
        formula = formula.replace("/-", "~");
        formula = formula.replace("*-", "`");


    }

    private Variable getActualVar(String s) {
        for (Variable variable : variables) {
            if (variable.getFormula().equals(s) || variable.getName().equals(s))
                return variable;
        }
        return null;
    }


    public void parseOnAction() {
        String main = formula;
        String sub = main;
        //TODO: парсинг формулы
    }

    private int prevSign(String s, int sign) {
        for (int i = sign; i > 0; i--) {
            if (isSign(s.charAt(i)) || isSpecialSign(s.charAt(i))) {
                return i + 1;
            }
        }

        return 0;
    }

    private int nextSign(String s, int sign) {
        for (int i = sign + 1; i < s.length(); i++) {
            if (isSign(s.charAt(i)) || isSpecialSign(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }

    private boolean isSign(char ch) {
        for (char sign : mathSigns) {
            if (ch == sign)
                return true;
        }
        return false;
    }

    private boolean isSpecialSign(char ch) {
        for (char sign : specialSigns) {
            if (ch == sign)
                return true;
        }
        return false;
    }

    public void getFormula() {
        System.out.println(formula);
    }
}
