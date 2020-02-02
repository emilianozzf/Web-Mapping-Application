public class UnionFind {
    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return - find(v1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws IllegalArgumentException{
        if (vertex >= parent.length) {
            throw new IllegalArgumentException("the index is invalid");
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        if ((v1 == v2) || (i == j)) {
            return;
        }

        if (parent[v1] <= parent[v2]) {
            int weight1 = parent[v1];
            parent[v1] = v2;
            parent[v2] += weight1;
        } else {
            int weight2 = parent[v2];
            parent[v2] = v1;
            parent[v1] += weight2;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    private int find(int vertex) {
        int root = vertex;
        while(parent[root] >= 0) {
            root = parent[root];
        }
        return root;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
   negative size of the tree for which v1 is the root. */
    private int parent(int v1) {
        this.validate(v1);
        return parent[v1];
    }

}
