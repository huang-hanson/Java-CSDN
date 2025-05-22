package com.demo.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NQueens
 * @Description N皇后问题
 *
 * n-皇后问题是在n行n列的棋盘上放置n个皇后，使得皇后彼此之间不受攻击，其规则是任意两个皇后不在同一行、同一列和相同的对角线上。
 * 拟采用以下思路解决n-皇后问题：第i个皇后放在第i行。
 * 从第一个皇后开始，对每个皇后，从其对应行（第i个皇后对应第i行）的第一列开始尝试放置，若可以放置，确定该位置，考虑下一个皇后；
 * 若与之前的皇后冲突，则考虑下一列；若超出最后一列，则重新确定上一个皇后的位置。重复该过程，直到找到所有的放置方案。
 *
 * @date 2025/5/22 10:19
 **/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        // 初始化棋盘，用二维数组表示，'.'表示空，'Q'表示皇后
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(solutions, board, 0);
        return solutions;
    }

    private void backtrack(List<List<String>> solutions, char[][] board, int row) {
        // 如果已经放置完所有行，说明找到一个解
        if (row == board.length) {
            solutions.add(constructSolution(board));
            // 注意这里的return只是当前递归层完成了
            return;
        }

        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';  // 放置皇后
                backtrack(solutions, board, row + 1);  // 递归处理下一行
                board[row][col] = '.';  // 撤销选择，回溯
            }
        }
    }

    // 检查在(row, col)放置皇后是否有效
    private boolean isValid(char[][] board, int row, int col) {
        // 检查同一列是否有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查左上对角线是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查右上对角线是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // 将棋盘转换为字符串列表
    private List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    public static void main(String[] args) {
        NQueens solver = new NQueens();
        int n = 4;  // 可以修改n的值
        List<List<String>> solutions = solver.solveNQueens(n);
        System.out.println(n + "皇后问题的解有 " + solutions.size() + " 种:");
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}