package com.graphs.implementations.traversing;

import com.graphs.implementations.AdjancencyList;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class resursiveBFS<T> {
    public static void main(String[] args) {
        resursiveBFS<Integer> BFS = new resursiveBFS<>();
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        System.out.println(g.toString());
        BFS.recursiveBfs(3, g);


    }

    public void recursiveBfs(T node, AdjancencyList.Graph<T> g) {
        System.out.println("******* bfs using recursion in adj list *****");
        Set<T> isVisted = new HashSet<>();
        ArrayDeque<T> q = new ArrayDeque<>();
        recursiveBfsUtil(node, g, isVisted, q);
    }

    private void recursiveBfsUtil(T node, AdjancencyList.Graph<T> g, Set<T> isVisted, ArrayDeque<T> q) {
        isVisted.add(node);
        System.out.print(node + "-->");
        for (T v : g.map.get(node)) {
            if (!isVisted.contains(v)) {
                isVisted.add(v);
                q.add(v);
            }

        }
        while (!q.isEmpty()) {
            recursiveBfsUtil(q.poll(), g, isVisted, q);
        }
    }

}
