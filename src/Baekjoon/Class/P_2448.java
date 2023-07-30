package Baekjoon.Class;

import java.io.*;

public class P_2448 {

    static int k;
    static String[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        k = n / 3;

        result = new String[n];
        result[0] = "  *  ";
        result[1] = " * * ";
        result[2] = "*****";
        for (int k = 1; 3 * Math.pow(2, k) <= n; k++)
            star(k);

        for (int i = 0; i < n; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }

    private static void star(int turn) {
        int btm = (int) (3 * Math.pow(2, turn));
        int mid = btm / 2;
        for (int i = mid; i < btm; i++) {
            result[i] = result[i - mid] + " " + result[i - mid];
        }
        String padding = "";
        while (padding.length() < mid) padding += " ";
        for (int i = 0; i < mid; i++) {
            result[i] = padding + result[i] + padding;
        }
    }
}
