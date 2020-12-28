package com.graphs.implementations.traversing;

import com.graphs.implementations.AdjancencyList;
import com.graphs.implementations.AdjancencyMatrix;

import java.util.*;

public class Traverse<T> {


    public static void main(String[] args) {
        Traverse traverse=new Traverse();
       /* AdjancencyMatrix.Matrix adjMatrix = new AdjancencyMatrix.Matrix(5);
        adjMatrix.addEdge(0, 1, true);
        adjMatrix.addEdge(0, 4, true);
        adjMatrix.addEdge(1, 2, true);
        adjMatrix.addEdge(1, 3, true);
        adjMatrix.addEdge(1, 4, true);
        adjMatrix.addEdge(2, 3, true);
        adjMatrix.addEdge(3, 4, true);
        BfsInAdjMatrix(adjMatrix, 3);
*/
        /*AdjancencyList.Graph<Integer> directedGraphList = new AdjancencyList.Graph<>();

        directedGraphList.addEdge(0, 1, false);
        directedGraphList.addEdge(0, 2, false);

        directedGraphList.addEdge(2, 0, false);
        directedGraphList.addEdge(2, 3, false);
        directedGraphList.addEdge(3, 3, false);
        Traverse traverse=new Traverse();
        traverse.DfsInAdjanceyList(directedGraphList,2);

        AdjancencyList.Graph<Integer> g = new AdjancencyList.Graph<>();
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);
        System.out.println("\n");

        traverse.DfsInAdjanceyList(g,2);*/
        AdjancencyMatrix.Matrix adjMatrix = new AdjancencyMatrix.Matrix(5);
        adjMatrix.addEdge(0, 1, true);
        adjMatrix.addEdge(0, 4, true);
        adjMatrix.addEdge(1, 2, true);
        adjMatrix.addEdge(1, 3, true);
        adjMatrix.addEdge(1, 4, true);
        adjMatrix.addEdge(2, 3, true);
        adjMatrix.addEdge(3, 4, true);
//        adjMatrix.showMatrix();

//        Traverse.DfsInAdjMatrix(adjMatrix,3);
        Traverse.DfsInAdjMatrix(getMtx(),0);


    }

    public void BfsInAdjList(AdjancencyList.Graph<T> graph, T startNode) {
        String s="Bfs traversal using adj list of the Graph";
        System.out.println(s);
        LinkedList<T> linkedList = new LinkedList<T>();
        Set<T> set = new HashSet<>();
        linkedList.add(startNode);
        set.add(startNode);

        while (!linkedList.isEmpty()) {
            T node = linkedList.poll();
            System.out.println(node + " ");

            for (T v : graph.map.get(node)) {
                if (!set.contains(v)) {
                    linkedList.add(v);
                    set.add(v);
                }

            }
        }
    }

    public static void BfsInAdjMatrix(AdjancencyMatrix.Matrix matrix, int startNode) {
        System.out.println("\n************************");
        System.out.print(" BFS Traversal Using the Adj MAtrix************");
        System.out.println("Your Given Adjanceny Matrix is : ");
        matrix.showMatrix();
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        q.add(startNode);
        set.add(startNode);
        int size = matrix.size;
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int r = node; r < size; r++) {
                for (int col = 0; col < size; col++) {
                    if (matrix.adjMatrix[r][col] == 1 && !set.contains(col)) {
                        set.add(col);
                        q.add(col);
                    }
                }

            }
        }


    }

    public void DfsInAdjanceyList(AdjancencyList.Graph<T> list, T startNode) {
        String s="************ DFs Traversal Using Adj List***********";
        System.out.println(s);
        Stack<T> stack = new Stack<>();
        Set<T> isVisited = new HashSet<>();
        stack.push(startNode);
        isVisited.add(startNode);

        while (!stack.isEmpty()) {
            T node = stack.pop();
            System.out.print(node + " ");
            for (T v : list.map.get(node)) {
                if (!isVisited.contains(v)) {
                    stack.push(v);
                    isVisited.add(v);

                }


            }
        }

    }

    public static void DfsInAdjMatrix(AdjancencyMatrix.Matrix matrix, int startNode) {
        String s="************ DFs Traversal Using Adj Matrix In Recursive Order***********";
        System.out.println(s);
        Set<Integer>set=new HashSet<>();

        DFSUtil(matrix.adjMatrix.length,startNode,set,matrix);

    }

    public static void DFSUtil(int size,int start, Set<Integer> set, AdjancencyMatrix.Matrix matrix){
        System.out.print(start+" ");
        set.add(start);
        for (int i = 0; i < size; i++) {
            if (matrix.adjMatrix[start][i]!=0 && !set.contains(i)) {
                DFSUtil(size,i,set,matrix);

            }
        }
    }

    public static AdjancencyMatrix.Matrix getMtx(){
    AdjancencyMatrix.Matrix matrix=new AdjancencyMatrix.Matrix(9);
    matrix.addEdge(0,1,false);
    matrix.addEdge(0,2,false);
    matrix.addEdge(1,3,false);
    matrix.addEdge(1,4,false);
    matrix.addEdge(2,5,false);
    matrix.addEdge(2,6,false);
    matrix.addEdge(4,7,false);
    matrix.addEdge(6,8,false);

    return matrix;
    }
}


