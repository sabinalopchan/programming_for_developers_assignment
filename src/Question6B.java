//Question 6
//b) You are given an array of different words and target words. Each character of a word represents a different
//        digit ranging from 0 to 9, and no two character are linked to same digit. If the sum of the numbers represented
//        by each word on the array equals the sum the number represented by the targeted word, return true; otherwise,
//        return false. Note: Provided list of words and targeted word is in the form of equation
//        Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
//        Output: true
//        Explanation:
//        s=6
//        I=5
//        X=0,
//        E=8,
//        V=7,
//        N=2,
//        T=1,
//        W=3,
//        Y=4
//        SIX +SEVEN + SEVEN = TWENTY
//        650 + 68782 + 68782 = 138214

import java.util.*;

public class Question6B {
    // Map to associate each letter to its corresponding digit
    private static final Map<Character, Integer> LETTER_TO_DIGIT = new HashMap<Character, Integer>() {{
        put('S', 6);
        put('I', 5);
        put('X', 0);
        put('E', 8);
        put('V', 7);
        put('N', 2);
        put('T', 1);
        put('W', 3);
        put('Y', 4);
    }};

    public static boolean isSumEqual(String[] words, String result) {
        // Compute the sum of the numbers represented by each word in the array
        int sum = 0;
        for (String word : words) {
            int value = 0;
            for (char c : word.toCharArray()) {
                value = value * 10 + LETTER_TO_DIGIT.get(c);
            }
            sum += value;
        }

        // Compute the number represented by the targeted word
        int target = 0;
        for (char c : result.toCharArray()) {
            target = target * 10 + LETTER_TO_DIGIT.get(c);
        }

        // Check if the sum of the numbers in the array equals the number represented by the targeted word
        return sum == target;
    }

    public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        boolean res = isSumEqual(words, result);
        System.out.println(res);
    }
}
