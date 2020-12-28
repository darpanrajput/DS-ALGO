package com.graphs.implementations;

import com.graphs.implementations.traversing.Traverse;

public class Main {

    public static void main(String[] args) {
        // Object of graph is created.
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        AdjancencyList.Graph<Integer> directedGraphList = new AdjancencyList.Graph<>();

        directedGraphList.addEdge(0, 1, false);
        directedGraphList.addEdge(0, 2, false);

        directedGraphList.addEdge(2, 0, false);
        directedGraphList.addEdge(2, 3, false);
        directedGraphList.addEdge(3, 3, false);
        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        // print the graph.
        System.out.println("Undirected adjanceny list Graph 1:\n"
                + g.toString());


        Traverse<Integer>traverse=new Traverse<>();
        //traverse.BfsInAdjList(g,0);
        traverse.BfsInAdjList(directedGraphList,2);
        /*
        AdjancencyMatrix.Matrix adjMatrix=new AdjancencyMatrix.Matrix(5);
        adjMatrix.addEdge(0, 1, true);
        adjMatrix.addEdge(0, 4, true);
        adjMatrix.addEdge(1, 2, true);
        adjMatrix.addEdge(1, 3, true);
        adjMatrix.addEdge(1, 4, true);
        adjMatrix.addEdge(2, 3, true);
        adjMatrix.addEdge(3, 4, true);
        System.out.println("Undirected adjanceny Matrix Graph 1:\n");
        adjMatrix.showMatrix();
*/
    }
}
