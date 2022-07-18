package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_16194 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cards = new int[n + 1];
        for(int i = 0; i < n; i++) cards[i + 1] = arr[i];
        int[] dp = new int[n + 1];

        dp[1] = cards[1];
        for (int i = 2; i <= n; i++) {
            dp[i] = cards[i];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + cards[j]);
            }
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
