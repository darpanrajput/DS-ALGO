package com.graphs.implementations.cycleDetect;

public class disjointPathCompression {

    public static void main(String[] args) {
        int V = 3, E = 3;
        Graph graph = new Graph(V, E);
        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

      graph.edges[2].src = 0;
    graph.edges[2].dest = 2;

        System.out.println("CYCLE DETECTED IN UNIDIRECTED USING DISJOINT SETS USING PATH COMPRESSION");
        if (graph.isCycle(graph) == 1)
            System.out.println("Graph contians cycle");
        else System.out.println("Graph does not contain cylcle");
    }

    private static class Graph {
        int nE, nV;

        static class Edge {
            int src, dest;
        }

        Edge[] edges;

        public Graph(int nV, int nE) {
            this.nE = nE;
            this.nV = nV;
            edges = new Edge[nE];
            for (int i = 0; i < nE; i++)
                edges[i] = new Edge();

        }

        static class Subsets {
            int parent, rank;
        }

        private int Find(Subsets[] subsets, int node) {
            if (subsets[node].parent != node)
                //comparing the node itself
                subsets[node].parent = Find(subsets, subsets[node].parent);
            return subsets[node].parent;
        }

        private void Union(Subsets[] subsets, int v1, int v2) {
            int abs_root_s1 = Find(subsets, v1);
            int abs_root_s2 = Find(subsets, v2);
            if (subsets[abs_root_s1].rank < subsets[abs_root_s2].rank)
                subsets[abs_root_s1].parent = abs_root_s2;

            else if (subsets[abs_root_s2].rank < subsets[abs_root_s1].rank)
                subsets[abs_root_s2].parent = abs_root_s1;
                /*if both the ranks are equal increase rank*/
            else {
                subsets[abs_root_s1].parent = abs_root_s2;
                subsets[abs_root_s2].rank++;
            }
        }

        private int isCycle(Graph g){
            Subsets[]subsets=new Subsets[g.nV];
            for (int i = 0; i < nV; i++) {
                subsets[i]=new Subsets();
                subsets[i].parent=i;
                subsets[i].rank=0;
            }
            for (int i=0;i<nE;i++){
                int x=g.Find(subsets,g.edges[i].src);
                int y=g.Find(subsets,g.edges[i].dest);
                if(x==y){
                    System.out.println("Cycle is Between "+g.edges[i].src+
                            " and "+edges[i].dest);return 1;};
                g.Union(subsets,x,y);
            }

            return 0;
        }

    }

}
