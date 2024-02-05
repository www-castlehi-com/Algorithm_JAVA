package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] a = new int[n];
            int[] b = new int[m];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            long res;
            if (n > m)
                res = solve(n, a, m, b);
            else
                res = solve(m, b, n, a);

            System.out.println("#" + test + " " + res);
        }
    }

    private static long solve(int biggerSize, int[] biggerArr, int smallerSize, int[] smallerArr) {
        long res = Long.MIN_VALUE;
        for (int i = 0; i <= biggerSize - smallerSize; i++) {
            long temp = 0;
            for (int j = 0; j < smallerSize; j++) {
                temp += biggerArr[i + j] * smallerArr[j];
            }
            res = Math.max(res, temp);
        }
        return res;
    }
}
