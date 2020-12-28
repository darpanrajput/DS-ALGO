package com.graphs.implementations.n_queens;

import java.util.Scanner;

public class NQueenRecursion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] chess = new int[n][n];
        printNQueens(chess, "", 0);
    }

    private static void printNQueens(int[][] chess, String qsf, int row) {
        //jab row aapki chess.length ho jayegi tyo khatam karna hai
        if (row == chess.length) {
            System.out.println(qsf);
            return;

        }
        for (int col = 0; col < chess.length; col++) {
            //bahi queen safe hai ki nhi ye to check kar lo
            if (isItSafeMoveForQueen(chess, row, col)) {
                chess[row][col] = 1;//Queen ko dala is block mein
                printNQueens(chess, qsf + row + "-" + col + ",", row + 1);//row ko diya bada
                chess[row][col] = 0;//wapas aate huye 0 kar diya .queen hata diya
            }
        }
    }

    private static boolean isItSafeMoveForQueen(int[][] chess, int row, int col) {
        //vertically upward check
        for (int i = row - 1, j = col; i >= 0; i--) {
            if (chess[i][j] == 1) return false;
        }


        //ab left upward diagonal check
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 1) return false;
        }
        //ab right diagonal check karna hai
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 1) return false;
        }
        return true;
    }

}
