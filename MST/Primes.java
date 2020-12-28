package com.graphs.implementations.MST;

import com.graphs.implementations.AdjancencyMatrix;

class Primes {

    public static void main(String[] args) {
        Primes primes = new Primes();
        int[][] graph = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};
        primes.primeMST(graph);
    }

    private void primeMST(int [][] matrix) {
        /*our Requirements are the Dist,mst and parent*/
        int V = matrix.length;
        int[] parent = new int[V];
        int[] distance = new int[V];
        boolean[] MstVisited = new boolean[V];

        //initialise default values to distance and mst
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            MstVisited[i] = false;

        }
        //to select the first node
        distance[0] = 0;
        parent[0] = -1;

        //performing the iteration for the v-1
        for (int count = 0; count < V - 1; count++) {
            //step 1 find the min
            int min = MinDistance(distance, MstVisited);
            //now i get the min node vertex as min (index of the distance)
            // step 2 mark the min node as visited
            MstVisited[min] = true;
            //step3 relax the adjacent
            for (int col = 0; col < V; col++) {
                //follow ith breadth wise relax the adjacent nodes
                if (matrix[min][col] != 0 && !MstVisited[col]
                        && matrix[min][col] < distance[col]) {
                    distance[col] = matrix[min][col];
                    parent[col] = min;
                }
            }

        }

        printMST(parent, matrix,distance);


    }

    private void printMST(int[] parent, int[][] adjMatrix,int []distance) {
        System.out.println("Edge\tWeight");
     /*   for (int i = 0; i < adjMatrix.length; i++) {
            System.out.println( i+ "-->" + parent[i] + "\t" + adjMatrix[i][parent[i]]);
         }*/
        System.out.println("\n");
        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.println(i+ "-->" + parent[i] + "\t" + distance[i]);

        }
    }

    private int MinDistance(int[] distance, boolean[] mstVisited) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        System.out.println("dist len=" + distance.length);

        for (int i = 0; i < distance.length; i++) {
//            if node is visited and also the distance is less than the
            //infinity
            if (!mstVisited[i] && distance[i] < min) {
                min = distance[i];
                min_index = i;
            }

        }
        return min_index;
    }
}
