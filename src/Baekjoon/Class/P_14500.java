package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_14500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] b_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] board = new int[b_info[0]][b_info[1]];
        for (int i = 0; i < b_info[0]; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = first_polyomino(board);
        answer = (answer > second_polyomino(board)) ? answer : second_polyomino(board);
        answer = (answer > third_polyomino(board)) ? answer : third_polyomino(board);
        answer = (answer > fourth_polyomino(board)) ? answer : fourth_polyomino(board);
        answer = (answer > fifth_polyomino(board)) ? answer : fifth_polyomino(board);

        System.out.println(answer);
    }

    public static int first_polyomino(int[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i].length - j >= 4) {
                    int res = board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i][j + 3];
                    max = (max > res) ? max : res;
                }

                if (board.length - i >= 4) {
                    int res = board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 3][j];
                    max = (max > res) ? max : res;
                }
            }
        }

        return max;
    }

    public static int second_polyomino(int[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i].length - j >= 2 && board.length - i >= 2) {
                    int res = board[i][j] + board[i][j + 1] + board[i + 1][j] + board[i + 1][j + 1];
                    max = (max > res) ? max : res;
                }
            }
        }

        return max;
    }

    public static int third_polyomino(int[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i].length - j >= 2 && board.length - i >= 3) {
                    int res = board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 2][j + 1];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i + 2][j + 1];
                    max = (max > res) ? max : res;

                    res = board[i][j + 1] + board[i + 1][j + 1] + board[i + 2][j + 1] + board[i + 2][j];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i][j + 1] + board[i + 1][j] + board[i + 2][j];
                    max = (max > res) ? max : res;
                }

                if (board[i].length - j >= 3 && board.length - i >= 2) {
                    int res = board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j];
                    max = (max > res) ? max : res;

                    res = board[i + 1][j] + board[i + 1][j + 1] + board[i + 1][j + 2] + board[i][j + 2];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i + 1][j] + board[i + 1][j + 1] + board[i + 1][j + 2];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j + 2];
                    max = (max > res) ? max : res;
                }
            }
        }

        return max;
    }

    public static int fourth_polyomino(int[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i].length - j >= 2 && board.length - i >= 3) {
                    int res = board[i][j] + board[i + 1][j] + board[i + 1][j + 1] + board[i + 2][j + 1];
                    max = (max > res) ? max : res;

                    res = board[i][j + 1] + board[i + 1][j] + board[i + 1][j + 1] + board[i + 2][j];
                    max = (max > res) ? max : res;
                }

                if (board[i].length - j >= 3 && board.length - i >= 2) {
                    int res = board[i + 1][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i][j + 2];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i + 1][j + 2];
                    max = (max > res) ? max : res;
                }
            }
        }

        return max;
    }

    public static int fifth_polyomino(int[][] board) {
        int max = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i].length - j >= 3 && board.length - i >= 2) {
                    int res = board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j + 1];
                    max = (max > res) ? max : res;

                    res = board[i + 1][j] + board[i + 1][j + 1] + board[i + 1][j + 2] + board[i][j + 1];
                    max = (max > res) ? max : res;
                }

                if (board[i].length - j >= 2 && board.length - i >= 3) {
                    int res = board[i + 1][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i + 2][j + 1];
                    max = (max > res) ? max : res;

                    res = board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 1][j + 1];
                    max = (max > res) ? max : res;
                }
            }
        }

        return max;
    }
}
