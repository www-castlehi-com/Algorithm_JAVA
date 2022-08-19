package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_10869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write((s[0] + s[1]) + "\n");
        bw.write((s[0] - s[1]) + "\n");
        bw.write((s[0] * s[1]) + "\n");
        bw.write((s[0] / s[1]) + "\n");
        bw.write((s[0] % s[1]) + "\n");
        bw.flush();
    }
}
