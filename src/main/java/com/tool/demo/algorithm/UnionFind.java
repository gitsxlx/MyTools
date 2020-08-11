package com.tool.demo.algorithm;

/**
 * UnionFind
 *
 * @author WangChao
 * @date 2020/08/07
 **/
public class UnionFind {

    private int count = 0;
    private int[] parents;

    public UnionFind(int totalNodes) {
        count = totalNodes;
        parents = new int[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            parents[i] = i;
        }
    }

    // 合并连通区域是通过find来操作的, 即看这两个节点是不是在一个连通区域内.
    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 == root2) {
            return;
        }
        parents[root1] = node2;
        count--;
    }

    public int find(int node) {
        while (parents[node] != node) {
            // 当前节点的父节点 指向父节点的父节点.
            // 保证一个连通区域最终的parents只有一个.
            parents[node] = parents[parents[node]];
            node = parents[node];
        }

        return node;
    }

}
