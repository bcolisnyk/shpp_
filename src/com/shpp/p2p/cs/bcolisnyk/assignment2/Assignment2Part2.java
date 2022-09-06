package com.shpp.p2p.cs.bcolisnyk.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {

    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    /*size of circles*/
    public static final int R = 50;
    public static final int D = 2 * R;

    /*
    * this method draws 4 black filled circles in corners of application window
    * and 1 white filled rectangle in the centre of application window
    * rectangle will overlap a quarter of each circle
    *  */
    public void run() {
        //draw circle in left upper corner
        drawCircle(0, 0, D);
        //draw circle in right upper corner
        drawCircle(getWidth() - D, 0, D);
        //draw circle in left bottom corner
        drawCircle(0, getHeight() - D, D);
        //draw circle in right bottom corner
        drawCircle(getWidth() - D, getHeight() - D, D);
        drawRectangleInTheCentre();
    }

    /*
    * this method always draws black filled circles
    * it is necessary to pass position as parameters
    */
    private void drawCircle(double x, double y, double D) {
        GOval circle = new GOval(x, y, D, D);
        circle.setFilled(true);
        circle.setFillColor(Color.BLACK);
        add(circle);
        println(circle.getLocation());
    }


    /*
    * this method draws white filled rectangle .
    * rectangle will overlap a quarter of each circle
    */
    private void drawRectangleInTheCentre() {
        double width = getWidth() - D;
        double height = getHeight() - D;
        GRect rectangle = new GRect(
                (getWidth() / 2) - (width / 2),
                (getHeight() / 2) - (height / 2),
                width, height);
        rectangle.setFilled(true);
        rectangle.setFillColor(Color.WHITE);
        rectangle.setColor(Color.WHITE);
        add(rectangle);
    }
}
