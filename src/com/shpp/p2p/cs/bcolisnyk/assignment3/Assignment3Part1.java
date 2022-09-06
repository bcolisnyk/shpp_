package com.shpp.p2p.cs.bcolisnyk.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    /* can be 5 or 7 training day of the week */
    private static final int COUNT_OF_TRAINING_DAY_IN_THE_WEEK = 7;
    /* minimal time of training for cardiovacular health */
    private static final int TIME_NEED_FOR_CARDIOVACULAR = 30;
    /* minimal time of training for blood pressure */
    private static final int TIME_NEED_FOR_BLOOD_PRESSURE = 40;
    /* minimal count of training days in the week for cardiovacular health */
    private static final int DAYS_NEED_FOR_CARDIOVACULAR_IN_THE_WEEK = 5;
    /* minimal count of training days in the week for blood pressure */
    private static final int DAYS_NEED_FOR_BLOOD_PRESSURE_IN_THE_WEEK = 3;

    /** this program calculates the success of training
     * user enters time duration of daily training
     * then program shows results of users training week
     * */
    public void run() {
        // starting program with zeroed count of completed training days
        int cardiovularCompletedDays = 0;
        int bloodPressureCompletedDays = 0;

        // user enters time duration of daily training
        // if time of training >= necessary minimal of training
        // add one to the number of successful training
        for (int i = 0; i < COUNT_OF_TRAINING_DAY_IN_THE_WEEK; i++) {
            double timeForTraining =
                    readDouble("How many minutes did you do on day " + (i + 1) + "? : ");
            if (timeForTraining >= TIME_NEED_FOR_CARDIOVACULAR) cardiovularCompletedDays++;
            if (timeForTraining >= TIME_NEED_FOR_BLOOD_PRESSURE) bloodPressureCompletedDays++;
        }

        // show results of training
        showResultsOfTraining(cardiovularCompletedDays, bloodPressureCompletedDays);
    }

    /* this method analysts entering user`s information
    * calculate how many days were not enough for a successful training week
    * or print that user has done enough exercise for his health */
    private void showResultsOfTraining(int cardiovularCompletedDays, int bloodPressureCompletedDays) {
        // check for cardiovacular heath
        println("Cardiovacular health:");
        if (cardiovularCompletedDays >= DAYS_NEED_FOR_CARDIOVACULAR_IN_THE_WEEK) {
            println("\tGreat job! You've done enough exercise for cardiovacular health.");
        } else {
            println(
                    "\tYou needed to train hard for at least " +
                            (DAYS_NEED_FOR_CARDIOVACULAR_IN_THE_WEEK - cardiovularCompletedDays) +
                            " more day(s) a week!");
        }

        //check for blood pressure
        println("Blood pressure:");
        if (bloodPressureCompletedDays >= DAYS_NEED_FOR_BLOOD_PRESSURE_IN_THE_WEEK) {
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println(
                    "\tYou needed to train hard for at least " +
                            (DAYS_NEED_FOR_BLOOD_PRESSURE_IN_THE_WEEK - bloodPressureCompletedDays) +
                            " more day(s) a week!");
        }
    }

}







