package com.shpp.p2p.cs.bcolisnyk.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    /*location of the readable file*/
    public static final String file = "assets/example.txt";

    /**
     * this program read CSV file and displays on screen ArrayList
     * with word from specified column
     */
    public void run() {
        ArrayList<String> res;
        try {
            res = extractColumn(file, 5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        print(res);

    }

    /* this method open csv file, and return ArrayList with words
     * from specified column */
    private ArrayList<String> extractColumn(String filename, int columnIndex) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> array;
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while (true) {
                line = br.readLine();
                if (line == null) break;

                array = fieldsIn(line);
                result.add(array.get(columnIndex));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /* this method take CSV-format string, and return ArrayList
     * with words from this string*/
    private ArrayList<String> fieldsIn(String line) {
        ArrayList result = new ArrayList();
        int start = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                if (checkWord(line, i)) {
                    addWord(line, start, i, result);
                    start = i + 1;
                }
            }
        }
        addWord(line, start, line.length(), result);
        return result;
    }

    /* this method take string, index of first char of word, index of last char of word
     * create new string with word from string and add this word in ArrayList*/
    private void addWord(String line, int start, int end, ArrayList<String> result) {
        char ch;
        String word = "";
        for (int i = start; i < end; i++) {
            ch = line.charAt(i);
            word += ch;
        }

        if (word.charAt(0) == '\"' && word.charAt(word.length() - 1) == '\"') {
            String res = "";
            for (int i = 1; i < word.length() - 1; i++) {
                ch = word.charAt(i);
                res += ch;
            }
            result.add("\"" + res + "\"");
        } else {
            result.add("\"" + word + "\"");
        }

    }

    /* this method check next symbol from string,
     * if this symbol is ' ' - return false,
     * else - true*/
    private boolean checkWord(String line, int i) {
        return line.charAt(i + 1) != ' ';
    }
}
