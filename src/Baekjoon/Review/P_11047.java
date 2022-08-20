package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = line[0], k = line[1];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            cnt += k / a[i];
            k %= a[i];
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
