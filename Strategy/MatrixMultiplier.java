package Strategy;

public class MatrixMultiplier {
    private int[][] matrix1;
    private int[][] matrix2;
    private MatrixMultiplierInterface matrixMultiplier;
    
    public MatrixMultiplier(int[][] matrix1, int[][] matrix2, MatrixMultiplierInterface matrixMultiplier) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.matrixMultiplier = matrixMultiplier;
    }

    public int[][] multiply() {
        int rows = this.matrix1.length;
        int coloums = this.matrix2[0].length;
        int[][] answer = new int[rows][coloums];

        return matrixMultiplier.multiply(matrix1, matrix2, answer);
    }
}
