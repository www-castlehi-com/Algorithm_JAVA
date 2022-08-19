package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_10972 {
    static int      n;
    static int[]    perm;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        perm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        find_next_perm();

        bw.flush();
    }

    public static void find_next_perm() throws IOException {
        int idx = -1;

        for (int i = n - 2; i>= 0; i--) {
            if (perm[i] < perm[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) bw.write("-1");
        else {
            for (int j = n - 1; j > idx; j--) {
                if (perm[j] > perm[idx]) {
                    int temp = perm[j];
                    perm[j] = perm[idx];
                    perm[idx] = temp;
                    break;
                }
            }
            Arrays.sort(perm, idx + 1, n);
            for (int num : perm) bw.write(num + " ");
        }

    }
}
