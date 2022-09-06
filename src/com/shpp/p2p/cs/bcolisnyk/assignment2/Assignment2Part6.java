package com.shpp.p2p.cs.bcolisnyk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /*count and size of circles*/
    private static final int COUNT_OF_CIRCLES = 8;
    private static final double DIAMETER = 100;
    private static final double RADIUS = DIAMETER / 2;

    /*distant between each upper circles, and between each bottom circles*/
    private static final double STEP = DIAMETER/6;

    /*this method draw caterpillar made of circles*/
    public void run() {
        //with this variable we alternate upper and bottom cirle
        boolean upperPosition = false;
        for (int i = 0; i < COUNT_OF_CIRCLES; i++) {
            if (upperPosition) {
                drawCircle(RADIUS + ((DIAMETER+STEP)*(i/2)), 0);
                upperPosition = false;
            } else {
                drawCircle(0 + ((DIAMETER+STEP)*(i/2)), RADIUS);
                upperPosition = true;
            }
        }

    }

    /*
    * this method draw green filled circle with red stroke
    * it is necessary start position of circle as parameters*/
    private void drawCircle(double x, double y) {
        GOval circle = new GOval(x, y, DIAMETER, DIAMETER);
        circle.setColor(Color.RED);
        circle.setFilled(true);
        circle.setFillColor(Color.GREEN);
        add(circle);
    }
}
