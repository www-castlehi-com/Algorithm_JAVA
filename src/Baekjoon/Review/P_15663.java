package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_15663 {

    static int n, m;
    static int[] answer;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        answer = new int[m];
        visited = new boolean[n];
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        solve(arr, 0,0);

        bw.flush();
    }

    private static void solve(int[] arr, int cur, int cnt) throws IOException {
        int prevNum = -1;

        if (cnt == m) {
            for (int i : answer) bw.write(i + " ");
            bw.newLine();
        }
        else {
            for (int i = 0; i < n; i++) {
                if (!visited[i] && arr[i] != prevNum) {
                    visited[i] = true;
                    prevNum = arr[i];
                    answer[cnt] = arr[i];
                    solve(arr, i + 1, cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
