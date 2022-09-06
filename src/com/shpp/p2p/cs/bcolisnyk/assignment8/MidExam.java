package com.shpp.p2p.cs.bcolisnyk.assignment8;


import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This class starts game :
 * On screen appear START_GRAINS grains (black rect), and
 * falling down.
 * Mouse click create new blue rect, which falling down too.
 * When blue rect is catching up black rect - they become green rects
 * When blue rect is catching up green rect - it becomes green too
 */
public class MidExam extends WindowProgram {
    /**
     * Max count of rects in row
     */
    public static final int MAX_BLOCK_ROW = 20;
    /**
     * Max count of rects in column
     */
    public static final int MAX_BLOCK_COL = 15;
    /**
     * Width and height of rect
     */
    public static final double BLOCK_SIZE = 30;
    /**
     * Height of top panel, if on your device
     * getHeight() == APPLICATION_HEIGHT, set this const == 0
     */
    public static final int TOP_PANEL_HEIGHT = 23;
    /**
     * Width of starting window
     */
    public static final int APPLICATION_WIDTH = (int) (BLOCK_SIZE * MAX_BLOCK_COL);
    /**
     * Height of starting window
     */
    public static final int APPLICATION_HEIGHT = (int) (BLOCK_SIZE * MAX_BLOCK_ROW) + TOP_PANEL_HEIGHT;
    /**
     * Count of black rects on starting screen
     */
    public static final int START_GRAINS = 10;
    /**
     * Array with all blocks on screen
     */
    GRect[][] blockField = new GRect[MAX_BLOCK_COL][MAX_BLOCK_ROW];

    /**
     * This method draw start field and moves all rects
     */
    public void run() {
        addMouseListeners();
        drawStartField();

        while (true) {
            moveBlocks();
            checkBlocks();
            pause(1000);
        }
    }

    /* This method check block for cases :
     *  when blue block is catching up black block - they become 2 green blocks
     *  when blue block is catching up green block - blue block become green */
    private void checkBlocks() {
        for (int i = 0; i < blockField.length; i++) {
            for (int j = 1; j < blockField[0].length; j++) {
                // check for case blue + black
                if (blockField[i][j].getFillColor() == Color.black &&
                        blockField[i][j - 1].getFillColor() == Color.blue) {
                    changeBlockColor(blockField[i][j], Color.green);
                    changeBlockColor(blockField[i][j - 1], Color.green);
                }

                // check for case blue + green
                if (blockField[i][j].getFillColor() == Color.green &&
                        blockField[i][j - 1].getFillColor() == Color.blue) {
                    changeBlockColor(blockField[i][j - 1], Color.green);
                }

            }
        }
    }

    /* This method add mouseClicked listener, and when user clicks on screen -
     *  white ( only white blocks ) change colors on blue */
    public void mouseClicked(MouseEvent me) {
        if (getElementAt(me.getX(), me.getY()).getColor() == Color.white) {
            changeBlockColor((GRect) getElementAt(me.getX(), me.getY()), Color.blue);
        }
    }

    /*This method moves all blocks one cell down
     * If block already at the bottom and if this is no water ( blue rect )
     * Don`t moves block and go to next block */
    private void moveBlocks() {
        for (int j = blockField[0].length - 1; j >= 0; j--) {
            for (int i = blockField.length - 1; i >= 0; i--) {

                if (j != blockField[0].length - 1 &&
                        blockField[i][j + 1].getFillColor() != Color.white)
                    continue;

                if (blockField[i][j].getFillColor() != Color.white) {
                    if (j != blockField[0].length - 1) {
                        changeBlockColor(blockField[i][j + 1], blockField[i][j].getFillColor());
                        changeBlockColor(blockField[i][j], Color.white);
                    } else if (blockField[i][j].getFillColor() == Color.blue)
                        changeBlockColor(blockField[i][j], Color.white);
                }
            }
        }
    }

    /*This method draw start field
     * It draws array with white block and add it on screen
     * After this - change color of random rects */
    private void drawStartField() {
        //draw array with all white blocks
        for (int i = 0; i < blockField.length; i++) {
            for (int j = 0; j < blockField[0].length; j++) {
                blockField[i][j] = drawBlock(i, j, Color.white);
            }
        }

        int countOfGrains = 0;
        RandomGenerator rgen = RandomGenerator.getInstance();
        GRect block;
        // draw START_GRAINS ( black rects )
        while (countOfGrains < START_GRAINS) {
            block = blockField[rgen.nextInt(0, blockField.length - 1)][rgen.nextInt(0, blockField[0].length - 1)];

            if (block.getFillColor() == Color.white) {
                changeBlockColor(block, Color.black);
                countOfGrains++;
            }
        }
    }

    /* This method change color of block
     * block - GRect, the color of which we change
     * color - new color of GRect*/
    private void changeBlockColor(GRect block, Color color) {
        block.setColor(color);
        block.setFillColor(color);
    }

    /* This method draw block ( rect ) and return this block
     * i and j - indices of array with blocks
     * color - color of rect */
    private GRect drawBlock(int i, int j, Color color) {
        GRect block = new GRect(i * BLOCK_SIZE, j * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        block.setFilled(true);
        block.setColor(color);
        block.setFillColor(color);
        add(block);
        return block;
    }
}
