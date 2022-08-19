package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], k = arr[1];
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            cnt += k / coins[i];
            k -= (coins[i] * (k / coins[i]));
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
