package com.shpp.p2p.cs.tsierikova.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {

    private final HashMap<String, NameSurferEntry> hashmap = new HashMap<>();

	/* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        String line = buffer.readLine();
        while (line != null) { //read file line by line
            NameSurferEntry entry = new NameSurferEntry(line);
            hashmap.put(entry.name.toLowerCase(), entry); //add line to hashmap
            line = buffer.readLine();
        }
        buffer.close();
        } catch (Exception e) { //if error
            System.out.println("Error " + e); //print error to the user
        }
    }
	
	/* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        name = name.toLowerCase();
        if (hashmap.containsKey(name)) { //if name exists in database
            return hashmap.get(name);
        } else { //if name doesn't exists in database
            return null;
        }
    }
}

