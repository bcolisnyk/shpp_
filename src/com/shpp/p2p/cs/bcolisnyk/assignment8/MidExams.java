package com.shpp.p2p.cs.bcolisnyk.assignment8;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This class starts window program:
 * on screen appear black blocks,
 * when mouse is moved - first group of block approaches the cursor,
 * second group - moves away from the cursor.
 * When user clicks the mouse - groups move backwards
 */
public class MidExams extends WindowProgram {
    /**
     * Width of starting window
     */
    public static final int APPLICATION_WIDTH = 600;
    /**
     * Height of starting window
     */
    public static final int APPLICATION_HEIGHT = 800;
    /**
     * Size of rect
     */
    private static final double RECT_SIZE = 25;
    /**
     * Count of all rects
     */
    private static final int COUNT_OF_RECT = 25;
    /**
     * Count of rects in first group
     */
    private static final int FIRST_GROUP = 15;
    /**
     * Count of rects in second group
     */
    private static final int SECOND_GROUP = COUNT_OF_RECT - FIRST_GROUP;
    /**
     * Speed of moving rects
     */
    private static final double MOVESPEED = 0.3;
    /**
     * Array with first group rects
     */
    GRect[] firstGroup = new GRect[FIRST_GROUP];
    /**
     * Array with second group rects
     */
    GRect[] secondGroup = new GRect[SECOND_GROUP];
    /**
     * boolean for changing moving
     */
    boolean mousePressed = false;

    /**
     * This method draws start blocks and adds mouse listeners
     */
    public void run() {
        drawField();
        addMouseListeners();

    }

    /**
     * When mouse is clicked - change moving
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mousePressed) mousePressed = false;
        else mousePressed = true;
    }

    /**
     * When mouse is moved - move rect groups
     */
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mousePressed) {
            moveWhileFalse(mouseEvent);
        } else
            moveWhileTrue(mouseEvent);

    }

    /**
     * This method moves first group of block further away from the cursor,
     * second group - closer
     */
    private void moveWhileFalse(MouseEvent mouseEvent) {
        // move first group
        for (int i = 0; i < firstGroup.length; i++) {

            if (firstGroup[i].getX() > mouseEvent.getX()) {
                firstGroup[i].move(MOVESPEED, 0);
            } else {
                firstGroup[i].move(-MOVESPEED, 0);
            }

            if (firstGroup[i].getY() > mouseEvent.getY()) {
                firstGroup[i].move(0, MOVESPEED);
            } else {
                firstGroup[i].move(0, -MOVESPEED);
            }
        }
        //move second group
        for (int i = 0; i < secondGroup.length; i++) {

            if (secondGroup[i].getX() > mouseEvent.getX()) {
                secondGroup[i].move(-MOVESPEED, 0);
            } else {
                secondGroup[i].move(MOVESPEED, 0);
            }

            if (secondGroup[i].getY() > mouseEvent.getY()) {
                secondGroup[i].move(0, -MOVESPEED);
            } else {
                secondGroup[i].move(0, MOVESPEED);
            }
        }
    }

    /**
     * This method moves first group of blocks closer to the cursor,
     * second group - farther
     */
    private void moveWhileTrue(MouseEvent mouseEvent) {
        //move first group
        for (int i = 0; i < firstGroup.length; i++) {

            if (firstGroup[i].getX() - RECT_SIZE/2  > mouseEvent.getX()) {
                firstGroup[i].move(-MOVESPEED, 0);
            } else {
                firstGroup[i].move(MOVESPEED, 0);
            }

            if (firstGroup[i].getY() - RECT_SIZE/2 > mouseEvent.getY()) {
                firstGroup[i].move(0, -MOVESPEED);
            } else {
                firstGroup[i].move(0, MOVESPEED);
            }
        }
        //move second group
        for (int i = 0; i < secondGroup.length; i++) {

            if (secondGroup[i].getX() - RECT_SIZE/2 > mouseEvent.getX()) {
                secondGroup[i].move(MOVESPEED, 0);
            } else {
                secondGroup[i].move(-MOVESPEED, 0);
            }

            if (secondGroup[i].getY() - RECT_SIZE/2 > mouseEvent.getY()) {
                secondGroup[i].move(0, MOVESPEED);
            } else {
                secondGroup[i].move(0, -MOVESPEED);
            }
        }
    }

    /**
     * This method draw two groups of blocks in random position
     * and adds them in arrays
     */
    private void drawField() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        // fill first group
        for (int i = 0; i < firstGroup.length; i++) {
            firstGroup[i] = drawRect(rgen.nextInt(0,
                    (int) (getWidth() - RECT_SIZE)), rgen.nextInt(0, (int) (getHeight() - RECT_SIZE)), Color.black);
        }
        // fill second group
        for (int i = 0; i < secondGroup.length; i++) {
            secondGroup[i] = drawRect(rgen.nextInt(0,
                    (int) (getWidth() - RECT_SIZE)), rgen.nextInt(0, (int) (getHeight() - RECT_SIZE)), Color.black);
        }
    }

    /**
     * This method draws and returns rect
     * x - x coordinate
     * y - y coordinate
     * color - color of rect
     */
    public GRect drawRect(double x, double y, Color color) {
        GRect rect = new GRect(x, y, RECT_SIZE, RECT_SIZE);
        rect.setFilled(true);
        rect.setFillColor(color);
        rect.setColor(color);
        add(rect);
        return rect;
    }
}
