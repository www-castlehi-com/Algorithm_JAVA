package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1509 {

    static String[] str;
    static int len;
    static boolean[][] isPalindrome;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine().split("");
        len = str.length;
        isPalindrome = new boolean[len + 1][len + 1];

        for (int i = 0; i < len; i++) {
            for(int j = 1; j + i <= len; j++) {
                if (i == 0) isPalindrome[j][j] = true;
                else if (checkPalindrome(j, j + i)) isPalindrome[j][j + i] = true;
                else isPalindrome[j][j + i] = false;
            }
        }

        dp = new int[len + 1];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= i; j++) {
                if (isPalindrome[j][i]) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
            }
        }

        bw.write(Integer.toString(dp[len]));
        bw.flush();
    }

    private static boolean checkPalindrome(int start, int end) {
        if (str[start - 1].equals(str[end - 1])) {
            if (end - start == 1 || isPalindrome[start + 1][end - 1]) return true;
        }
        return false;
    }
}
