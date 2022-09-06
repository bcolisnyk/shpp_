package com.shpp.p2p.cs.bcolisnyk.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private JTextField enteredName;
    private JButton graphButton;
    private JButton clearButton;

    private NameSurferGraph graph = new NameSurferGraph();
    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name:"), NORTH);
        enteredName = new JTextField(NUM_COLUMNS);
        add(enteredName, NORTH);

        graphButton = new JButton("Graph");
        add(graphButton, NORTH);

        clearButton = new JButton("Clear");
        add(clearButton, NORTH);

        add(graph);

        enteredName.addActionListener(this);
        addActionListeners();

    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        NameSurferDataBase db = new NameSurferDataBase("assets/" + NAMES_DATA_FILE);
        NameSurferEntry entry;

        //if "graph" or enter is pressed - find entry from list from database
        if (e.getSource() == enteredName || e.getSource() == graphButton) {
             entry = db.findEntry(enteredName.getText());
             //if entry is found, add graph with info from this entry
            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            }
            //if "clear" is pressed - clear all graphs from screen
        } else {
            graph.clear();
            graph.update();
        };
    }
}
