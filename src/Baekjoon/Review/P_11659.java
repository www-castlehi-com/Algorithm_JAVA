package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];

        int[] number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();;
        int[] dp = new int[n + 1];
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += number[i];
            dp[i + 1] = result;
        }

        while (m-- > 0) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int i = line[0], j = line[1];
            bw.write((dp[j] - dp[i - 1]) + "\n");
        }
        bw.flush();
    }
}
