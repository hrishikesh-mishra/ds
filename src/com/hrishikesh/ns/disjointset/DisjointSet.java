package com.hrishikesh.ns.disjointset;

/**
 * Disjoint Set
 *
 * @author hrishikesh.mishra
 * @link http://hrishikeshmishra.com/disjoint-set/
 */
public class DisjointSet {

    private int[] set;
    private int size;

    public void makeSet(int size) {
        set = new int[size];
        for (int i = size - 1; i >= 0; i--) set[i] = -1;
    }

    public int find(int x) {
        if (!(x >= 0 && x < size)) throw new RuntimeException("Invalid argument");
        if (set[x] == -1) return x;
        else return find(set[x]);
    }

    /**
     * @param root1
     * @param root2
     */
    public void union(int root1, int root2) {
        if ((find(root1) == find(root2)) && find(root1) != -1) return;
        if (set[root2] < set[root1]) {
            set[root1] = root2;
            set[root2] += set[root1];
        } else {
            set[root2] = root1;
            set[root1] += set[root2];
        }

    }

}
