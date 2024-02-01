package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2961 {

    static final int MAX = 1_000_000_000;

    static int n;
    static int[][] cooks;
    static int res = MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        cooks = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cooks[i][0] = Integer.parseInt(st.nextToken());
            cooks[i][1] = Integer.parseInt(st.nextToken());
        }

        solve(0, 1, 0);

        System.out.println(res);
    }

    private static void solve(int idx, int soar, int bitter) {
        if (idx == n) {
            if (soar != 1 && bitter != 0) {
                res = Math.min(res, Math.abs(soar - bitter));
            }
        }
        else {
            solve(idx + 1, soar, bitter);
            solve(idx + 1, soar * cooks[idx][0], bitter + cooks[idx][1]);
        }
    }
}
