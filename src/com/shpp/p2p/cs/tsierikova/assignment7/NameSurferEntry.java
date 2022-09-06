package com.shpp.p2p.cs.tsierikova.assignment7;

/** Вывод стринга
 * */
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

public class NameSurferEntry implements NameSurferConstants {

    public String name;
    public int[] rank;
	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file. Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] words = line.split(" ");
        rank = new int[NDECADES];
        name = words[0]; //define name as first word from line
        for (int i = 0; i < rank.length; i++) {
            rank[i] = Integer.parseInt(words[i+1]); //append rank by parsed int from words
        }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return rank[decade];
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String result = name + " " + "[";
        for (int i = 0; i < NDECADES - 1; i++) {
            result += rank[i];
            result += ", ";
        }
        result += rank[NDECADES - 1];
        result += "]";
        return result;
    }
}

