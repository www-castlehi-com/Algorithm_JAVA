package Baekjoon.Review;

import java.io.*;

public class P_9663 {

    static int n;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        dfs(new int[n], 0);

        bw.write(Long.toString(ans));
        bw.flush();
    }

    private static void dfs(int[] chess, int cur) {
        if (cur == n) {
            ans++;
            for (int i : chess) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        else {
            for (int i = 1; i <= n; i++) {
                chess[cur] = i;
                if (!checkPoint(chess, cur)) dfs(chess, cur + 1);
            }
        }
    }

    private static boolean checkPoint(int[] chess, int cur) {
        boolean checkPoint = false;

        for (int i = 0; i < cur; i++) {
            if (chess[i] == chess[cur] || Math.abs(cur - i) == Math.abs(chess[i] - chess[cur])) {
                checkPoint = true;
                break;
            }
        }

        return checkPoint;
    }
}
