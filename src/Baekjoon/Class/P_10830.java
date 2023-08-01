package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_10830 {

    static int n;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] line = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        n = (int) line[0]; long b = line[1];
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) matrix[i][j] %= 1000;
        }

        int[][] result = solve(b);

        for (int[] ints : result) {
            for (int anInt : ints) {
                bw.write(anInt + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }

    private static int[][] solve(long exp) {
        if (exp == 1) return matrix;

        int[][] sub = solve(exp / 2);
        if (exp % 2 == 0) {
            return multiplication(sub, sub);
        }
        else {
            return multiplication(multiplication(sub, sub), matrix);
        }
    }

    private static int[][] multiplication(int[][] mat1, int[][] mat2) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum = (sum + (mat1[i][k] * mat2[k][j]) % 1000) % 1000;
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}

