package Baekjoon.Class;

import java.io.*;

public class P_11444 {

    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(br.readLine());

        long[][] A = {{1, 1}, {1, 0}};
        long[][] U0 = {{1, 0}, {0, 0}};
        long[][] result;

        result = matrixPow(n, A);
        result = matrixMul(result, U0);

        bw.write(Long.toString(result[1][0]));
        bw.flush();
    }

    private static long[][] matrixPow(long n, long[][] A) {
        if (n == 0 || n == 1) return A;

        long[][] result = matrixPow(n / 2, A);

        result = matrixMul(result, result);

        if (n % 2 != 0) result = matrixMul(result, new long[][] {{1, 1}, {1, 0}});

        return result;
    }

    private static long[][] matrixMul(long[][] A, long[][] B) {
        long[][] result = new long[2][2];

        result[0][0] = ((A[0][0] * B[0][0]) + (A[0][1] * B[1][0])) % MOD;
        result[0][1] = ((A[0][0] * B[0][1]) + (A[0][1] * B[1][1])) % MOD;
        result[1][0] = ((A[1][0] * B[0][0]) + (A[1][1] * B[1][0])) % MOD;
        result[1][1] = ((A[1][0] * B[0][1]) + (A[1][1] * B[1][1])) % MOD;

        return result;
    }
}
