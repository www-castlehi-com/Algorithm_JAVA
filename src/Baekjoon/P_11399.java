package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(p);

        int time = p[0];
        for (int i = 1; i < n; i++) {
            p[i] += p[i - 1];
            time += p[i];
        }

        bw.write(Integer.toString(time));
        bw.flush();
    }
}
