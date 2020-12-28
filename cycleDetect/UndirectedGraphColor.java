package com.graphs.implementations.cycleDetect;

import com.graphs.implementations.AdjancencyList;

import java.util.HashSet;
import java.util.Set;

public class UndirectedGraphColor {
    public static void main(String[] args) {
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3,4,true);
        System.out.println(g.toString());
        System.out.println("Cycle Detection in adjlist using DFS in undirected graph BY GRAPH COLORING");
        graphCycle cycleInUndiected = new graphCycle();
        System.out.println(cycleInUndiected.isCycle(g));


        AdjancencyList.Graph<Integer> g2 = new AdjancencyList.Graph<>();
        g2.addEdge(0, 1, true);
        g2.addEdge(1, 2, true);
        g2.addEdge(2, 3, true);
        g2.addEdge(2, 5, true);
        g2.addEdge(3,4,true);
        g2.addEdge(3,6,true);
       // g2.addEdge(5,6,true);

        System.out.println(g2.toString());
        System.out.println("Cycle Detection in adjlist using DFS in undirected graph 2 BY GRAPH COLORING");

        System.out.println(cycleInUndiected.isCycle(g2));

    }

    private static class graphCycle {
        //using dfs recursive calls
        private boolean isCycle(AdjancencyList.Graph<Integer> graph) {
            int V = graph.map.size();
            int[] color = new int[V];
            //0 means unvisited
            //1 means visited
            for (int i = 0; i < V; i++) {
                if (color[i] == 0)
                    if (isCycleUtil(i, color, graph, -1)) {
                        return true;

                    }
            }
            return false;
        }

        private boolean isCycleUtil(int i, int[] color, AdjancencyList.Graph<Integer> graph, int parent) {
            color[i] = 1;
            for (int v : graph.map.get(i)) {
                if (color[v] != 1) {
                    if (isCycleUtil(v, color, graph, i)) {
                        return true;
                    }
                } else if (v != parent) return true;


            }
            return false;
        }

    }
}
