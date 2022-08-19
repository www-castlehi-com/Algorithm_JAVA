package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11722 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    count = (count < dp[j]) ? dp[j] : count;
                }
            }
            dp[i] = count + 1;
            max = (max > dp[i]) ? max : dp[i];
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}