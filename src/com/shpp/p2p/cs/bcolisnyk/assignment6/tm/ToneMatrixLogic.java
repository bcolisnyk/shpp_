package com.shpp.p2p.cs.bcolisnyk.assignment6.tm;



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
        //count of active notes in a column
        int countOfNotes = 0;
        //if note is active, add array with note to result array
        for (int row = 0; row < toneMatrix.length; row ++) {
            if (toneMatrix[row][column]) {
                for (int i = 0; i < result.length; i++) {
                    result[i] += samples[row][i];
                }
                countOfNotes++;
            }
        }

        //if count of active notes > 1, normalize tha result array
        if (countOfNotes > 1) {
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] / countOfNotes;
            }
        }

        return result;
    }
}
