package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1974 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            int[][] sudoku = new int[9][9];

            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 9; j++) {
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + test + " " + checkSudoku(sudoku));
        }
    }

    private static int checkSudoku(int[][] sudoku) {
        if (!validateGroup(sudoku)) return 0;
        if (!validateLine(sudoku)) return 0;
        return 1;
    }

    private static boolean validateLine(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            int checkHorizontal = 0;
            int checkVertical = 0;
            for (int j = 0; j < 9; j++) {
                if ((checkHorizontal & (1 << sudoku[i][j])) != 0) return false;
                if ((checkVertical & (1 << sudoku[j][i])) != 0) return false;
                checkHorizontal |= (1 << sudoku[i][j]);
                checkVertical |= (1 << sudoku[j][i]);
            }
        }
        return true;
    }

    private static boolean validateGroup(int[][] sudoku) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int checkNumber = 0;
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        if ((checkNumber & (1 << sudoku[k][l])) != 0) return false;
                        checkNumber |= (1 << sudoku[k][l]);
                    }
                }
            }
        }
        return true;
    }
}
