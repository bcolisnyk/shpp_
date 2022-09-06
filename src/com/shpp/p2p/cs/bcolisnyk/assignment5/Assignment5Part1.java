package com.shpp.p2p.cs.bcolisnyk.assignment5;


import com.shpp.cs.a.console.TextProgram;


public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
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
        // create string with lower case word
        String str = word.toLowerCase();
        int syllables = 0;

        // check every symbol of word
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (checkForVowel(ch)) {
                // if previous symbol was vowel - we do not count as a syllable
                if (i != 0) {
                    if (checkForVowel(str.charAt(i - 1))) continue;
                }
                // if 'e' - is the last letter of word - we do not count as a syllable
                if (i == str.length() - 1) {
                    if (ch == 'e') continue;
                }
                // if none of the above conditions matched - increase the number of syllables by 1
                syllables++;
            }
        }

        // if our algorithm didn't find one syllable - return syllables == 1
        if (syllables == 0) syllables = 1;
        return syllables;
    }

    /* Given a symbol and check this symbol for vowel english letter (include 'y)
    * check only in lower case
    *
    * if symbol is vowel - return true
    * else - return false */
    private boolean checkForVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y';
    }
}