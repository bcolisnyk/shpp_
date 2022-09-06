package com.shpp.p2p.cs.apylypenko.assignment6.tm;

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
        /* Combine total array of sound samples for current column */
        double[][] currentResultSound = getResultSoundArray(toneMatrix, column, samples, result);
        /* Sumarrize all current samples sounds into result sound */
        double[] sumSound = getSumSound(currentResultSound);
        /* Normalize sum sound for current program */
        result = getNormalizedSound(sumSound);

        return result;
    }

    /**
     * Combine total array of sound samples for current column
     */
    private static double[][] getResultSoundArray(boolean[][] toneMatrix, int column,
                                                  double[][] samples, double[] result) {
        /* Array of all sounds for this column   */
        double[][] currentResultSound = new double[toneMatrix.length][result.length];
        /* Run through certain toneMatrix column, and if need add samples to result array */
        for (int row = 0; row < toneMatrix.length; row++) {
            if(toneMatrix[row][column]){
                currentResultSound[row] = samples[row];
            }
        }
        return currentResultSound;
    }

    /**
     * Sumarrize all current samples sounds into result sound
     */
    private static double[] getSumSound(double[][] currentResultSound) {
        /* Runs through columns of input array and sumarrize values into result array */
        double[] result = new double[currentResultSound[0].length];
        double sum = 0;
        for (int col = 0; col < currentResultSound[0].length; col++) {
            for (int row = 0; row < currentResultSound.length; row++) {
                sum += currentResultSound[row][col];
            }
            result[col] = sum;
        }
        return result;
    }

    /**
     * Normalize sum sound for current program
     */
    private static double[] getNormalizedSound(double[] resultSound) {
        /* Normalize input array values to obtained max value */
        for (int i = 0; i < resultSound.length; i++) {
            if (getAbsMaximum(resultSound) > 0) {
                resultSound[i] = resultSound[i] / getAbsMaximum(resultSound);
            } else {
                resultSound[i] = 0;
            }
        }
        return resultSound;
    }

    /**
     * Get abs maximum value from array
     */
    private static double getAbsMaximum(double[] array) {
        /* Process of max value searching */
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Math.abs(max) < Math.abs(array[i])) {
                max = array[i];
            }
        }
        return Math.abs(max);
    }
}
