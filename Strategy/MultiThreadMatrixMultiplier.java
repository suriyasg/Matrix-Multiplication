package Strategy;

import java.util.ArrayList;
import java.util.List;
public class MultiThreadMatrixMultiplier implements MatrixMultiplierInterface {
    @Override
    public int[][] multiply(int[][] matrix1, int[][] matrix2, int[][] answer) {
        List<Thread> threads = new ArrayList<>();

        long startTime = System.nanoTime();
        for(int i = 0, n = matrix1.length; i < n; i++){
            Thread thread = new Thread(new VectorMatrixMultiplier(matrix1[i], matrix2, answer[i]));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Multithread Run time " + String.format("%,d",endTime - startTime) + " nanoseconds");
        return answer;
    }
}
