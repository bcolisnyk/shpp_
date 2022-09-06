package com.shpp.p2p.cs.bcolisnyk.testing;

import com.shpp.cs.a.console.TextProgram;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class test extends TextProgram {
    public static void main(String[] args) {
        String s = "0123456789";
        System.out.println(s.substring(0)); //3456789
        System.out.println(s.length());
        System.out.println(s.substring(3, 10)); //345
        // 3 - входит
        // 6 - не входит

    }

    private static boolean isMathSign(char ch) {
        return ch == '-' || ch == '+' || ch == '/' || ch == '*' || ch == '^';
    }

    private static int nextSign(String s, int index) {
        int res = s.length();
        for (int i = index + 1; i < s.length(); i++) {
            if (isMathSign(s.charAt(i))) {
                res = i;
                break;
            }
        }
        return res;
    }
}

