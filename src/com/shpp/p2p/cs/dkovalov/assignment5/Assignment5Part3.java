package com.shpp.p2p.cs.dkovalov.assignment5;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment5Part3 extends TextProgram {

    public static final String FILE_NAME = "assets/assignment5/en-dictionary.txt";

    public void run() {
        while (true) {
           String word = readLine("Enter a USA car number:");
            println("The words:\n" + findWords(word.toLowerCase()));
        }
    }

    /**
     * this method searching words which have letters which entered user
     * @param word letters which entered user
     * @return words that have been tested by letters and from these letters you can add a word
     */
    private String findWords(String word) {
        char[] threeLetter = {'0', '0', '0'};
        int j = 0;
        int countLetter;
        String line;
        for (int i = 0; i < threeLetter.length; i++) {
            while (j < word.length()) {
                if (Character.isLetter(word.charAt(j)) == true) {
                    threeLetter[i] = word.charAt(j);
                    j++;
                    break;
                }
                if (threeLetter[i] != '0') {
                    break;
                }
                j++;
            }
        }

        for (int i = 0; i < threeLetter.length; i++) {
            if (threeLetter[i] == '0') {
                return "You enter less than 3 letters or more than 3 letters. Please, enter 3 letters";
            }
        }
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(FILE_NAME));
            while ((line = buffer.readLine()) != null) {
                countLetter = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (countLetter == 0) {
                        if (line.charAt(i) == threeLetter[0]) {
                            countLetter++;
                        }
                    } else if (countLetter == 1) {
                        if (line.charAt(i) == threeLetter[1]) {
                            countLetter++;
                        }
                    } else if (countLetter == 2) {
                        if (line.charAt(i) == threeLetter[2]) {
                            countLetter++;
                            break;
                        }
                    }
                }
                if (countLetter >= 3) {
                    result.append(line);
                    result.append("\n");
                }
            }
            buffer.close();
        } catch (IOException e) {
            println("The file not found" + e);
        }
        return result.toString();
    }
}
