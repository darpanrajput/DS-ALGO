package com.graphs.implementations;


import com.graphs.implementations.cycleDetect.directedGraphColor;

import java.util.*;

public class toplogicalSort {

    public static void main(String[] args) {
        AdjancencyList.Graph<Integer> DAG = new AdjancencyList.Graph<>();
        DAG.addEdge(0, 1, false);
        DAG.addEdge(1, 2, false);
        DAG.addEdge(2, 3, false);
       // DAG.addEdge(3, 1, false);to create the cycle
        DAG.addEdge(3,4,false);
        DAG.addEdge(6,1,false);
        DAG.addEdge(6,5,false);
        DAG.addEdge(5,3,false);
       // DAG.addEdge(7,8,false); to check for disconnected graph
        System.out.println(DAG.toString());
        System.out.println("Topological sorting of the above DAG");
        toplogicalSort toplogicalSort = new toplogicalSort();
        System.out.println(Arrays.toString(toplogicalSort.sort(DAG)));

    }
    private int[] sort(AdjancencyList.Graph<Integer> graph) {
        GraphCycle graphCycle=new GraphCycle() ;

        if (graphCycle.isCycle(graph)) return new int []{};

        int V = graph.map.size();
        int[] topOrder = new int[V];
        Set<Integer> isVisited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!isVisited.contains(i)) {
                isVisited.add(i);
                topSorting(i, isVisited, graph, stack);
            }
        }
        int k = 0;
        while (!stack.isEmpty()) {
            topOrder[k++] = stack.pop();
        }
        return topOrder;


    }

    private void topSorting(int i, Set<Integer> isVisited, AdjancencyList.Graph<Integer> graph, Stack<Integer> stack) {
        for (int v :graph.map.get(i)) {
            if (!isVisited.contains(v)){
                isVisited.add(v);
                topSorting(v,isVisited,graph,stack);

            }
        }
        stack.push(i);
    }


    private static class GraphCycle {
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
