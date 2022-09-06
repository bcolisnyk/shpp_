package com.shpp.p2p.cs.bcolisnyk.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram {
    /*size of application start window*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /*sizes of animated elements */
    public static final double FACE_WIDTH = 200;
    private static final double FACE_HEIGHT = 230;
    private static final double EYE_WIDTH = 30;
    private static final double EYE_HEIGHT = 50;
    private static final double EYE_OFFSET_Y = 50;
    private static final double PUPIL_WIDTH = 15;
    private static final double PUPIL_HEIGHT = 15;
    private static final double PUPIL_OFFSET = 20;
    private static final double DISTANCE_BETWEEN_EYES = 40;
    private static final double NOSE_WIDTH = 20;
    private static final double NOSE_HEIGHT = 20;
    public static final double MOUTH_WIDTH = 60;
    public static final double MOUTH_HEIGHT = 30;
    public static final double DISTANCE_BETWEEN_MOUTH_AND_NOSE = 50;
    public static final double TONGUE_WIDTH = 40;
    public static final double TONGUE_HEIGHT = 20;
    public static final double HAIR_ELEMENT_WIDTH = 60;
    public static final double HAIR_ELEMENT_HEIGHT = 30;

    /* speed of moving */
    private static final double ANIMATION_SPEED = 7;
    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 24;

    /*custom colors */
    public static final Color PANTONE = new Color(255, 223, 196);
    public static final Color NOSE_COLOR = new Color(224, 207, 177);
    public static final Color PINK = new Color(255, 192,203);
    public static final Color BROWN = new Color(116, 78, 59);

    /*final positions of elements*/
    private static final double HAIR_FINAL_PISITION = 140;

    /** this program makes 5 second animation
     * on start screen user see pantone oval
     * it is the basis of the future head
     *
     * gradually, on our basic will appear eyes,
     * mouth, nose and hair*/
    public void run() {
        waitForClick();
        //print in console system start time
        long startTime = System.currentTimeMillis();
        println("Start program time = " + startTime + " ms");

        //draw and animate head
        drawStartFace();
        drawAndMoveEyes();
        drawAndMoveNoseAndMouth();
        drawAndMoveHair();

        //print in console system end time
        long endTime = System.currentTimeMillis();
        println("End program time = " + endTime + " ms");
        //count duration of animation
        println("Duration program = " + (double)(endTime - startTime)/1000 + " sec");
    }

    /*this method draw hair behind the application window
    * and moves hair on theirs position*/
    private void drawAndMoveHair() {
        double hairOffset = 2 * HAIR_ELEMENT_HEIGHT + 1;

        GOval hairElement1 = drawHairElement(
                getWidth()/2 - HAIR_ELEMENT_WIDTH/2, 0 - hairOffset);
        GOval hairElement2 = drawHairElement(
                getWidth()/2 - HAIR_ELEMENT_WIDTH, HAIR_ELEMENT_HEIGHT/2 - hairOffset);
        GOval hairElement3 = drawHairElement(
                getWidth()/2, HAIR_ELEMENT_HEIGHT/2- hairOffset);
        GOval hairElement4 = drawHairElement(
                getWidth()/2 - HAIR_ELEMENT_WIDTH/2, HAIR_ELEMENT_HEIGHT- hairOffset);
        GOval hairElement5 = drawHairElement(
                getWidth()/2 - HAIR_ELEMENT_WIDTH - HAIR_ELEMENT_WIDTH/2, HAIR_ELEMENT_HEIGHT- hairOffset);
        GOval hairElement6 = drawHairElement(
                getWidth()/2 + HAIR_ELEMENT_WIDTH - HAIR_ELEMENT_WIDTH/2, HAIR_ELEMENT_HEIGHT- hairOffset);

        animHair(hairElement1, hairElement2, hairElement3, hairElement4, hairElement5, hairElement6);
    }

    /*this method accepts 6 hair elements and move them on their position*/
    private void animHair(GOval hE1, GOval hE2, GOval hE3, GOval hE4, GOval hE5, GOval hE6) {
        while ( hE1.getY() <= HAIR_FINAL_PISITION) {
            hE1.move(0, ANIMATION_SPEED);
            hE2.move(0, ANIMATION_SPEED);
            hE3.move(0, ANIMATION_SPEED);
            hE4.move(0, ANIMATION_SPEED);
            hE5.move(0, ANIMATION_SPEED);
            hE6.move(0, ANIMATION_SPEED);

            pause(PAUSE_TIME);
        }

    }

    /*this method draw nose and mouth behind the application window
     * and moves them on theirs position*/
    private void drawAndMoveNoseAndMouth() {
        GOval nose = drawOval(getWidth() / 2 - NOSE_WIDTH / 2,
                getHeight(), NOSE_WIDTH, NOSE_HEIGHT, NOSE_COLOR, Color.black);

        GOval mouth = drawOval(getWidth() / 2 - MOUTH_WIDTH / 2,
                getHeight() + DISTANCE_BETWEEN_MOUTH_AND_NOSE,
                MOUTH_WIDTH, MOUTH_HEIGHT, Color.white, Color.black
        );

        GOval tongue = drawOval( getWidth() / 2 - TONGUE_WIDTH/2,
                getHeight() + DISTANCE_BETWEEN_MOUTH_AND_NOSE + (MOUTH_HEIGHT - TONGUE_HEIGHT),
                TONGUE_WIDTH, TONGUE_HEIGHT, PINK, PINK);

        animNoseAndMouth(nose, mouth, tongue);
    }

    /* this method accepts 3 ovals (nose, mouth and tongue)
     * and move them on their position */
    private void animNoseAndMouth(GOval nose, GOval mouth, GOval tongue) {
        while (nose.getY() >= (getHeight() / 2 - NOSE_HEIGHT)) {
            nose.move(0, -ANIMATION_SPEED);
            mouth.move(0, -ANIMATION_SPEED);
            tongue.move(0, -ANIMATION_SPEED);
            pause(PAUSE_TIME);
        }
    }

    /*this method draw eyes and pupils behind the application window
     * and moves them on theirs position*/
    private void drawAndMoveEyes() {
        GOval leftEye = drawOval(-EYE_WIDTH,
                (getHeight() - EYE_HEIGHT) / 2 - EYE_OFFSET_Y,
                EYE_WIDTH, EYE_HEIGHT, Color.white, Color.black);

        GOval rightEye = drawOval(getWidth(),
                (getHeight() - EYE_HEIGHT) / 2 - EYE_OFFSET_Y,
                EYE_WIDTH, EYE_HEIGHT, Color.white, Color.black);

        GOval leftPupil = drawOval(
                -EYE_WIDTH / 2 - PUPIL_WIDTH / 2,
                (getHeight() - EYE_HEIGHT) / 2 - EYE_OFFSET_Y + PUPIL_OFFSET,
                PUPIL_WIDTH, PUPIL_HEIGHT, Color.black, Color.black
        );

        GOval rightPupil = drawOval(
                getWidth() + EYE_WIDTH / 2 - PUPIL_WIDTH / 2,
                (getHeight() - EYE_HEIGHT) / 2 - EYE_OFFSET_Y + PUPIL_OFFSET,
                PUPIL_WIDTH, PUPIL_HEIGHT, Color.black, Color.black
        );

        animEyes(leftEye, rightEye, leftPupil, rightPupil);
    }
    /* this method accepts 4 ovals (2 eyes and 2 pupils)
     * and move them on their position */
    private void animEyes(GOval leftEye, GOval rightEye, GOval leftPupil, GOval rightPupil) {
        while (leftEye.getX() <= (getWidth() - DISTANCE_BETWEEN_EYES) / 2 - EYE_WIDTH) {
            leftEye.move(ANIMATION_SPEED, 0);
            leftPupil.move(ANIMATION_SPEED, 0);
            rightEye.move(-ANIMATION_SPEED, 0);
            rightPupil.move(-ANIMATION_SPEED, 0);
            pause(PAUSE_TIME);
        }
    }

    /*this method draw basic of future head
    * and center it*/
    private void drawStartFace() {
        GOval face = drawOval((getWidth() - FACE_WIDTH) / 2,
                (getHeight() - FACE_HEIGHT) / 2,
                FACE_WIDTH, FACE_HEIGHT, PANTONE, PANTONE);
    }

    /* this method accepts X and Y start position and
    draw brown small oval,
    * adds oval on application screen and return drawn oval*/
    private GOval drawHairElement(double x, double y) {
        GOval circle = new GOval( x ,y, HAIR_ELEMENT_WIDTH, HAIR_ELEMENT_HEIGHT);
        circle.setFilled(true);
        circle.setFillColor(BROWN);
        circle.setColor(BROWN);
        add(circle);
        return circle;
    }

    /* this method accepts start X and Y position,
    * width and height, filled color and stroke color,
    * draws oval with this parameters, add them on application width
    * and returns this oval*/
    private GOval drawOval(double x, double y, double width, double height, Color fillColor, Color color) {
        GOval oval = new GOval(x, y, width, height);
        oval.setFilled(true);
        oval.setFillColor(fillColor);
        oval.setColor(color);
        add(oval);
        return oval;
    }
}