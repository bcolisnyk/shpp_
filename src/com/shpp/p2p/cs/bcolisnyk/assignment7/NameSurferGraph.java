package com.shpp.p2p.cs.bcolisnyk.assignment7;

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

    /**
     * ArrayList with NameSurferEntry to be displayed on the screen
     */
    ArrayList<NameSurferEntry> list = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        list.clear();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        list.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        //delete all object from screen and redraw background
        removeAll();
        drawBackground();
        //if there are objects to display - draw it
        if (!list.isEmpty()) {
            drawGraphs();
        }

    }

    /**
     * This method draw object from ArrayList<NameSurferEntry> list
     * It draws graphs and label with current info in every decade
     */
    private void drawGraphs() {
        //distance between decades
        double step = getWidth() / NDECADES;
        // height of field between upper and bottom line
        double fieldHeight = getHeight() - (2 * GRAPH_MARGIN_SIZE);
        // counter for pick right color for graph
        int colorCounter = 0;
        GLine line;
        GLabel label;

        for (NameSurferEntry entry : list) {
            for (int i = 0; i < NDECADES - 1; i++) {
                // start and finish point of graph in every decade
                double startY = GRAPH_MARGIN_SIZE + ((double) entry.getRank(i) / MAX_RANK * fieldHeight);
                double finishY = GRAPH_MARGIN_SIZE + ((double) entry.getRank(i + 1) / MAX_RANK * fieldHeight);
                // if rank == 0, draw graph in bottom part of field
                if (startY == GRAPH_MARGIN_SIZE) startY = getHeight() - GRAPH_MARGIN_SIZE;
                if (finishY == GRAPH_MARGIN_SIZE) finishY = getHeight() - GRAPH_MARGIN_SIZE;

                //draw line of graph
                line = new GLine(step * i, startY, 0 + step * (i + 1), finishY);
                line.setColor(getCurrentColor(colorCounter));
                add(line);

                //draw label near line
                String rank;
                if (entry.getRank(i) == 0) rank = "*";
                else rank = String.valueOf(entry.getRank(i));
                label = new GLabel(entry.getName() + " " + rank);
                label.setLocation(step * i, startY);
                label.setFont("Verdana-12");
                label.setColor(getCurrentColor(colorCounter));
                add(label);

            }
            //go to the next color in the list
            colorCounter++;
        }
    }

    /**
     * This method return color in next sequence
     * blue - red - magenta - black - blue - red ... etc
     * <p>
     * i - counter of color queue
     */
    private Color getCurrentColor(int i) {
        if (i % 4 == 0) return Color.blue;
        if (i == 1 || i % 4 == 1) return Color.red;
        if (i == 2 || i % 4 == 2) return Color.magenta;
        return Color.black;
    }

    /**
     * This method draw background grid:
     * horizontal top and bottom lines,
     * and vertical lines for each decade
     */
    private void drawBackground() {
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        topLine.setColor(Color.black);

        GLine bottomLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),
                getHeight() - GRAPH_MARGIN_SIZE);
        bottomLine.setColor(Color.black);

        add(topLine);
        add(bottomLine);

        double step = getWidth() / NDECADES;
        GLabel text;
        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(step * i, 0, 0 + step * i, getHeight()));

            text = new GLabel(String.valueOf(START_DECADE + 10 * i));
            text.setFont("Verdana-12");
            text.setLocation(step * i, getHeight() - text.getDescent());
            add(text);
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
