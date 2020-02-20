package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
  private double[] percolationStats;


  /**
   * Performs T independent experiments on an N-by-N grid.
   *
   * Throws a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0.
   */
  public PercolationStats(int N, int T, PercolationFactory pf) {
    percolationStats = new double[T];

    for (int i = 0; i < T; i++) {
      Percolation percolationTest = pf.make(N);
      int[] opens = StdRandom.permutation(N * N);
      int count = 0;
      while (count < opens.length && !percolationTest.percolates()) {
        percolationTest.open((opens[count] - 1) / N, (opens[count] - 1) % N);
        count += 1;
      }
      count -= 1;
      percolationStats[i] = (double) count / (N * N);
    }
  }

  /**
   * Returns sample mean of percolation threshold.
   */
  public double mean() {
    return StdStats.mean(percolationStats);
  }

  /**
   * Returns sample standard deviation of percolation threshold.
   */
  public double stddev() {
    return StdStats.stddev(percolationStats);
  }

  /**
   * Returns low endpoint of 95% confidence interval.
   */
  public double confidenceLow() {
    return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(percolationStats.length) ;
  }

  /**
   * Returns high endpoint of 95% confidence interval
   */
  public double confidenceHigh() {
    return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(percolationStats.length);
  }
}
