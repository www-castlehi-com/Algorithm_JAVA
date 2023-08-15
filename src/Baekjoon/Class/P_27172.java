package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_27172 {

    static int max = 0;
    static int n;
    static int[] arr;
    static boolean[] nums = new boolean[1000001];
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            nums[arr[i]] = true;
        }

        score = new int[max + 1];
        sieve();

        for (int i : arr) {
            bw.write(score[i] + " ");
        }

        bw.flush();

        bw.close();
        br.close();
    }

    private static void sieve() {
        for (int i = 0; i < n; i++) {
            for (int j = 2 * arr[i]; j <= max; j += arr[i]) {
                if (nums[j]) {
                    score[j]--;
                    score[arr[i]]++;
                }
            }
        }
    }
}
