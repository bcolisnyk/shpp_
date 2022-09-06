package com.shpp.p2p.cs.tsierikova.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private final ArrayList<NameSurferEntry> namesDisplayed;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        namesDisplayed = new ArrayList<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        namesDisplayed.clear();
        update();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        namesDisplayed.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawBackgroundNet();
        // draw graph:
        if (namesDisplayed.size() > 0) {
            for (int i = 0; i < namesDisplayed.size(); i++) {
                drawGraphEntry(namesDisplayed.get(i));
            }
        }
    }

    /**
     * The method draw background for the graphs
     */
    private void drawBackgroundNet() {
        double distance = (double) getWidth() / (double) NDECADES;
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(i * distance, 0, i * distance, getHeight()));
            String years = (START_DECADE + 10 * i) + "";
            GLabel yearLabel = new GLabel(years);
            //add vertical line
            add(yearLabel, i * distance, getHeight() - GRAPH_MARGIN_SIZE + yearLabel.getHeight());
        }
        //add horizontal lines
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
    }

    /**
     * The method draws one graph
     *
     * @param entry NameSurferEntry for the user's input
     */
    private void drawGraphEntry(NameSurferEntry entry) {
        String name = entry.getName();
        Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
        //the remainder operator gives the remainder of integer division and helps to select color
        // by dividing entry index to length of color array
        Color color = colors[(namesDisplayed.indexOf(entry)) % colors.length];
        //define start point
        GPoint point = new GPoint(0, 0);
        //drawing the graph in loop
        for (int i = 0; i < NDECADES; i++) {
            int rank = entry.getRank(i); //get rank from entry
            double x = getWidth() * (double) i / NDECADES; //count x
            double y; //initialize y
            //count y:
            if (rank == 0) {
                y = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                y = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (double) rank / (double) MAX_RANK + GRAPH_MARGIN_SIZE;
            }

            GPoint newPoint = new GPoint(x, y); //get point from counted x and y
            //draw graph line
            if (i != 0) {
                GLine line = new GLine(point.getX(), point.getY(), newPoint.getX(), newPoint.getY());
                line.setColor(color);
                add(line);
            }

            //add label to the graph
            String rankString = String.valueOf(rank);
            GLabel label;
            if (rankString.equals("0")) {
                label = new GLabel(name + " " + "*", newPoint.getX(), newPoint.getY());
            } else {
                label = new GLabel(name + " " + rankString, newPoint.getX(), newPoint.getY());
            }
            label.setColor(color);
            add(label);

            point = newPoint;
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
