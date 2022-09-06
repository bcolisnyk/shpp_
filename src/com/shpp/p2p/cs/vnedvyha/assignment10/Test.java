package com.shpp.p2p.cs.vnedvyha.assignment10;
/** Class for calling Assignment10 and checking its results */
public class Test {
    /** Some random examples */
    public static void main(String[] args) {
        System.out.println("Expected = 2.2");
        Assignment10Part1.main(new String[]{"2.2"});
        System.out.println("---------");

        System.out.println("Expected = 4.2");
        Assignment10Part1.main(new String[]{"2.2 +2"});
        System.out.println("---------");

        System.out.println("Expected using of already parsed formula");
        Assignment10Part1.main(new String[]{"2.2 +2"});
        System.out.println("---------");

        System.out.println("Expected = -0.8");
        Assignment10Part1.main(new String[]{".2 + -1"});
        System.out.println("---------");

        System.out.println("Expected = -1.3");
        Assignment10Part1.main(new String[]{"-4/+3"});
        System.out.println("---------");

        System.out.println("Expected = 1");
        Assignment10Part1.main(new String[]{"a", "a=1"});
        System.out.println("---------");

        System.out.println("Expected = -2.5");
        Assignment10Part1.main(new String[]{"a/2^2*2", "a     = -5."});
        System.out.println("---------");

        System.out.println("Expected = 0.6");
        Assignment10Part1.main(new String[]{"a/+5", "a=3"});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"a+b", "a=3"});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"-4=3"});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"-4/4./ "});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"="});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"-"});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"a//2^2*2", "a     = -5."});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"a/. 2^2*2", "a     = -5."});
        System.out.println("---------");

        System.out.println("Expected: error");
        Assignment10Part1.main(new String[]{"a", "a=-5 ."});
    }
}
