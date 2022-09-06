package com.shpp.p2p.cs.dstriapukhin.assignment6.tm;

import static java.lang.Math.abs;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        //System.out.println(result[0]);

        // Cycle in which iterations occur up to the toneMatrix value
        for (int row = 0; row < toneMatrix.length; row++) {
            // At each iteration, we assign the required part to the result
            if (toneMatrix[row][column]) {
                for (int i = 0; i < result.length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }
        return normalize(result);
    }

    /**
     * Method for sound normalization
     * When we divide the sample value by a negative number, we get a change in the phase of the sound
     * (the graph of our sinusoid changes direction, and if it started up, then after normalization
     * it will start with a downward movement)
     *
     * @param result the whole array
     * @return normalized sound
     */
    private static double[] normalize(double[] result) {
        // Variable of the maximum value in the result
        double maxValueInResult = 0.0;
        // Maximum valid value
        double maxCorrectValue = 1.0;
        // Cycle in which the length of the result array is checked
        for (int i = 0; i < result.length; i++) {
            /*
             * If the positive value of result is greater than the maximum value in the result
             * then maxValueInResult we assign the primary result
             */
            if (abs(result[i]) > abs(maxValueInResult))
                maxValueInResult = result[i];
        }
        /*
         * If the positive value of result is greater than the maximum correct value
         * then a cycle begins in which the length of the result array is checked
         * and result is assigned the value of result divided by the maximum value in the result
         */
        if (abs(maxValueInResult) > maxCorrectValue) {
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] / maxValueInResult;
            }
        }
        return result;
    }
}
