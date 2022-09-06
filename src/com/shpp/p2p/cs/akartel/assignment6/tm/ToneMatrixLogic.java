package com.shpp.p2p.cs.akartel.assignment6.tm;

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
        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                //combine all the sounds while (true)
                for (int i = 0; i < samples[row].length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }
        normalizeSound(result);
        return result;
    }

    /**
     * normalize the sound wave for the range [-1.0; 1.0]
     *
     * @param accord
     */
    private static void normalizeSound(double[] accord) {
        double maxIntensity = 1; // max intensity value
        for (int i = 0; i < accord.length; i++) {
            if (Math.abs(accord[i]) > maxIntensity) {
                maxIntensity = Math.abs(accord[i]);
            }
        }
        for (int i = 0; i < accord.length; i++) {
            accord[i] /= maxIntensity;
        }
    }
}