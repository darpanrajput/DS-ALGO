package com.graphs.implementations.cycleDetect;

import com.graphs.implementations.AdjancencyList;

import java.util.*;

public class CycleInUndiected {

    public static void main(String[] args) {
        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, true);
        g.addEdge(1, 2, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
       // g.addEdge(1,4,true);
        System.out.println(g.toString());
        System.out.println("Cycle Detection in adjlist using DFS in undirected graph");
        CycleInUndiected cycleInUndiected = new CycleInUndiected();
        System.out.println(cycleInUndiected.isCyleDFS(g));
        System.out.println("CYLCE DETECTION IN ADJ LIST USING BFS IN UNDIRECTED GRAPH");
        System.out.println(cycleInUndiected.isCycleBfs(g));

    }

    public boolean isCyleDFS(AdjancencyList.Graph<Integer> g) {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < g.map.size(); i++) {
            if (!visited.contains(i)) {
                if (isDFSUtil(g, visited, -1, i))
                    return true;
            }
        }
        return false;
    }

    private boolean isDFSUtil(AdjancencyList.Graph<Integer> g, Set<Integer> visited, int parent, int start) {
        visited.add(start);
        for (int v : g.map.get(start)) {
            if (!visited.contains(v)) {
                if (isDFSUtil(g, visited, start, v))
                    return true;
            } else if (v != parent)
                return true;
        }
        return false;
    }

    private boolean isCycleBfs(AdjancencyList.Graph<Integer> g) {
        Set<Integer> isVisited = new HashSet<>();
        Map<Integer, Integer> parent = new Hashtable<>();
        Queue<Integer> queue = new LinkedList<>();
//        initialise the parent map with -1
        for (Map.Entry<Integer, List<Integer>> v : g.map.entrySet()) {
            parent.put(v.getKey(), -1);
        }
        //iterating each vertex
        for (int i = 0; i < g.map.size(); i++) {
            if (!isVisited.contains(i))
                if (isCycleBfsUtil(i, isVisited, queue, g,parent))
                    return true;
        }
        return false;

    }

    private boolean isCycleBfsUtil(int start, Set<Integer> isVisited, Queue<Integer> queue, AdjancencyList.Graph<Integer> g
    ,Map<Integer,Integer>parentMap) {
        isVisited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
         int S=queue.poll();
            for (int v : g.map.get(S)) {
                if (!isVisited.contains(v)) {
                    isVisited.add(v);
                    queue.add(v);
                    parentMap.put(v, S);
                } else if (!parentMap.get(S).equals(v))
                    return true;
            }
        }
        return false;
    }
}
