package com.shpp.p2p.cs.bcolisnyk.fail;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;


public class Assignment3Part6_fail extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 600;

    /*sizes of head element*/
    public static final double BODY_OFFSET_X = 0;
    public static final double BODY_OFFSET_Y = 75;
    public static final double BODY_WIDTH = 140;
    public static final double BODY_HEIGHT = 120;
    public static final double HEAD_OFFSET_X = 20;
    public static final double HEAD_OFFSET_Y = 10;
    public static final double HEAD_WIDTH = 100;
    public static final double HEAD_HEIGHT = 100;
    private static final double EYE_OFFSET_X = 20;
    private static final double EYE_OFFSET_Y = 20;
    private static final double EYE_WIDTH = 16;
    private static final double EYE_HEIGHT = 17;
    private static final double SPACE_BETWEEN_EYES = 27;
    private static final double PUPIL_WIDTH = 7;
    private static final double PUPIL_HEIGHT = 7;
    private static final double STOMACH_WIDTH = 100;
    private static final double STOMACH_HEIGHT = 80;
    private static final double NOSE_OFFSET_Y = 40;
    private static final double NOSE_WIDTH = 30;
    private static final double NOSE_HEIGHT = 15;
    private static final double MOUTH_OFFSET_Y = 65;
    private static final double MOUTH_WIDTH = 10;
    private static final double MOUTH_HEIGHT = 6;
    private static final double EAR_WIDTH = 40;
    private static final double EAR_HEIGHT = 40;
    private static final double EAR_OFFSET_X = 5;
    private static final double EAR_OFFSET_Y = 10;
    private static final double ARM_OFFSET_X = 15;
    private static final double ARM_OFFSET_Y = 90;
    private static final double ARM_WIDTH = 30;
    private static final double ARM_HEIGHT = 30;
    private static final double LEG_OFFSET_X = 10;
    private static final double LEG_OFFSET_Y = 170;
    private static final double LEG_WIDTH = 50;
    private static final double LEG_HEIGHT = 30;


    public void run() {
        double startX = 300;
        double startY = 300;

        animVinni(startX , startY);

    }

    private void animVinni(double x, double y) {
        drawBody(x, y);
        drawHead(x, y);
        drawArms(x, y);
        drawLegs(x, y);
    }

    private void drawLegs(double x, double y) {
        drawOval( x - LEG_OFFSET_X, y + LEG_OFFSET_Y, LEG_WIDTH, LEG_HEIGHT, Color.red);
        drawOval( x + BODY_WIDTH + LEG_OFFSET_X - LEG_WIDTH,
                y + LEG_OFFSET_Y, LEG_WIDTH, LEG_HEIGHT, Color.red);
    }

    private void drawArms(double x, double y) {
        drawOval(x - ARM_OFFSET_X, y + ARM_OFFSET_Y, ARM_WIDTH, ARM_HEIGHT, Color.red);
        drawOval( x + BODY_WIDTH + ARM_OFFSET_X - ARM_WIDTH,
                y + ARM_OFFSET_Y, ARM_WIDTH, ARM_HEIGHT, Color.red);
    }

    private void drawHead(double x, double y) {


        double locationHeadX = x + HEAD_OFFSET_X;
        double locationHeadY = y + HEAD_OFFSET_Y;

        drawEars(locationHeadX, locationHeadY);
        drawOval(x + HEAD_OFFSET_X, y + HEAD_OFFSET_Y, HEAD_WIDTH, HEAD_HEIGHT, Color.red);

        drawEyes(locationHeadX, locationHeadY);
        drawNose(locationHeadX, locationHeadY);
        drawMouth(locationHeadX, locationHeadY);
    }

    private void drawEars(double x, double y) {
        drawOval(x + EAR_OFFSET_X, y - EAR_OFFSET_Y, EAR_WIDTH,EAR_HEIGHT, Color.red);
        drawOval( x + HEAD_WIDTH - EAR_WIDTH - EAR_OFFSET_X, y - EAR_OFFSET_Y,
                EAR_WIDTH, EAR_HEIGHT, Color.red);
    }

    private void drawMouth(double x, double y) {
        drawOval(x + (HEAD_WIDTH - MOUTH_WIDTH)/2, y + MOUTH_OFFSET_Y, MOUTH_WIDTH, MOUTH_HEIGHT,Color.black);
    }

    private void drawNose(double x, double y) {
        drawOval(x + (HEAD_WIDTH - NOSE_WIDTH)/2 ,
                y + NOSE_OFFSET_Y, NOSE_WIDTH, NOSE_HEIGHT, Color.black);
    }

    private void drawEyes(double x, double y) {
        drawOval(x + EYE_OFFSET_X, y + EYE_OFFSET_Y, EYE_WIDTH, EYE_HEIGHT, Color.white);
        drawOval(x + EYE_OFFSET_X + EYE_WIDTH + SPACE_BETWEEN_EYES,
                y + EYE_OFFSET_Y, EYE_WIDTH, EYE_HEIGHT, Color.white);

        drawOval(x + EYE_OFFSET_X + EYE_WIDTH/2 - PUPIL_WIDTH/2,
                y + EYE_OFFSET_Y + EYE_HEIGHT - PUPIL_HEIGHT,
                PUPIL_WIDTH, PUPIL_HEIGHT, Color.black);
        drawOval(x + EYE_OFFSET_X + EYE_WIDTH + SPACE_BETWEEN_EYES + EYE_WIDTH/2 - PUPIL_WIDTH/2,
                y + EYE_OFFSET_Y + EYE_HEIGHT - PUPIL_HEIGHT,
                PUPIL_WIDTH, PUPIL_HEIGHT, Color.black);
    }

    private void drawBody(double x, double y) {
        drawOval(x + BODY_OFFSET_X, y + BODY_OFFSET_Y, BODY_WIDTH, BODY_HEIGHT, Color.red);
        drawOval(x + BODY_OFFSET_X + (BODY_WIDTH - STOMACH_WIDTH)/2,
                y + BODY_OFFSET_Y + (BODY_HEIGHT - STOMACH_HEIGHT)/2, STOMACH_WIDTH, STOMACH_HEIGHT, Color.YELLOW);
    }

    private GOval drawOval(double x, double y, double width, double height, Color color) {
        GOval oval = new GOval(x, y, width, height);
        oval.setFilled(true);
        oval.setFillColor(color);
        oval.setColor(color);
        add(oval);
        return oval;
    }
}