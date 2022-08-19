package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = s[0], m = s[1];
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) dp[i + 1] = dp[i] + numbers[i];

        while (m-- > 0) {
            int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.write((dp[range[1]] - dp[range[0] - 1]) + "\n");
        }
        bw.flush();
    }
}
