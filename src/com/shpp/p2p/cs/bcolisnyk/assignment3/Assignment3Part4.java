package com.shpp.p2p.cs.bcolisnyk.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 300;
    /*size of brick*/
    public static final double BRICK_HEIGHT = 20;
    public static final double BRICK_WIDTH = 55;
    /*amount of bricks in the lowest row*/
    public static final int BRICKS_IN_BASE = 10;

    /**this program draw the pyramid
    * pyramid centered in the lower part of application window */
    public void run() {
        buildThePyramid();
    }
    /*this method draw the pyramid
     * pyramid centered in the lower part of application window */
    private void buildThePyramid() {
        //count X start position of the lowest row
        double startX = (getWidth() - BRICKS_IN_BASE * BRICK_WIDTH) / 2;
        //draw pyramid
        for (int i = 0; i < calculateCountOfRows(); i++) {
            buildTheRow(
                    startX + ((BRICK_WIDTH / 2) * i),
                    getHeight() - BRICK_HEIGHT * (i + 1),
                    BRICKS_IN_BASE - i
            );
        }
    }
    /*draw row of the brick
    * it is necessary to pass start X and Y row position
    * and count of bricks in the row*/
    private void buildTheRow(double x, double y, int countOfBricks) {
        for (int i = 0; i < countOfBricks; i++) {
            buildBrick(x + BRICK_WIDTH * i, y);
        }
    }
    /* draw red rectangle with black lining
    * it is necessary to pass start X and Y position*/
    private void buildBrick(double x, double y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setColor(Color.black);
        brick.setFilled(true);
        brick.setFillColor(Color.red);
        add(brick);
    }
    /*calculate count of rows in the pyramid
    * amount of bricks in next row less than in previous*/
    private int calculateCountOfRows() {
        int countOfRow = 0;
        int countOfBricks = BRICKS_IN_BASE;

        while (countOfBricks > 0) {
            countOfRow += 1;
            countOfBricks -= 1;
        }

        return countOfRow;
    }
}