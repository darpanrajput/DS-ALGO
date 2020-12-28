package com.graphs.implementations;

public class AdjancencyMatrix {

    public static class Matrix {
        public final int[][] adjMatrix;
        public int size;

        public Matrix(int nodes) {
            this.adjMatrix = new int[nodes][nodes];
            this.size = nodes;
        }

        public void addEdge(int u,int v,boolean isBidirctional){
            adjMatrix[u][v]=1;
            if (isBidirctional)
                adjMatrix[v][u]=1;
        }

        public void showMatrix(){
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    System.out.print(adjMatrix[row][col] + "\t");
                }

                System.out.println("\n");
            }
        }

        private boolean hasEdge(int start, int end) {
            return adjMatrix[start][end] == 1;
        }
    }
}
