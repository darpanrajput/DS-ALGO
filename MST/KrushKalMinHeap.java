package com.graphs.implementations.MST;

import java.util.*;

public class KrushKalMinHeap {
    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 4);

        graph.Mst();
        System.out.println("SECOND GRAPH");
        Graph e = new Graph(9);
        e.addEdge(0, 1, 4);
        e.addEdge(0, 7, 8);
        e.addEdge(1, 2, 8);
        e.addEdge(1, 7, 11);
        e.addEdge(2, 3, 7);
        e.addEdge(2, 8, 2);
        e.addEdge(2, 5, 4);
        e.addEdge(3, 4, 9);
        e.addEdge(3, 5, 14);
        e.addEdge(4, 5, 10);
        e.addEdge(5, 6, 2);
        e.addEdge(6, 7, 1);
        e.addEdge(6, 8, 6);
        e.addEdge(7, 8, 7);
        e.Mst();
    }

    private static class Graph {
        int nv;
        Map<Integer, List<Node>> map = new HashMap<>();

        public Graph(int v) {
            this.nv = v;
        }

        private static class Vertex {
            int vertex, key;

            public Vertex() {
            }

            public Vertex(int vertex, int key) {
                this.vertex = vertex;
                this.key = key;
            }
        }

        private static class Node {
            int dest, w;

            public Node(int d, int cost) {
                this.dest = d;
                this.w = cost;
            }
        }

        private void addVertex(int dest) {
            map.put(dest, new LinkedList<>());
        }

        public void addEdge(int s, int d, int cost) {
            if (!map.containsKey(s))
                addVertex(s);
            if (!map.containsKey(d))
                addVertex(d);
            map.get(s).add(new Node(d, cost));
            map.get(d).add(new Node(s, cost));
        }

        private void Mst() {
            boolean[] Mst = new boolean[nv];
            int[] Parent = new int[nv];
            Vertex[] edge = new Vertex[nv];
            for (int i = 0; i < nv; i++) {
                edge[i] = new Vertex();
            }
            for (int i = 0; i < nv; i++) {
                edge[i].vertex = i;
                edge[i].key = Integer.MAX_VALUE;
                Parent[i] = -1;
                Mst[i] = false;
            }
            /*initialise our vertex to 0 to be selected first*/
            edge[0].key = 0;
            /*prepare min heap*/
            TreeSet<Vertex> queue = new TreeSet<>(new Comparator<Vertex>() {
                //return 1 if o1.key>o2.key
                //return -1 if o1.key<o2.key
                //return 0 otherwise
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    return o1.key - o2.key;
                }
            });

            for (int i = 0; i < nv; i++) {
                queue.add(edge[i]);
            }

           /* System.out.println("************** queue:start **********");
           for (Vertex v:queue){
              // System.out.println(v.vertex);
               System.out.println(v.key);
           }
            System.out.println("************** queue:end **********");
*/
            while (!queue.isEmpty()) {
                //select min node
                Vertex n = queue.pollFirst();
                //mark visited
                Mst[n.vertex] = true;
                //relax adjancent
                for (Node adjancent : map.get(n.vertex)) {
                    if (!Mst[adjancent.dest]
                            && edge[adjancent.dest].key > n.key + adjancent.w) {
                        queue.remove(edge[adjancent.dest]);
                        edge[adjancent.dest].key = n.key + adjancent.w;
                        queue.add(edge[adjancent.dest]);
                        Parent[adjancent.dest] = n.vertex;


                    }
                }
            }
            printMST(Parent, edge);
        }

        private void printMST(int[] parent, Vertex[] edge) {
            System.out.println("Edges \t \t Weights");
            for (int i = 0; i < nv; i++) {
                System.out.println(i + "-->" + parent[i] + "\t" +
                        edge[i].key);
            }
        }

    }
}
