package com.shpp.p2p.cs.bcolisnyk.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;


public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * this method starts "Breakout" game
     */
    public void run() {
        playBreakout();
    }

    /* create global object - paddle */
    GRect paddle = drawPaddle();
    /* create global variable, which calculate count of bricks on the screen */
    int coutOfBrick = 0;
    /* create global variable, which are responsible for the balls move-speed */
    double vx = 0; // speed X axis
    double vy = 3; // speed Y axis

    /* create global variable, which contains the current number of lives*/
    int lifes = NTURNS;
    /* create random generator*/
    RandomGenerator rgen = RandomGenerator.getInstance();
    /* create global object - collider
     * collider - is a object which the ball hit at a given moment */
    GObject collider = null;

    /**
     * this method start Breakout game
     * in bottom part of screen user has paddle
     * this paddle moves with mouse-moving
     * <p>
     * on upper part of screen, user has set of bricks and ball in middle of screen
     * when game starting, ball starts to move
     * when ball hits the brick, brick is removed
     * balls can hit left, right and top edges
     * but can`t hit down edges ( - 1 life )
     * <p>
     * the game continues until users lost 3 life, or until all bricks will be removed
     */
    private void playBreakout() {
        addMouseListeners();
        drawBricks();               //draw bricks in bottom part of screen
        GOval ball = drawBall();    // create ball object
        moveBall(ball);             // ball this ball

    }

    /* this method draw set of bricks in bottom part of screen
     *  and counts quantity of bricks */
    private void drawBricks() {
        double startX =
                (getWidth() - (NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP)) / 2;
        double stepX = BRICK_WIDTH + BRICK_SEP;

        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                drawBrick((startX + stepX * j), BRICK_Y_OFFSET + ((BRICK_HEIGHT + BRICK_SEP) * i), i);
                coutOfBrick++;
            }
        }
    }

    /* draw one brick
     * pass as a parameters start point (x, y)
     * and number of row (i) for chose color of brick  */
    private void drawBrick(double x, double y, int i) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(findColor(i));
        brick.setFillColor(findColor(i));
        add(brick);
    }

    /* find color of brick in current row
     * Each two rows has a new color in the sequence:
     * RED, ORANGE, YELLOW, GREEN, CYAN.*/
    private Color findColor(int i) {
        if (i == 0 || i == 1 || i % 10 == 0 || i % 10 == 1) return Color.red;
        if (i == 2 || i == 3 || i % 10 == 2 || i % 10 == 3) return Color.orange;
        if (i == 4 || i == 5 || i % 10 == 4 || i % 10 == 5) return Color.yellow;
        if (i == 6 || i == 7 || i % 10 == 6 || i % 10 == 7) return Color.green;
        if (i == 8 || i == 9 || i % 10 == 8 || i % 10 == 9) return Color.cyan;

        return Color.black;
    }

    /* this method moves paddle with mouse-moved */
    public void mouseMoved(MouseEvent me) {
        paddle.setLocation(me.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
    }


    /* when game starting, ball starts to move
     * when ball hits the brick, brick is removed
     * balls can hit left, right and top edges
     * but can`t hit down edges ( - 1 life )
     *
     * the game continues until users lost 3 life, or until all bricks will be removed*/
    private void moveBall(GOval ball) {
        waitForClick();
        while (lifes > 0) {
            ball.move(vx, vy);
            // when ball hits left of right edges change direction of movement along the axis X
            if (checkHorizontalEdges(ball)) {
                vx = -vx;
            }
            // when ball hits upper edge change direction of movement along the axis X
            if (checVerticalEdges(ball))
                vy = -vy;
            //if ball hits any brick - this bricks will be removed and change ball direction
            //if ball hits paddle, ball only change direction
            collider = getCollidingObject(ball);
            if (collider != null) {
                checkForRemove(collider);
                changeDirection();
            }
            //if count of bricks == 0 , game end
            if (coutOfBrick == 0) {
                break;
            }
            //if ball fell below the bottom part of screen
            // - 1 life
            if (ball.getY() >= getHeight()) {
                lifes--;
                ball.setLocation(getWidth() / 2 - BALL_RADIUS / 2,
                        getHeight() / 2 - BALL_RADIUS / 2);
                lostLifeLabel(lifes);
            }

            pause(1000 / 120);
        }

        result(lifes);
    }

    /*this method change direction of moving ball
    * for X - this code assigns a random value of type double in the range from 1.0 to 3.0
    * and then, with a probability of 50%
    * changes the sign of the resulting number to minus.
    * for Y - every time change sign of number to minus.*/
    private void changeDirection() {
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5)) {
            vx = -vx;
        }
        vy = -vy;
    }

    /*when user lost 1 lif, he sees label on centre of screen
    * and after click, continue game*/
    private void lostLifeLabel(int lives) {
        GLabel result = new GLabel(lives + " live(s) left");
        result.setFont("Verdana-30");
        add(result, getWidth() / 2 - result.getWidth() / 2, getHeight() / 2 - result.getHeight() / 2);

        waitForClick();
        remove(result);
    }

    /* when ball stops moving, we check count of lifes,
    * if > 0, write "Win game", else write "Lost game" */
    private void result(double lifes) {
        if (lifes > 0) {
            winGame();
        } else loseGame();
    }

    /*if lives == 0, user lost game and see label */
    private void loseGame() {
        GLabel result = new GLabel(" YOU LOST ");
        result.setFont("Verdana-30");
        add(result, getWidth() / 2 - result.getWidth() / 2, getHeight() / 2 - result.getHeight() / 2);
    }

    /*if count of bricks == 0, user win the game and see label */
    private void winGame() {
        GLabel result = new GLabel("YOU WIN");
        result.setFont("Verdana-30");
        add(result, getWidth() / 2 - result.getWidth() / 2, getHeight() / 2 - result.getHeight() / 2);
    }

    /* if collider is brick, program removes this brick*/
    private void checkForRemove(GObject collider) {
        if (collider != paddle) {
            remove(collider);
            coutOfBrick--;
        }
    }

    /*check 4 ball point, and check objects in this point
    * if object is present, return this objec,
    * else return null */
    private GObject getCollidingObject(GOval ball) {
        if (getElementAt(ball.getX(), ball.getY()) != null)
            return getElementAt(ball.getX(), ball.getY());

        if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null)
            return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);

        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null)
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());

        if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null)
            return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);

        return null;
    }

    /*check ball hit in upper part of screen*/
    private boolean checVerticalEdges(GOval ball) {
        if (ball.getY() <= 0) return true;
        return false;
    }
    /*check ball hit in left or right part of screen*/
    private boolean checkHorizontalEdges(GOval ball) {
        if (ball.getX() <= 0) return true;
        if (ball.getX() + 2 * BALL_RADIUS >= getWidth()) return true;
        return false;
    }

    /* draw and return black filled ball*/
    private GOval drawBall() {
        GOval ball = new GOval(getWidth() / 2 - BALL_RADIUS / 2,
                getHeight() / 2 - BALL_RADIUS / 2, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        ball.setFillColor(Color.black);
        ball.setColor(Color.black);
        add(ball);
        return ball;
    }
    /*draw and return black rect in bottom part of screen */
    private GRect drawPaddle() {
        GRect paddle = new GRect(getWidth() / 2 - PADDLE_WIDTH / 2,
                getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.black);
        paddle.setColor(Color.black);
        add(paddle);
        return paddle;
    }
}