package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_15666 {

    static int n, m;
    static int[] arr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        dfs(new int[m], 0, 0);

        bw.flush();
    }

    private static void dfs(int[] ans, int idx, int cnt) throws IOException {
        int prevNum = -1;

        if (cnt == m) {
            for (int an : ans) bw.write(an + " ");
            bw.newLine();
        }
        else {
            for (int i = idx; i < n; i++) {
                if (arr[i] != prevNum) {
                    ans[cnt] = arr[i];
                    prevNum = arr[i];
                    dfs(ans, i, cnt + 1);
                }
            }
        }
    }
}
