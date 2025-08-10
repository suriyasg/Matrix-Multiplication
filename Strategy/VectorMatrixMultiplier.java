package Strategy;

public class VectorMatrixMultiplier implements Runnable {
    private int[] vector;
    private int[][] matrix;
    private int[] answer;


    public VectorMatrixMultiplier(int[] vector, int[][] matrix, int[] answer) {
        this.vector = vector;
        this.matrix = matrix;
        this.answer = answer;
    }

    public void multiply(){
        for(int k = 0; k < vector.length; k++) {
            answer[k] = 0;
        }

        for(int i = 0; i < matrix.length; i++) {
            for(int k = 0; k < vector.length; k++) {
                answer[k] +=  vector[i] * matrix[i][k];
            }
        }
    }

    @Override
    public void run() {
        this.multiply();
    }
}
