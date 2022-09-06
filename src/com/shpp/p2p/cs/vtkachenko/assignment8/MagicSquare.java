package com.shpp.p2p.cs.vtkachenko.assignment8;

import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/*
 * File: MagicSquare.java
 * ---------------------
 * This program implements the games with mouse and squares.
 */
public class MagicSquare extends WindowProgram implements MagicSquareConstants {

    RandomGenerator rgen = new RandomGenerator();

    /* For store squares */
    ArrayList<Square> oneList = new ArrayList<>();
    ArrayList<Square> twoList = new ArrayList<>();

    /* Change of position  */
    double delta = 0;

    /* The last mouse position  */
    private GPoint last;

    public void run() {
        addMouseListeners();
        oneSquares();
        twoSquares();
    }

    /* Called on mouse press to record the coordinates of the click */
    public void mousePressed(MouseEvent e) {
        last = new GPoint(e.getPoint());
    }

    /* Called where the mouse moved */
    public void mouseMoved(MouseEvent mouseEvent) {
        if (last != null) {
            delta = mouseEvent.getX() - last.getX();
        }
        update(delta);
    }

    /**
     * Method redraw everything.
     *
     * @param delta Change position.
     */
    private void update(double delta) {
        removeAll();
        for (int i = 0; i < COUNT; i++) {

            drawSquare(oneList.get(i).x + delta, oneList.get(i).y + delta);

            drawSquare(twoList.get(i).x - delta, twoList.get(i).y - delta);
        }
    }

    /* The method creates squares that move in one direction*/
    private void twoSquares() {
        for (int i = 0; i < COUNT; i++) {

            Square square = new Square(rgen.nextInt(0, getWidth() - SQUARE_WIDTH),
                    rgen.nextInt(0, getHeight() - SQUARE_WIDTH));
            twoList.add(square);

            drawSquare(twoList.get(i).x, twoList.get(i).y);
        }
    }

    /* The method creates squares that move in the opposite direction */
    private void oneSquares() {
        for (int i = 0; i < COUNT; i++) {
            Square square = new Square(rgen.nextInt(0, getWidth() - SQUARE_WIDTH),
                    rgen.nextInt(0, getHeight() - SQUARE_WIDTH));
            oneList.add(square);

            drawSquare(oneList.get(i).x, oneList.get(i).y);
        }
    }

    /**
     * Draw black squares.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private void drawSquare(double x, double y) {
        GRect rect = new GRect(x,
                y,
                SQUARE_WIDTH,
                SQUARE_WIDTH);

        rect.setColor(Color.BLACK);
        rect.setFilled(true);
        add(rect);
    }
}



