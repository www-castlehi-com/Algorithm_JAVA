package Baekjoon.Class;

import java.io.*;

import java.util.Arrays;

public class P_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) ints[i] = Integer.parseInt(br.readLine());
        Arrays.sort(ints);

        for (int anInt : ints) {
            bw.write(anInt + "\n");
        }
        bw.flush();
    }
}
