package Baekjoon.Class;

import java.io.*;

public class P_12850 {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[][] matrix = {
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1, 0}
        };

        int d = Integer.parseInt(br.readLine());

        long[][] res = matrixPower(matrix, d);

        bw.write(Long.toString(res[0][0]));
        bw.flush();
    }

    private static long[][] matrixPower(long[][] matrix, int d) {
        if (d == 1) return matrix;
        if (d % 2 == 0) {
            long[][] halfPower = matrixPower(matrix, d / 2);
            return multiplyMatrices(halfPower, halfPower);
        } else return multiplyMatrices(matrix, matrixPower(matrix, d - 1));
    }

    private static long[][] multiplyMatrices(long[][] matrix1, long[][] matrix2) {
        long[][] res = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    res[i][j] = (res[i][j] + matrix1[i][k] * matrix2[k][j] % MOD) % MOD;
                }
            }
        }
        return res;
    }
}
