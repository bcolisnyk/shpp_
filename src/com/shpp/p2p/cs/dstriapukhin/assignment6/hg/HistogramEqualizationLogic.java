package com.shpp.p2p.cs.dstriapukhin.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        // The length of all the arrays in the array luminances
        int rows = luminances.length;
        //The length of the first array luminances
        int cols = luminances[0].length;
        // A histogram array that has length MAX_LUMINANCE plus one
        int[] histogram = new int[MAX_LUMINANCE + 1];
        // The loop iterates through the entire array row by row
        for (int row = 0; row < rows; row++) {
            // A cycle in which at each iteration the value of the histogram array increases by one
            for (int col = 0; col < cols; col++) {
                histogram[luminances[row][col]]++;
            }
        }
        return histogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        // We create an array of values with the length of a histogram
        int[] cumulativeSum = new int[histogram.length];
        //Initial index
        int temp = cumulativeSum[0];
        // A cycle in which at each iteration we add all the values
        for (int i = 0; i < cumulativeSum.length; i++) {
            cumulativeSum[i] = histogram[i] + temp;
            temp = cumulativeSum[i];
        }
        return cumulativeSum;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        // We multiply all the pixels in each array
        int totalPixels = luminances.length * luminances[0].length;
        return totalPixels;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        // The length of all the arrays in the array luminances
        int rows = luminances.length;
        // The length of the first array luminances
        int cols = luminances[0].length;
        // We create arrays
        int[] histogram = histogramFor(luminances);
        int[] cumulativeHistogram = cumulativeSumFor(histogram);
        int totalPixels = totalPixelsIn(luminances);
        // We create a new array of image pixels
        int[][] newLuminance = new int[rows][cols];
        // The cycle iterates the entire array row by row
        for (int row = 0; row < rows; row++) {
            // The loop goes through the entire content of each row and replaces the value
            for (int col = 0; col < cols; col++) {
                // We create a variable fractionSmaller
                double fractionSmaller = (double) cumulativeHistogram[luminances[row][col]] / totalPixels;
                // Calculation according to the formula
                newLuminance[row][col] = (int) (MAX_LUMINANCE * fractionSmaller);
            }
        }
        return newLuminance;
    }
}
