package Baekjoon;

import java.io.*;
import java.util.ArrayList;

public class P_4574 {

    static int[][] board;
    static int[][] resultBoard;
    static ArrayList<SudoinokuCoordinate> zero;
    static boolean[][] dominoes;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class SudoinokuCoordinate {
        int x;
        int y;

        public SudoinokuCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            bw.write("Puzzle ");
            bw.write(cnt + "\n");
            cnt++;

            board = new int[9][9];
            resultBoard = new int[9][9];
            zero = new ArrayList<>();
            dominoes = new boolean[10][10];

            for (int i = 0; i < n; i++) {
                String[] arr = br.readLine().split(" ");
                int u = Integer.parseInt(arr[0]), v = Integer.parseInt(arr[2]);
                board[arr[1].charAt(0) - 'A'][Integer.parseInt(String.valueOf(arr[1].charAt(1))) - 1] = u;
                board[arr[3].charAt(0) - 'A'][Integer.parseInt(String.valueOf(arr[3].charAt(1))) - 1] = v;
                dominoes[u][v] = dominoes[v][u] = true;
            }
            String[] arr = br.readLine().split(" ");
            for (int i = 0; i < 9; i++) {
                board[arr[i].charAt(0) - 'A'][Integer.parseInt(String.valueOf(arr[i].charAt(1))) - 1] = i + 1;
            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) if (board[i][j] == 0) zero.add(new SudoinokuCoordinate(j, i));
            }
            Sudoku(0);

            for (int[] ints : resultBoard) {
                for (int anInt : ints) {
                    bw.write(Integer.toString(anInt));
                }
                bw.newLine();
            }
        }
        bw.flush();
    }

    public static void Sudoku(int sudokuN) {
        if (sudokuN == zero.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) resultBoard[i][j] = board[i][j];
            }
            return ;
        }

        SudoinokuCoordinate SudoinokuCoordinate = zero.get(sudokuN);
        if (board[SudoinokuCoordinate.y][SudoinokuCoordinate.x] == 0) {
            for (int i = 1; i <= 9; i++) {
                board[SudoinokuCoordinate.y][SudoinokuCoordinate.x] = i;
                for (int j = 1; j <= 9; j++) {
                    if (dominoes[i][j] == false && dominoes[j][i] == false) {
                        if (SudoinokuCoordinate.y + 1 < 9 && board[SudoinokuCoordinate.y + 1][SudoinokuCoordinate.x] == 0) {
                            board[SudoinokuCoordinate.y + 1][SudoinokuCoordinate.x] = j;
                            dominoes[i][j] = dominoes[j][i] = true;
                            if (!Failure(sudokuN, i, SudoinokuCoordinate.y + 1, SudoinokuCoordinate.x, j))
                                Sudoku(sudokuN + 1);
                            board[SudoinokuCoordinate.y + 1][SudoinokuCoordinate.x] = 0;
                            dominoes[i][j] = dominoes[j][i] = false;
                        }

                        if (SudoinokuCoordinate.x + 1 < 9 && board[SudoinokuCoordinate.y][SudoinokuCoordinate.x + 1] == 0) {
                            board[SudoinokuCoordinate.y][SudoinokuCoordinate.x + 1] = j;
                            dominoes[i][j] = dominoes[j][i] = true;
                            if (!Failure(sudokuN, i, SudoinokuCoordinate.y, SudoinokuCoordinate.x + 1, j))
                                Sudoku(sudokuN + 1);
                            board[SudoinokuCoordinate.y][SudoinokuCoordinate.x + 1] = 0;
                            dominoes[i][j] = dominoes[j][i] = false;
                        }
                    }
                }
                board[SudoinokuCoordinate.y][SudoinokuCoordinate.x] = 0;
            }
        }
        else Sudoku(sudokuN + 1);
    }

    public static boolean Failure(int sudokuN, int numI, int y, int x, int numJ) {
        SudoinokuCoordinate SudoinokuCoordinate = zero.get(sudokuN);

        for (int i = 0; i < 9; i++) {
            if (i != SudoinokuCoordinate.x && board[SudoinokuCoordinate.y][i] == numI) return true;
            if (i != SudoinokuCoordinate.y && board[i][SudoinokuCoordinate.x] == numI) return true;

            if (i != x && board[y][i] == numJ) return true;
            if (i != y && board[i][x] == numJ) return true;
        }

        int numIYIdx = SudoinokuCoordinate.y / 3 * 3;
        int numIXIdx = SudoinokuCoordinate.x / 3 * 3;
        for (int i = numIYIdx; i < numIYIdx + 3; i++) {
            for (int j = numIXIdx; j < numIXIdx + 3; j++) {
                if(i != SudoinokuCoordinate.y && j != SudoinokuCoordinate.x && board[i][j] == numI) return true;
            }
        }

        int numJYIdx = y / 3 * 3;
        int numJXIdx = x / 3 * 3;
        for (int i = numJYIdx; i < numJYIdx + 3; i++) {
            for (int j = numJXIdx; j < numJXIdx + 3; j++) {
                if (i != y && j != x && board[i][j] == numJ) return true;
            }
        }

        return false;
    }
}
