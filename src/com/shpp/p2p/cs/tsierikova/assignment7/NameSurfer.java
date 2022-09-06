package com.shpp.p2p.cs.tsierikova.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private JTextField textField;
    private NameSurferDataBase dataBase;
    private NameSurferGraph graph;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */

    public void init() {
        this.add(new JLabel("Name:"), "North");
        this.add(textField = new JTextField(20), "North");
        this.add(new JButton("Graph"), "North");
        this.add(new JButton("Clear"), "North");
        this.addActionListeners();
        dataBase = new NameSurferDataBase("assets/" + NAMES_DATA_FILE); //create new dataBase
        graph = new NameSurferGraph(); //initialize new graph
        add(graph); //add graph to the screen
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Graph")) {
            NameSurferEntry entry = dataBase.findEntry(textField.getText()); //find user's name in dataBase
            if (entry != null) {
                graph.addEntry(entry); //add user's name to the list
                graph.update(); //redraw graph
            }
        } else if (e.getActionCommand().equals("Clear")) {
            graph.clear();
            graph.update();
        }
    }
}
