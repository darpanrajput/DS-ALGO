package com.graphs.implementations.cycleDetect;

import com.graphs.implementations.AdjancencyList;

public class directedGraphColor {
    public static void main(String[] args) {
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, false);
        g.addEdge(1, 2, false);
        g.addEdge(1, 4, false);
        g.addEdge(2, 3, false);
        g.addEdge(3,4,false);
        System.out.println(g.toString());
        System.out.println("Cycle Detection in adjlist using DFS in directed graph 1 BY GRAPH COLORING");
        graphCycle cycleInUndiected = new graphCycle();
        System.out.println(cycleInUndiected.isCycle(g));


        AdjancencyList.Graph<Integer> g2 = new AdjancencyList.Graph<>();
        g2.addEdge(0, 1, false);
        g2.addEdge(1, 2, false);
        g2.addEdge(2, 3, false);
        g2.addEdge(5, 2, false);
        g2.addEdge(3,4,false);
        g2.addEdge(3,6,false);
        g2.addEdge(6,5,false);

        System.out.println(g2.toString());
        System.out.println("Cycle Detection in adjlist using DFS in directed graph 2 BY GRAPH COLORING");

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
                    if (isCycleUtil(i, color, graph)) {
                        return true;

                    }
            }
            return false;
        }

        private boolean isCycleUtil(int i, int[] color, AdjancencyList.Graph<Integer> graph) {
            color[i] = 1;
            for (int v : graph.map.get(i)) {
                if (color[v]==1)return true;
                if (color[v] == 0) {
                    if (isCycleUtil(v, color, graph)) {
                        return true;
                    }
                }

            }
            color[i]=2;
            return false;
        }

    }
}
