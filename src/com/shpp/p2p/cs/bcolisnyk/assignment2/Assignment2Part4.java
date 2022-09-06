package com.shpp.p2p.cs.bcolisnyk.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /*size of flag stripe*/
    public static final double STRIP_HEIGHT = 200;
    public static final double STRIP_WIDTH = 80;


    /*
    * this method draw tricolor flag,
    * place it in the centre of application
    *  and signs it
    */
    public void run() {
        drawTricolor(Color.BLACK, Color.YELLOW, Color.RED);
        drawLabel("Belgium ");
    }

    /*
    * this method draw 3 strips of flag
    * and place it in the centre of application
    * it is necessary to pass color of strips in the right order as parameters
    */
    private void drawTricolor(Color color1, Color color2, Color color3) {
        //draw left strip of tricolor
        drawStrip(
                (getWidth() / 2) - (STRIP_WIDTH * 1.5),
                (getHeight() / 2) - (STRIP_HEIGHT / 2),
                STRIP_WIDTH, STRIP_HEIGHT, color1
        );
        //draw middle strip of tricolor
        drawStrip(
                (getWidth() / 2) - (STRIP_WIDTH / 2),
                (getHeight() / 2) - (STRIP_HEIGHT / 2),
                STRIP_WIDTH, STRIP_HEIGHT, color2
        );
        //draw right strip of tricolor
        drawStrip(
                (getWidth() / 2) + (STRIP_WIDTH / 2),
                (getHeight() / 2) - (STRIP_HEIGHT / 2),
                STRIP_WIDTH, STRIP_HEIGHT, color3
        );
    }

    /*
    * this method draw strip of flag
    * it is necessary to pass start position,
    * width, height and color of strip as parameters
    */
    private void drawStrip(double x, double y, double width, double height, Color color) {
        GRect strip = new GRect(x, y, width, height);
        strip.setColor(color);
        strip.setFilled(true);
        strip.setFillColor(color);
        add(strip);

    }
    /*
    * this method draw the label with name of country
    * and place it in right bottom corner
    * jt is necessary to pass name of country as parameters*/
    private void drawLabel(String nameOfCountry) {
        GLabel label = new GLabel("Flag of " + nameOfCountry);
        label.setFont("Verdana-30");
        add(label, getWidth() - label.getWidth(), getHeight()-label.getDescent());

    }
}
