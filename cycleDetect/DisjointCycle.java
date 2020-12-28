package com.graphs.implementations.cycleDetect;

public class DisjointCycle {
    /*This Disjoint method can only be used in Undirected Graph*/
    /*Preparing the the structure of the edges and grah
     * */

    public static void main(String[] args) {
        int V = 3, E = 2;
        Graph graph = new Graph(V, E);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

      /*  graph.edges[2].src = 0;
        graph.edges[2].dest = 2;*/

        System.out.println("CYCLE DETECTED IN UNIDIRECTED USING DISJOINT SETS");
        if (graph.isCycle(graph) == 1)
            System.out.println("Graph contians cycle");
        else System.out.println("Graph does not contain cylcle");
    }

    private static class Graph {
        public static class Edge {
            int src, dest;
        }

        int V, E;
        Edge[] edges;

        private Graph(int v, int e) {
            this.V = v;
            this.E = e;
            this.edges = new Edge[V];//size initalise
            for (int i = 0; i < e; i++) {
                edges[i] = new Edge();//each value intialize
            }
        }

        private int Find(int[] parents, int node) {
            if (parents[node] == -1) return node;
            return Find(parents, parents[node]);
        }

        private void Union(int[] parent, int node1, int node2) {
            int absRoot1 = Find(parent, node1);
            int absRoot2 = Find(parent, node2);
            parent[absRoot1] = absRoot2;

        }

        private int isCycle(Graph graph) {
            int[] parent = new int[graph.V];
          /*  filing the parent array with -1
          as initially all the sets will be treated as
          as the disjoint set
            */
            for (int v = 0; v < graph.V; v++) {
                parent[v] = -1;
            }
            /*now the real game begins
             * we need to calculate the absolute parent
             * of the both the vertices from the edge list
             * and if equal then return true*/
            for (int i = 0; i < graph.E; i++) {
                int x = graph.Find(parent, graph.edges[i].src);
                int y = graph.Find(parent, graph.edges[i].dest);
                if (x == y) return 1;

                graph.Union(parent, x, y);

            }

            return 0;
        }

    }
}

