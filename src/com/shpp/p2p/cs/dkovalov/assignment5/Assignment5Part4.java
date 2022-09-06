package com.shpp.p2p.cs.dkovalov.assignment5;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    // file name for parsing
    public static final String NAME_FILE = "assets/assignment5/123.txt";

    @SuppressWarnings("InfiniteLoopStatement")

    /**
     * this method parsing .csv file and print the text from the specified column
     */
    public void run() {
        while (true) {
            int column = readInt("Enter a column:");
            if (column < 0) {
                println("You cannot enter number column less 0");
            } else {
                println(extractColumn(column));
            }
        }
    }

    /**
     * this method sorting text from .csv file and writing clean text
     *
     * @param line which is read from the file
     * @return resultText which sorted by commas and double quotes
     */
    private ArrayList<String> fieldsIn(String line) {

        // result text output
        ArrayList<String> resultText = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        char doubleQuot = '\"';
        char comma = ',';
        int countDoubleQuot = 0;

        // pass along the line
        for (int i = 0; i < line.length(); i++) {

            // check double quotes
            if (line.charAt(i) == doubleQuot) {
                countDoubleQuot++;
                if (countDoubleQuot % 2 == 0) {

                    // check next symbol comma
                    if (line.charAt(i + 1) == comma) {
                        countDoubleQuot = 0;
                        continue;
                    }
                }
                else if (countDoubleQuot % 2 != 0) {
                    continue;
                }
            }
            // check count quotes
            if (line.charAt(i) == comma && countDoubleQuot == 0) {
                resultText.add(text.toString());
                text.delete(0, text.length());
            } else if (i == line.length() - 1 && countDoubleQuot == 0) {
                text.append(line.charAt(i));
                resultText.add(text.toString());
            } else if (line.charAt(i) == comma && countDoubleQuot % 2 == 0) {
                text.append(line.charAt(i));
            } else {
                text.append(line.charAt(i));
            }
        }
        return resultText;
    }

    /**
     * this method open file and make sorting text by commas and quotes
     *
     * @param columnIndex get index column cvv text
     * @return final text for output
     */
    private ArrayList<String> extractColumn(int columnIndex) {
        ArrayList<String> text;
        ArrayList<String> resultText = new ArrayList<>();

        /*
         * open file and sorting text by commas and quotes
         */
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(NAME_FILE));
            String line;
            while ((line = buffer.readLine()) != null) {
                text = fieldsIn(line);
                resultText.add(text.get(columnIndex - 1));
            }
            buffer.close();
        } catch (IOException e) {
            println("File (" + NAME_FILE + ") not found" + e);
            return null;
        }
        return resultText;
    }
}
