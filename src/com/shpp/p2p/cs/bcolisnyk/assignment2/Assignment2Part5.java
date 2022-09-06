package com.shpp.p2p.cs.bcolisnyk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 270;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /*
    * this method draw field with boxes and spaces between then
    * and place this field in the centre of application
    */
    public void run() {
        //calculate amount of spaces in each row and colum
        int amountOfSpacesInRow = NUM_COLS - 1;
        int amountOfSpacesInCol = NUM_ROWS - 1;

        //calculate size of future field
        double fieldWidth = NUM_COLS * BOX_SIZE + amountOfSpacesInRow * BOX_SPACING;
        double fieldHeight = NUM_ROWS * BOX_SIZE + amountOfSpacesInCol * BOX_SPACING;

        //calculate start position for field
        double StartPositionX = getWidth() / 2 - fieldWidth / 2;
        double StartPositionY = getHeight() / 2 - fieldHeight / 2;
        //calculate distance between previous and next box
        double step = BOX_SIZE + BOX_SPACING;

        drawTheField(StartPositionX, StartPositionY, step);
        println(getHeight());

    }

    /*
    * this method draw black filled square
    * it is necessary to pass start position of square as parameters
    */
    private void drawTheBox(double x, double y) {
        GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setFillColor(Color.BLACK);
        add(box);
    }

    /*
    * this method draw the field of boxes
    * it is necessary to pass start position and distance between previous and next boxes
    * as parameters
    */
    private void drawTheField(double x, double y, double step) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                drawTheBox(x + step * j, y + step * i);
            }
        }
    }

}