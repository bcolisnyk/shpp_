package com.shpp.p2p.cs.apylypenko.assignment6.hg;

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
        /* Output array size corresponds max able value of luminance */
        int[] histogram = new int[MAX_LUMINANCE + 1];
        /* Runs through every input array pixel-luminance
         * summarize count of pixels for current luminance into output frequences histogram  */
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[0].length; col++) {
                //int thisPixelLuminance = luminances[row][col];
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
        /* Recognize sizes of input array */
        int[]cumulativeOutput = new int [histogram.length];
        /* Start cell filling cumulative histogram */
        cumulativeOutput[0] = histogram[0];
        /* Sumarizes all last input array values into next output cell */
        for(int i = 1; i < histogram.length; i++){
            cumulativeOutput[i] = cumulativeOutput[i-1] + histogram[i];
        }
        return cumulativeOutput;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        /* Suppose that each cell of input array corresponds to single pixel */
        return luminances.length * luminances[0].length;
        //return totalPixelsIn;
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
        /* Recognize sizes of input array */
        int[][] outputArray = new int[luminances.length][luminances[0].length];
        /* Get cumulativeHistogram for imgage luminances */
        int[]cumulativeHistogram = cumulativeSumFor(histogramFor(luminances));
        /* Get total pixels quantity */
        int totalPixels = totalPixelsIn(luminances);
        for (int row = 0; row < luminances.length; row++) {
            for (int col = 0; col < luminances[0].length; col++) {
                /* Find modifying value for each pixel lumino - it gives share of pixels which have smaller brightnes
                 * This share value gets due to value of cumulative histogram due to this pixel luminance */
                double fractionSmaller = (double)cumulativeHistogram[luminances[row][col]] / totalPixels;
                /* Mofified luminance for current pixel  */
                int newLuminance = (int)(MAX_LUMINANCE * fractionSmaller);
                /* Filling output array*/
                outputArray[row][col] = newLuminance;
            }
        }
        return outputArray;
    }
}
