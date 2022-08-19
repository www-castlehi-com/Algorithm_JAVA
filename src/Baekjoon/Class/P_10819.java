package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P_10819 {
    static int      n;
    static int[]    perm;

    public static void main(String[] args) throws IOException {
        int max = Integer.MIN_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        perm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(perm);

        while (find_next_perm() != -1) {
            max = (max > find_result()) ? max : find_result();
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static int find_next_perm() throws IOException {
        int idx = -1;

        for (int i = n - 2; i>= 0; i--) {
            if (perm[i] < perm[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) return -1;
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
            return 1;
        }
    }

    public static int find_result() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (i + 1 < n) result += Math.abs(perm[i] - perm[i + 1]);
        }
        return result;
    }
}
