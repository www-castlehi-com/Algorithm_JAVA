package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_10942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == i) dp[i][j] = true;
                else if (i - j == 1 && arr[i] == arr[j]) dp[i][j] = true;
                else if (i - j >= 2) {
                    boolean palindrome = true;
                    int start = j + 1, end = i - 1;
                    while (start < end) {
                        if (dp[end][start] == false) {
                            palindrome = false;
                            break;
                        }
                        start++;
                        end--;
                    }

                    if (palindrome && arr[i] == arr[j]) dp[i][j] = true;
                    else dp[i][j] = false;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (dp[line[1] - 1][line[0] - 1]) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        }
        bw.flush();
    }
}
