package com.shpp.p2p.cs.vnedvyha.assignment10;
/** Interface, which contains constants */
public interface Errors {
    /** Array of possible errors */
    public static final String[] ERRORS = {
            "There's no '=' in some of args",
            "There isn't '=' on expected place",
            "There is an expression, which doesn't start with letter ",
            "There's no statement after '='",
            "There are too much signs",
            "There's a space between sign and number",
            "Too many dots",
            "Wrong expression",
            "There isn't a sign after a letter",
            "There is a sign on unexpected place",
            "There's a space between dot and number",
            "There's an invalid symbol",
            "Sign can't be only symbol in formula",
            "Operand should be last",
            "There are less parameters then used in formula"
    };
}
