# Matrix Multiplication Mini Project

## Overview

This project demonstrates the **Strategy Design Pattern** by implementing matrix multiplication using different strategies — single-threaded and multi-threaded approaches.

The goal is to see how we can encapsulate different algorithms behind a common interface and swap them at runtime easily.

---

## Project Structure

```bash
    .root
    ├── classes
    │   └── Strategy
    ├── README.md
    └── Strategy
        ├── Main.java
        ├── MatrixMultiplier.java
        ├── MatrixMultiplierInterface.java
        ├── MultiThreadMatrixMultiplier.java
        ├── SingleThreadMatrixMultiplier.java
        └── VectorMatrixMultiplier.java
```
- `MatrixMultiplierInterface` — defines the contract for matrix multiplication strategies.
- `SingleThreadMatrixMultiplier` — performs matrix multiplication in a single thread.
- `MultiThreadMatrixMultiplier` — performs matrix multiplication using multiple threads (one row per thred).
- `VectorMatrixMultiplier` — helper class implementing `Runnable` for per-row multiplication in the multi-threaded approach.
- `MatrixMultiplier` — context class that accepts a strategy implementation and performs multiplication.
- `Main` — driver class with example usage and result printing.

---
## Vector-Matrix Multiplication Implementation Details

In the multi-threaded strategy, each thread computes one row of the resulting matrix by performing a **vector-matrix multiplication**.

The key method in `VectorMatrixMultiplier` looks like this:

```java
public void multiply() {
    for (int k = 0; k < vector.length; k++) {
        answer[k] = 0;
    }

    for (int i = 0; i < matrix.length; i++) {
        for (int k = 0; k < vector.length; k++) {
            answer[k] += vector[i] * matrix[i][k];
        }
    }
}
```
### Cache Optimization:
- The inner loop accesses column elements in a row `matrix[i][k]` sequentially, which aligns with Java's row-major storage.
- This sequential access pattern helps reduce cache misses because rows are stored contiguously in memory.

Overall, this approach takes advantage of CPU caching to improve performance, especially for large matrices.

---
## How to Run

### Compilation

From the root directory:

```bash
    javac -d classes Strategy/*.java
```

This compiles the `.java` files and puts the `.class` files under the `classes` directory preserving the package structure.

### Execution
Run the program using:
```bash
    java -cp classes Strategy.Main
```

## Output
- The program multiplies two example matrices using both the single-threaded and multi-threaded strategies, printing:
- The runtime in nanoseconds for each approach.
- The resulting product matrix.

```bash
Input matrix 5x5
Single Thread Run time 4,500 nanoseconds (Winner)
Multithread Run time 1,738,833 nanoseconds

Input matrix 200x200
Single Thread Run time 10,810,208 nanoseconds (Still Winner)
Multithread Run time 14,990,834 nanoseconds

Input matrix 1000x1000
Single Thread Run time 1,091,298,875 nanoseconds 0.014990834 seconds (1.091298875 seconds)
Multithread Run time 106,460,250 nanoseconds (0.106460250 seconds) (Winner)

```

### Interpretation
__Small matrix (5x5)__
- Single-threaded is way faster (~4.5 microseconds) than multi-threaded (~1.7 milliseconds).
- This is expected because thread creation and management overhead dominate computation time for small tasks.
- Multithreading is not worth it for very small matrices.

__Medium matrix (200x200)__
- Single-threaded (~11 ms) is still slightly faster than multi-threaded (~15 ms).
- Thread overhead is still noticeable compared to the computation work.
- Multithreading benefits are not yet visible for this size.

__Large matrix (1000x1000)__
- Multi-threaded (~0.1 s) is roughly 10x faster than single-threaded (~1.1 s).
- Now the computation workload is large enough that dividing work among threads outweighs overhead.
- Multi-threading pays off significantly for large matrices.


## Notes
- The multi-threaded approach creates one thread per row of the first matrix to parallelize computation.
- Thread synchronization is handled by waiting (join()) for all threads to finish before returning the result.
- The project demonstrates clean separation of algorithms and flexible switching via the Strategy pattern.
- Timing results may vary depending on matrix size and system hardware.

## Possible Improvements
- Use thread pools (ExecutorService) to manage threads more efficiently.
- Add input validation and exception handling.
- Extend to support other multiplication algorithms or optimizations.
- Add unit tests for correctness verification.

## Author
[suriyasg](https://github.com/suriyasg)