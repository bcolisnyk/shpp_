package com.shpp.p2p.cs.dkovalov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word.toLowerCase()));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int countSyllables = 0;
        for (int i = 0; i < word.length(); i++) {

            //count vowels
            if ((word.charAt(i) == 'a' || word.charAt(i) == 'e') ||     /** Винести в окремий метод */
                    (word.charAt(i) == 'i' || word.charAt(i) == 'o') ||
                    (word.charAt(i) == 'u' || word.charAt(i) == 'y')) {
                countSyllables++;

                //checking the previous letter for a vowel
                if (i >= 1) {
                    if ((word.charAt(i - 1) == 'a' || word.charAt(i - 1) == 'e') ||
                            (word.charAt(i - 1) == 'i' || word.charAt(i - 1) == 'o') ||
                            (word.charAt(i - 1) == 'u' || word.charAt(i - 1) == 'y')) {
                        countSyllables--;
                    }
                }
            }
        }
        //checking the last letter for a 'e'
        if (word.charAt(word.length() - 1) == 'e') {
            countSyllables--;
            //check length word. If the word is less than 3 letter, then output one syllable
            if (word.length() <= 3 || word.charAt(word.length() - 2) == 'e') {
                countSyllables++;
            }
        }
        return countSyllables;
    }
}
