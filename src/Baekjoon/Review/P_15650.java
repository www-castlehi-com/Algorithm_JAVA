package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_15650 {

    static int n, m;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];

        solve(new int[m], 0);

        bw.flush();
    }

    public static void solve(int[] arr, int idx) throws IOException {
        if (idx == m)
            print(arr);
        else {
            for (int i = 1; i <= n; i++) {
                arr[idx] = i;
                if (check_validate(arr, idx))
                    solve(arr, idx + 1);
            }
        }
    }

    private static boolean check_validate(int[] arr, int idx) {
        for (int i = 0; i < idx; i++) {
            if (arr[i] >= arr[idx]) return false;
        }
        return true;
    }

    private static void print(int[] arr) throws IOException {
        for (int i : arr) {
            bw.write(i + " ");
        }
        bw.newLine();
    }
}
