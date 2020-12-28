package com.graphs.implementations.ShortestPath;

public class dijkstra {

    private static class shortestPath {
        int minDistance(int dist[], boolean[] visited) {
            int min_index = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < dist.length; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    min_index = i;
                }
            }

            return min_index;
        }

        void DijkstraSPG(int[][] graph, int src) {
            int nV = graph.length;
            /*declaration*/
            int dist[] = new int[nV];
            boolean[] isVisited = new boolean[nV];
            int[] parent = new int[nV];
            /*initialisation*/
            for (int i = 0; i < nV; i++) {
                dist[i] = Integer.MAX_VALUE;
                isVisited[i] = false;
                parent[i] = -1;
            }
            /*condition for src node as first node to be selected*/
            dist[src] = 0;
            for (int v = 0; v < nV - 1; v++) {
                /*step 1 find the minimum unvisited node*/
                int u = minDistance(dist, isVisited);
                /*step 2 mark that node as visited*/
                isVisited[u] = true;
                for (int vtx = 0; vtx < nV; vtx++) {
                    /*step 3 relax the adjancent vertex*/
                    if (!isVisited[vtx] && graph[u][vtx] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][vtx] < dist[vtx]) {

                        dist[vtx] = dist[u] + graph[u][vtx];
                        parent[vtx] = u;
                    }
                }
            }

            printSolution(parent, dist);
        }

        void printSolution(int[] parent, int[] dist) {
            System.out.println("Edage \t\t weights");
            for (int i = 0; i < parent.length; i++) {
                System.out.println(parent[i] + "-->" + i + "\t\t" + dist[i]);
            }
        }

    }

    public static void main(String[] args) {
        int[][] g = new int[][]
                {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                        {4, 0, 8, 0, 0, 0, 0, 11, 0},
                        {0, 8, 0, 7, 0, 4, 0, 0, 2},
                        {0, 0, 7, 0, 9, 14, 0, 0, 0},
                        {0, 0, 0, 9, 0, 10, 0, 0, 0},
                        {0, 0, 4, 14, 10, 0, 2, 0, 0},
                        {0, 0, 0, 0, 0, 2, 0, 1, 6},
                        {8, 11, 0, 0, 0, 0, 1, 0, 7},
                        {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        dijkstra.shortestPath d = new shortestPath();
        d.DijkstraSPG(g, 0);
        System.out.println("SECOND GRAPH ");
        int[][] g2 = {{0, 1, 4, 0, 0, 0},
                {1, 0, 4, 2, 7, 0},
                {4, 4, 0, 3, 5, 0},
                {0, 2, 3, 0, 4, 0, 7},
                {0, 7, 2, 4, 0, 7},
                {0, 0, 0, 6, 7, 0}};
        d.DijkstraSPG(g2,0);
        }
    }
