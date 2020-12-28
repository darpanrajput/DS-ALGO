package com.graphs.implementations.traversing;

import com.graphs.implementations.AdjancencyList;

import java.util.HashSet;
import java.util.Set;

public class recursiveDFS<T> {

    public static void main(String[] args) {
        recursiveDFS recursiveDFS=new recursiveDFS();
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        System.out.println(g.toString());
        recursiveDFS.recursiveDFSInAdjList(g,2);

    }
    private void recursiveDFSInAdjList(AdjancencyList.Graph<T> graph, T start) {
        System.out.println("Recursive DFS In adjancey List");
        Set<T> isVisited = new HashSet<>();
        dfsUtil(graph, isVisited, start);

    }

    private void dfsUtil(AdjancencyList.Graph<T> graph, Set<T> isVisited, T start) {
        isVisited.add(start);
        System.out.print(start + "-->");
        for (T s : graph.map.get(start)) {
            if (!isVisited.contains(s)) {
                dfsUtil(graph, isVisited, s);
            }
        }
    }

}
