//b)
//        Given an array of even numbers sorted in ascending order and an integer k,
//        Find the k^th missing even number from provided array
//        Input a[] ={0, 2, 6, 18, 22} k=6
//        Output: 16 examples:
//        Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth
//        missing number is on 6th place of the list i.e. 16


public class Question8B {
    public static int findKthMissingEvenNumber(int[] a, int k) {
        //initializing two variables
        int currentMissingNumber = a[0];
        int countMissing = 0;

        //loop through the elementts.
        for (int i = 1; i < a.length; i++) {
            //while loop to check if there are any missing even numbers
            while (currentMissingNumber + 2 < a[i]) {
                currentMissingNumber += 2;
                countMissing++;
                if (countMissing == k) {
                    return currentMissingNumber;
                }
            }
            currentMissingNumber = a[i];
        }
//to find remaining missing even numbers
        while (countMissing < k) {
            currentMissingNumber += 2;
            countMissing++;
            if (countMissing == k) {
                return currentMissingNumber;
            }
        }
//not enough missing numbers
        return -1;
    }
    //print missing even number.
    public static void main(String[] args) {
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        int result = findKthMissingEvenNumber(a, k);
        System.out.println("The " + k + "th missing even number is: " + result);
    }
}