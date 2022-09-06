package com.shpp.p2p.cs.bcolisnyk.assignment3;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    /**
     * this program starts game
     * on start user has 0$ on balance
     * every round starts with 1$ round-balance
     * and after this random generator gets 1 or 0
     * if it produces 1 - round-balance multiply by 2
     * if it produces 0 - round stop and start again
     *
     * rounds will continue until the total balance is 20
     */
    public void run() {
        int balance = 0;
        int countOfRounds = 0;
        //rounds will continue until total balance is 20
        while (balance < 20) {
            balance = playRound(balance);
            countOfRounds += 1;
        }
        //final result
        println(" It took " + countOfRounds + " games to earn $20 ");
    }

    /*this method plays round
    * every round starts with 1$ round-balance
    * and after this random generator gets 1 or 0
    * if it gets 1 - round-balance multiply by 2
    * if it gets 0 - round stop and start again*/
    private int playRound(int balance) {
        int roundBalance = 1;
        // coinflip generator produces 1 or 0
        RandomGenerator flipGenerator = RandomGenerator.getInstance();
        int coinFlip = flipGenerator.nextInt(0, 1);

        while (coinFlip == 1) {
            roundBalance = roundBalance * 2;
            coinFlip = flipGenerator.nextInt(0, 1);
        }
        //result of the round
        println("This game, you earned $" + roundBalance);
        println("Your total is $" + (balance + roundBalance));

        return (balance + roundBalance);

    }
}