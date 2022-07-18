package Baekjoon;

import java.io.*;

public class P_1463 {

    static int n;
    static int[] nbs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nbs = new int[n + 1];
        find_result();

        bw.write(Integer.toString(nbs[n]));
        bw.flush();
    }

    public static void find_result() {
        for (int i = 2; i <= n; i++) {
            nbs[i] = nbs[i - 1] + 1;
            if (i % 3 == 0)
                nbs[i] = Math.min(nbs[i], nbs[i / 3] + 1);
            if (i % 2 == 0)
                nbs[i] = Math.min(nbs[i], nbs[i / 2] + 1);
        }
    }
}
