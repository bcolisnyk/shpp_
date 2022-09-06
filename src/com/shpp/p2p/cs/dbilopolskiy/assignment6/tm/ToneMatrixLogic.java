package com.shpp.p2p.cs.dbilopolskiy.assignment6.tm;

/**
 * Невідформатовано
 */
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
        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                result = addToResult(result, samples[i]);
            }
        }
        return normalizeSumOfSamples(result);
    }

    /**
     * This method normalise result by division on max number of the row
     *
     * @param res - array which need to be normalized
     * @return - normalized result
     */
    private static double[] normalizeSumOfSamples(double[] res) {
        double maxSample = 0;
        for (int i = 0; i < res.length; i++) {
            if (Math.abs(maxSample) < Math.abs(res[i])) {
                maxSample = res[i];
            }
        }
        if (maxSample > 1 || maxSample < -1) {
            for (int i = 0; i < res.length; i++) {
                res[i] = res[i] / maxSample;
            }
        }
        return res;
    }

    /**
     * This method adds to result row with sound
     *
     * @param sample - array with sounds
     * @param res    - input array
     * @return - input array with added row
     */
    private static double[] addToResult(double[] sample, double[] res) {
        for (int i = 0; i < sample.length; i++) {
            res[i] += sample[i];
        }
        return res;
    }
}
