package com.shpp.p2p.cs.bcolisnyk.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     *
     * (Yes, I know that actual pawprints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /*
     * this method draws pawprint with
     * 3 toes and 1 hell
     * it is necessary to pass position as parameters
     */
    private void drawPawprint(double x, double y) {
        drawToe(x, y, FIRST_TOE_OFFSET_X, FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        drawToe(x, y, SECOND_TOE_OFFSET_X, SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        drawToe(x, y, THIRD_TOE_OFFSET_X, THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        drawHeel(x, y, HEEL_OFFSET_X, HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);


    }

    /*
     * this method draw black filled toe of paw.
     * necessary to pass position, offset of start position,
     * width and height of toe as parameters.
    */
    private void drawToe(
            double x,
            double y,
            double offsetX,
            double offsetY,
            double width,
            double height) {
        GOval toe = new GOval(x + offsetX, y + offsetY, width, height);
        toe.setFillColor(Color.black);
        toe.setFilled(true);
        toe.setFillColor(Color.BLACK);
        add(toe);

    }
    /*
     * this method draw black filled hell of paw.
     * necessary to pass position, offset of start position,
     * width and height of heel as parameters.
     */
    private void drawHeel(
            double x,
            double y,
            double offsetX,
            double offsetY,
            double width,
            double height) {
        GOval heel = new GOval(x + offsetX, y + offsetY, width, height);
        heel.setFillColor(Color.black);
        heel.setFilled(true);
        heel.setFillColor(Color.BLACK);
        add(heel);

    }

}