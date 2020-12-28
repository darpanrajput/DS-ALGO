package com.graphs.implementations.cycleDetect;

import com.graphs.implementations.AdjancencyList;

import java.util.*;

public class CycleInDirected {

    public static void main(String[] args) {
        System.out.println("Recusrsive DFS In Cycle Detecion in adj List");
     /*   AdjancencyList.Graph<Integer> g1 = new AdjancencyList.Graph<>();
        g1.addEdge(0, 1, false);
        g1.addEdge(1, 2, false);
        g1.addEdge(2, 3, false);
        g1.addEdge(3, 4, false);
        g1.addEdge(4, 1, false);
        System.out.println(g1.toString());
        System.out.println( isDfsCycle(g1));
*/

        AdjancencyList.Graph<Integer> g2 = new AdjancencyList.Graph<>();
        g2.addEdge(0, 1, false);
        g2.addEdge(1, 2, false);
        g2.addEdge(2, 3, false);
        g2.addEdge(3, 4, false);
        g2.addEdge(1, 4, false);
        System.out.println(g2.toString());
        System.out.println(isDfsCycle(g2));
        System.out.println("BFS Cycle DETECTION IN DIRECTED GRAPH USING ADJ LIST");
        System.out.println(isCycleBFS(g2));
    }


    public static boolean isDfsCycle(AdjancencyList.Graph<Integer> graph) {
        Set<Integer> Visited = new HashSet<>();
        Set<Integer> BackTack = new HashSet<>();
        for (int i = 0; i < graph.map.size(); i++) {
            if (!Visited.contains(i)) {
                if (isDfsCycleUtil(i, graph, Visited, BackTack))
                    return true;
            }
        }
        return false;
    }

    private static boolean isDfsCycleUtil(int startNode, AdjancencyList.Graph<Integer> graph, Set<Integer> visited, Set<Integer> backTack) {
        visited.add(startNode);
        backTack.add(startNode);
        for (int v : graph.map.get(startNode)) {
            if (backTack.contains(v)) return true;
            if (!visited.contains(v))
                if (isDfsCycleUtil(v, graph, visited, backTack))
                    return true;

        }
        backTack.remove(startNode);
        return false;
    }

    public static boolean isCycleBFS(AdjancencyList.Graph<Integer> graph) {

        Map<Integer, Integer> in_degree = new HashMap<>();
//        filling the hashmap with default in_degree
        for (Map.Entry<Integer, List<Integer>> v : graph.map.entrySet()) {
            in_degree.put(v.getKey(), 0);// default in_degree is zero
        }

        int visitedNodesNumber = 0;
        Queue<Integer> queue = new LinkedList<>();//will store nodes of zero in_degrees
        for (Map.Entry<Integer, List<Integer>> v : graph.map.entrySet()) {
            //        calculate the in_degrees of all nodes
            for (int degree : v.getValue())
                in_degree.put(degree, in_degree.get(degree) + 1);// default in_degree is zero
        }


        // fill the 0 degree nodes in queue
        for (Map.Entry<Integer, Integer> entry : in_degree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getValue());
            }
        }
        while (!queue.isEmpty()) {
            int V = queue.poll();
            visitedNodesNumber++;
            for (int v : graph.map.get(V)) {
                in_degree.put(v, in_degree.get(v) - 1);
                if (in_degree.get(v) == 0)
                    queue.add(v);
            }
        }

        return visitedNodesNumber != graph.map.size();

    }

}
