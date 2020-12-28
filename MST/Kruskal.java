package com.graphs.implementations.MST;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {

    public static void main(String[] args) {
        Graph e = new Graph(6, 10);
        e.addEdge(0, 1, 1);
        e.addEdge(1, 3, 1);
        e.addEdge(2, 4, 1);

        e.addEdge(0, 2, 2);
        e.addEdge(2, 3, 2);
        e.addEdge(3, 4, 2);

        e.addEdge(1, 2, 3);
        e.addEdge(1, 4, 3);
        e.addEdge(4, 5, 3);
        e.addEdge(3, 5, 4);
        e.MstKruskal();
    }

    private static class Graph {
        private static class Edge implements Comparable<Edge> {
            int src, dest, cost;

            @Override
            public int compareTo(Edge o) {
                return this.cost - o.cost;
            }
        }

        int nV, nE;
        ArrayList<Edge> edges;

        private static class Subset {
            int parent, rank;
        }

        public Graph(int nv, int ne) {
            this.nV = nv;
            this.nE = ne;
            edges = new ArrayList<>(nE);

        }

        private void addEdge(int s, int d, int w) {
            Edge e = new Edge();
            e.src = s;
            e.dest = d;
            e.cost = w;
            edges.add(e);
        }

        int find(Subset[] subsets, int node) {
            if (subsets[node].parent != node)
                subsets[node].parent =
                        find(subsets, subsets[node].parent);
            return subsets[node].parent;
        }

        void Union(Subset[] subsets, int x, int y) {
            int x_root = find(subsets, x);
            int y_root = find(subsets, y);
            if (subsets[x_root].rank < subsets[y_root].rank)
                subsets[x_root].parent = y_root;
            if (subsets[y_root].rank < subsets[x_root].rank)
                subsets[y_root].parent = x_root;
            else {
                subsets[y_root].parent = x_root;
                subsets[x_root].rank++;

            }
        }

        void MstKruskal() {
            Edge[] res = new Edge[nV];//no. of edge in mst =V-1
            for (int i = 0; i < nV; i++) {
                res[i] = new Edge();
            }
            Arrays.sort(new ArrayList[]{edges});
            Subset[] subsets = new Subset[nV];
            for (int i = 0; i < nV; i++) {
                subsets[i] = new Subset();
            }
            //intialisation
            for (int i = 0; i < nV; i++) {
                subsets[i].parent = i;
                subsets[i].rank = 0;
            }

            int Iindx = 0;
            int Eindx = 0;
            while (Eindx < nV - 1) {
                Edge next_edge = new Edge();
                next_edge = edges.get(Iindx++);
                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);
                if (x != y) {
                    res[Eindx++] = next_edge;
                    Union(subsets, x, y);
                }
            }
            printMST(res);
        }

        private void printMST(Edge[] res) {
            System.out.println("Edges\t\tweight");
            for (Edge re : res) {
                System.out.println(re.src + "-->" + re.dest + "==" +
                        re.cost);
            }
        }

    }
}
