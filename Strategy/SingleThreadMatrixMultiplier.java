package Strategy;

public class SingleThreadMatrixMultiplier implements MatrixMultiplierInterface {
    @Override
    public int[][] multiply(int[][] matrix1, int[][] matrix2, int[][] answer) {
        int rows = matrix1.length;
        int coloums = matrix2[0].length;

        long startTime = System.nanoTime();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < coloums; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    answer[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Single Thread Run time " + String.format("%,d",endTime - startTime) + " nanoseconds");
        return answer;
    }
}
