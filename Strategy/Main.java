package Strategy;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int input_rows = 1000;
        int input_coloums = 1000;
        System.out.println("Input matrix "+ input_rows + "x" + input_coloums);

        int[][] matrix1 = new int[input_rows][input_coloums];
        Random random = new Random();
        for (int i = 0; i < input_coloums; i++) {
            for (int j = 0; j < input_coloums; j++) {
                matrix1[i][j] = random.nextInt(100) + 1; // Generate values between 1 and 100
            }
        }
        int[][] matrix2 = new int[input_rows][input_coloums];
        for (int i = 0; i < input_coloums; i++) {
            for (int j = 0; j < input_coloums; j++) {
                matrix2[i][j] = random.nextInt(100) + 1; // Generate values between 1 and 100
            }
        }

        // Single-threaded multiplication
        MatrixMultiplier singleThread = new MatrixMultiplier(
                matrix1, matrix2, new SingleThreadMatrixMultiplier()
        );
        int[][] resultSingle = singleThread.multiply();
        System.out.println("Single-threaded result:");
        printMatrix(resultSingle);

        // Multi-threaded multiplication
        MatrixMultiplier multiThread = new MatrixMultiplier(
                matrix1, matrix2, new MultiThreadMatrixMultiplier()
        );
        int[][] resultMulti = multiThread.multiply();
        System.out.println("Multi-threaded result:");
        printMatrix(resultMulti);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
