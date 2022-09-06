package com.shpp.p2p.cs.dstriapukhin.assignment8;

import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;


import acm.graphics.GRect;


import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Class contains methods to accomplish given task
 */
public class MidExam extends WindowProgram {
    // Two constants for adjusting the size of the window in width and height
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    // How many the rows
    private static final int NUM_ROWS = 2;

    // How many the cols
    private static final int NUM_COLS = 2;

    // Width and height the box
    private static final double BOX_SIZE = 15;

    // Distance between fields horizontally and vertically (WARNING minimum input 2)
    private static final double BOX_SPACING = 10;


    private double MOUSE_X_COORDINATE = 0; // x coordinates of mouse
    private double MOUSE_Y_COORDINATE = 0;

    private GRect box;

    private double locX;
    private double locY;

    private boolean num = true;
    private GRect[] SQUARES = new GRect[NUM_ROWS * NUM_COLS];

    public void run() {

        drawBox();
        addMouseListeners();
        for (int i = 0; i < SQUARES.length; i++) {
            moveSquare(SQUARES[i]);
            if (num == true) {
                num = false;
            } else {
                num = true;
            }
        }

    }

    private void moveSquare(GRect square) {
        while (true) {

            double x = square.getX();
            double y = square.getY();

            square.move(x < MOUSE_X_COORDINATE ? 1 : -1, y < MOUSE_Y_COORDINATE ? 1 : -1);
            pause(10);
        }
    }

    public void mouseMoved(MouseEvent mouse) {
        MOUSE_X_COORDINATE = mouse.getX();
        MOUSE_Y_COORDINATE = mouse.getY();
    }

    // The method draws black boxes
    private void drawBox() {

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                RandomGenerator rgen = RandomGenerator.getInstance();
                locX = rgen.nextDouble(0, APPLICATION_WIDTH - BOX_SIZE);
                locY = rgen.nextDouble(0, APPLICATION_HEIGHT - BOX_SIZE);
                box = new GRect(locX, locY,
                        BOX_SIZE,
                        BOX_SIZE);
                box.setFilled(true);
                box.setFillColor(Color.BLACK);
                add(box);
                SQUARES[i] = box;
            }
        }
    }
}