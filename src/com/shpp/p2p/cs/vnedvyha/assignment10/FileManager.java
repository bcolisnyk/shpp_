package com.shpp.p2p.cs.vnedvyha.assignment10;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Class, which manages txt-reading and -writing
 */
public class FileManager {
    /**
     * Address to file
     */
    private static final String filename = "formulas.txt";

    /**
     * Contains formula and its parsed variant
     */
    public static HashMap<String, ArrayList<String>> formulas = new HashMap<>();

    /**
     * Search for formula in hashmap
     *
     * @param formula key for hashmap
     * @return value for "formula" key
     */
    public static ArrayList<String> searchFormula(String formula) {
        formula = formula.replaceAll(" ", "");
        return formulas.get(formula);
    }

    /**
     * Writes formula and it's parsed version in file
     *
     * @param formula unparsed version
     * @param parsed  parsed version of formula
     */
    public static void putFormula(String formula, ArrayList<String> parsed) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            // writes down formula without spaces
            bw.write(formula.replaceAll(" ", ""));
            bw.newLine();
            // writes down every element in parsed with commas between
            for (var el : parsed) {
                bw.write(el + ',');
            }
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read the txt-file into "formulas" HashMap
     */
    public static void read() {
        if (Files.exists(Path.of(filename))) {
            try {
                FileReader fr = new FileReader(filename);
                Scanner scan = new Scanner(fr);
                while (scan.hasNextLine()) {
                    // first line - formula
                    String formula = scan.nextLine();
                    // next line - parsed version with commas between
                    String[] arguments = scan.nextLine().split(",");
                    var parsed = new ArrayList<String>(Arrays.asList(arguments));
                    // fills hashmap
                    formulas.put(formula, parsed);
                }
                fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
