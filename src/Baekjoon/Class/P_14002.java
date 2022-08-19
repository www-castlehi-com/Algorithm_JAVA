package Baekjoon.Class;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P_14002 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> lis = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    if (count < dp[j]) {
                        count = dp[j];
                    }
                }
            }
            dp[i] = count + 1;
            max = (max > dp[i]) ? max : dp[i];
        }

        int idx = max;
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == idx) {
                lis.add(a[i]);
                idx--;
            }
        }
        Collections.sort(lis);

        bw.write(Integer.toString(max));
        bw.newLine();
        for (Integer li : lis) {
            bw.write(li + " ");
        }
        bw.flush();
    }
}
