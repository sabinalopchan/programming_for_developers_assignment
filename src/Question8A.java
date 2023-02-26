//Question 8
//        a)
//        Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
//        INPUT: 1 0 1 0 0
//        0 1 1 1 1
//        0 0 0 0 1
//        0 0 0 1 1
//        0 1 0 1 1
//        OUTPUT: 4

import java.util.Stack;
 class MaxSquareArea {
    public static int maxSquareArea(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1
        int[] histogram = new int[cols];
        int maxArea = 0;

        for (int i = 0; i < cols; i++) {
            histogram[i] = matrix[0][i];
            maxArea = Math.max(maxArea, histogram[i]);
        }

        // Step 2-3
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("matrix[i][j]"+matrix[i][j]);
                if (matrix[i][j] == 0) {
                    System.out.println("1st "+histogram[j]);
                    histogram[j] += matrix[i - 1][j];
                    System.out.println("histogram[j]"+histogram[j]);
                } else {
                    histogram[j] = 0;
                }
            }
            // Step 4
            maxArea = Math.max(maxArea, maxHistogramArea(histogram));
        }

        // Step 5
        return maxArea;
    }

    public static int maxHistogramArea(int[] histogram) {
        int n = histogram.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || histogram[stack.peek()] > histogram[i])) {
                int height = histogram[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 0, 1}};
        int maxArea = maxSquareArea(matrix);
        System.out.println("Maximum area of square made by 0s: " + maxArea);
    }
}