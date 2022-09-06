package com.shpp.p2p.cs.dstriapukhin.assignment6.sg;

import acm.graphics.*;


public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        //We create an array of pixels received at the input
        int[][] pixels = source.getPixelArray();
        // The length of all arrays in the pixels array
        int numRows = pixels.length;
        // Length of the first pixel array
        int numCols = pixels[0].length;

        /*
         *Create an array of values of the total length of the pixels array and the length of the
         * first subarray of pixels
         */
        boolean[][] result = new boolean[numRows][numCols];

        // Cycle in which the number of iterations specified in numRows occurs
        for (int row = 0; row < numRows; ++row) {
            /*
             * Cycle in which the number of iterations specified in numCols occurs
             * at each iteration we get a red channel
             * and check whether it is even or not
             */
            for (int col = 0; col < numCols; ++col) {
                // We get the red channel
                int red = GImage.getRed(pixels[row][col]);

                // If red is even true
                if (red%2 != 0){

                    result[row][col]=true;

                }

            }
        }


        // Return the result of the found image
        return result;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        // Create an array of input pixels
        int[][] pixels = source.getPixelArray();
        // The length of all arrays in the pixels array
        int numRows = pixels.length;
        // Length of the first pixel array
        int numCols = pixels[0].length;

        // Cycle in which the number of iterations specified in numRows occurs
        for (int row = 0; row < numRows; ++row) {
            /*
             * Cycle in which the number of iterations specified in numCols occurs
             * at each iteration we get a red channel
             * and check whether it is even or not
             */
            for (int col = 0; col < numCols; ++col) {
                // We get the red channel
                int red = GImage.getRed(pixels[row][col]);
                // We get the green channel
                int green = GImage.getGreen(pixels[row][col]);
                // We get the blue channel
                int blue = GImage.getBlue(pixels[row][col]);
                // If the secret pixel is black, make the red channel odd
                if (message[row][col]) {
                    if (red % 2 == 0) {
                        red++;
                    }
                } else {
                    // If the secret pixel is white, make the red channel even
                    if (red % 2 != 0) {
                        red++;
                    }

                }

                 pixels[row][col] = GImage.createRGBPixel(red, green, blue);
            }
        }
        return new GImage(pixels);
    }
}
