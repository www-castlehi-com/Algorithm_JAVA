package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_2580 {

    static int[][] board = new int[9][9];
    static ArrayList<SudokuCoordinate> zero = new ArrayList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class SudokuCoordinate {
        int x;
        int y;

        public SudokuCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 9; j++) if (board[i][j] == 0) zero.add(new SudokuCoordinate(j, i));
        }

        Sudoku(0);
    }

    public static void Sudoku(int sudokuN) throws IOException {
        if (sudokuN == zero.size()) {
            for (int[] ints : board) {
                for (int anInt : ints) {
                    bw.write(anInt + " ");
                }
                bw.newLine();
            }
            bw.flush();
            System.exit(0);
        }

        SudokuCoordinate sudokuCoordinate = zero.get(sudokuN);
        for (int i = 1; i <= 9; i++) {
            board[sudokuCoordinate.y][sudokuCoordinate.x] = i;
            if (!Failure(sudokuN, i)) Sudoku(sudokuN + 1);
            board[sudokuCoordinate.y][sudokuCoordinate.x] = 0;
        }
    }

    public static boolean Failure(int sudokuN, int num) {
        SudokuCoordinate sudokuCoordinate = zero.get(sudokuN);

        for (int i = 0; i < 9; i++) {
            if (i != sudokuCoordinate.x && board[sudokuCoordinate.y][i] == num) return true;
            if (i != sudokuCoordinate.y && board[i][sudokuCoordinate.x] == num) return true;
        }

        int y_idx = sudokuCoordinate.y / 3 * 3;
        int x_idx = sudokuCoordinate.x / 3 * 3;
        for (int i = y_idx; i < y_idx + 3; i++) {
            for (int j = x_idx; j < x_idx + 3; j++) {
                if(i != sudokuCoordinate.y && j != sudokuCoordinate.x && board[i][j] == num) return true;
            }
        }
        return false;
    }
}
