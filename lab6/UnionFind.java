public class UnionFind {

  private int[] parent;

  /**
   * Creates a UnionFind data structure holding n vertices. Initially, all vertices are in disjoint
   * sets.
   *
   * @param n - the given number of vertices.
   */
  public UnionFind(int n) {
    parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = -1;
    }
  }

  /**
   * Returns true if nodes v1 and v2 are connected.
   *
   * @param v1 - the given vertex 1.
   * @param v2 - the given vertex 2.
   * @return true if nodes v1 and v2 are connected.
   */
  public boolean connected(int v1, int v2) {
    validate(v1);
    validate(v2);
    return find(v1) == find(v2);
  }

  /**
   * Returns the root of the set v1 belongs to. Path-compression is employed allowing for fast
   * search-time.
   *
   * @param v1 - the given vertex.
   * @return the root of the set v1 belongs to.
   */
  public int find(int v1) {
    validate(v1);
    int root = v1;

    while (parent(root) >= 0) {
      root = parent(root);
    }

    while (v1 != root) {
      int newV1 = parent(v1);
      parent[v1] = root;
      v1 = newV1;
    }
    return root;
  }

  /**
   * Returns the parent of v1. If v1 is the root of a tree, returns the negative size of the tree
   * for which v1 is the root.
   *
   * @param v1 - the given vertex.
   * @return the parent of v1.
   */
  private int parent(int v1) {
    validate(v1);
    return parent[v1];
  }

  /**
   * Throws an IllegalArgumentException if v1 is not a valid index.
   *
   * @param v1 - the vertex to be validated.
   * @throws IllegalArgumentException if the given vertex is not valid.
   */
  private void validate(int v1) throws IllegalArgumentException {
    if (v1 < 0 || v1 >= parent.length) {
      throw new IllegalArgumentException("the given vertex is invalid");
    }
  }

  /**
   * Connects two elements v1 and v2 together. v1 and v2 can be any valid elements, and a
   * union-by-size heuristic is used. If the sizes of the sets are equal, tie break by connecting
   * v1's root to v2's root. Unioning a vertex with itself or vertices that are already connected
   * should not change the sets but may alter the internal structure of the data.
   *
   * @param v1 - the given vertex 1.
   * @param v2 - the given vertex 2.
   */
  public void union(int v1, int v2) {
    validate(v1);
    validate(v2);

    int root1 = find(v1);
    int root2 = find(v2);
    if (root1 != root2) {
      int size1 = sizeOf(root1);
      int size2 = sizeOf(root2);
      if (size1 > size2) {
        parent[root1] -= size2;
        parent[root2] = root1;
      } else {
        parent[root2] -= size1;
        parent[root1] = root2;
      }
    }
  }

  /**
   * Returns the size of the set v1 belongs to.
   *
   * @param v1 - the given vertex.
   * @return the size of the set v1 belongs to.
   */
  private int sizeOf(int v1) {
    validate(v1);
    return -parent(find(v1));
  }
}
