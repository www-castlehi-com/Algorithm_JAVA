package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;

public class P_9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] seq1 = br.readLine().split("");
        String[] seq2 = br.readLine().split("");

        int len1 = seq1.length;
        int len2 = seq2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (seq1[i - 1].charAt(0) == seq2[j - 1].charAt(0)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int i = len1, j = len2;
        ArrayList<String> lcs = new ArrayList<>();
        while (dp[i][j] != 0) {
            if (seq1[i - 1].charAt(0) == seq2[j - 1].charAt(0)) {
                lcs.add(seq1[i - 1]);
                i--; j--;
            }
            else {
                if (dp[i - 1][j] > dp[i][j - 1]) i--;
                else j--;
            }
        }

        bw.write(dp[len1][len2] + "\n");
        for (int k = lcs.size() - 1; k >= 0; k--) bw.write(lcs.get(k));
        bw.flush();
    }
}
