package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grid;
  private int numOfOpenSites;
  private WeightedQuickUnionUF unions;

  /**
   * Creates N-by-N grid, with all sites initially blocked.
   *
   * Throws a java.lang.IllegalArgumentException if N ≤ 0.
   * The constructor should take time proportional to N^2.
   */
  public Percolation(int N) {
    if (N <= 0) {
      throw new IllegalArgumentException();
    }

    grid = new boolean[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        grid[i][j] = false;
      }
    }
    numOfOpenSites = 0;
    unions = new WeightedQuickUnionUF(N * N + 2);
  }

  /**
   * Opens the site (row, col) if it is not open already.
   *
   * Throws a java.lang.IndexOutOfBoundsException if any argument is outside its prescribed range.
   */
  public void open(int row, int col) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
      throw new IndexOutOfBoundsException();
    }

    if (!grid[row][col]) {
      grid[row][col] = true;
      numOfOpenSites += 1;
      if (row - 1 != -1 && isOpen(row-1, col)) {
        unions.union(xyTo1D(row, col), xyTo1D(row-1, col));
       }
      if (col - 1 != -1 && isOpen(row, col-1)) {
        unions.union(xyTo1D(row, col), xyTo1D(row, col-1));
      }
      if (row + 1 < grid.length && isOpen(row+1, col)) {
        unions.union(xyTo1D(row, col), xyTo1D(row+1, col));
      }
      if (col + 1 < grid.length && isOpen(row, col+1)) {
        unions.union(xyTo1D(row, col), xyTo1D(row, col+1));
      }
      if (row == 0) {
        unions.union(xyTo1D(row, col), grid.length * grid.length);
      }
      if (row == grid.length - 1) {
        unions.union(xyTo1D(row, col), grid.length * grid.length + 1);
      }
    }
  }


  /**
   * Determines whether the site (row, col) is open?
   *
   * Throws a java.lang.IndexOutOfBoundsException if any argument is outside its prescribed range.
   */
  public boolean isOpen(int row, int col) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
      throw new IndexOutOfBoundsException();
    }

    return grid[row][col];
  }

  /**
   * Determines whether the site (row, col) is full?
   *
   * Throws a java.lang.IndexOutOfBoundsException if any argument is outside its prescribed range.
   */
  public boolean isFull(int row, int col) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
      throw new IndexOutOfBoundsException();
    }

    return unions.connected(xyTo1D(row, col), grid.length * grid.length);
  }

  /**
   * Returns the number of open sites.
   */
  public int numberOfOpenSites() {
    return numOfOpenSites;
  }

  /**
   * Determines whether the system percolate.
   */
  public boolean percolates() {
    return unions.connected(grid.length * grid.length, grid.length * grid.length + 1);
  }

  private int xyTo1D (int row, int col) {
    return row * grid.length + col;
  }

  /**
   * Uses for unit testing (not required, but keep this here for the auto-grader.
   */
  public static void main (String[] args) {
  }
}