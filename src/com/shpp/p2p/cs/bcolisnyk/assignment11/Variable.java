package com.shpp.p2p.cs.bcolisnyk.assignment11;

public class Variable {
    private String formula;
    private double value;
    private String name;


    Variable(String formula, int i) {
        this.name = "A_" + i;
        this.formula = formula;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public String getFormula() {
        return this.formula;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Name : " + this.name + "; Formula : " + this.formula + "; Value : " + value;
    }
}
