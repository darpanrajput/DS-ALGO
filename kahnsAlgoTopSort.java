package com.graphs.implementations;

import java.util.*;

public class kahnsAlgoTopSort {

    public static void main(String[] args) {
        AdjancencyList.Graph<Integer> DAG = new AdjancencyList.Graph<>();
        DAG.addEdge(0, 1, false);
        DAG.addEdge(1, 2, false);
        DAG.addEdge(2, 3, false);
        //DAG.addEdge(3, 1, false);//to create the cycle
        DAG.addEdge(3,4,false);
        DAG.addEdge(6,1,false);
        DAG.addEdge(6,5,false);
        DAG.addEdge(5,3,false);
        DAG.addEdge(7,8,false);// to check for disconnected graph
        System.out.println(DAG.toString());
        System.out.println("Topological sorting of the above DAG");
        kahnsAlgoTopSort toplogicalSort = new kahnsAlgoTopSort();
        toplogicalSort.sort(DAG);
    }

    private void sort(AdjancencyList.Graph<Integer> graph) {
        //step 1 find the in degree
        int V = graph.map.size();
        Queue<Integer> queue = new LinkedList<>();
        int[] degree = new int[V];
        for (int i = 0; i < V; i++) degree[i] = 0;

        for (int i = 0; i < V; i++)
            for (int v : graph.map.get(i))
                degree[v] = degree[v] + 1;


        //step 2 put all the vertex with 0 degree in queue
        for (int deg = 0; deg < degree.length; deg++)
            if (degree[deg] == 0) queue.add(deg);

        //step 3 Process the queue
        int count = 0;
        Vector<Integer> topOrder = new Vector<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topOrder.add(u);
            for (int v : graph.map.get(u)) {
                if (--degree[v] == 0) queue.add(v);
            }
            count++;

        }
        //check if there was a cycle
        if (count != V) {
            System.out.println("There Exist A Cycle");
            return;
        }


        for (int i : topOrder) {
            System.out.print(i+" ");
        }
    }

}
