package com.shpp.p2p.cs.bcolisnyk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {

    /**
     * This program plays american game
     * user enters any 3 letter
     * after this, program try to find word,
     * which will contain these 3 letters in the given order
     */
    public void run() {
        //read text file with words
        ArrayList<String> words = readWords("assets/en-dictionary.txt");
        //user enters any 3 letter
        while (true) {
            String enteredLetters = enterString();
            checkWords(words, enteredLetters);
        }
    }

    /* this method ask the user to enter any 3 letter,
     * rewrites them to lower case
     * and check, if length string is not 3, gives an error message
     */
    private String enterString() {
        String word;
        String result;

        while (true) {
            word = readLine("Enter 3 letters : ");
            result = word.toLowerCase();

            if (result.length() == 3) {
                break;
            } else {
                println(" Sorry, but enter 3 letter ( no more, no less ) ");
            }
        }
        return result;
    }

    /*this methods gets ArrayList with different words, and string with 3 letters,
     * check every word from ArrayList, and if word is correct, return this word */
    private void checkWords(ArrayList<String> words, String enteredLetters) {
        String res = "";
        char ch1 = enteredLetters.charAt(0);
        char ch2 = enteredLetters.charAt(1);
        char ch3 = enteredLetters.charAt(2);

        int index1;
        int index2;
        int index3;

        for (String word : words) {
            index1 = word.indexOf(ch1);
            index2 = word.indexOf(ch2, index1 + 1);
            index3 = word.indexOf(ch3, index2 + 1);

            if (index1 != -1 && index2 != -1 && index3 != -1) {
                res = word;
                println(word);
                break;
            }
        }

        if (res.equals("")) println("Sorry, try another letter :( ");
    }

    /* this method reads text file with word in every line
     * and return ArrayList with words from file*/
    private ArrayList<String> readWords(String filename) {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (true) {
                String word = br.readLine();
                if (word == null) break;

                result.add(word);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}