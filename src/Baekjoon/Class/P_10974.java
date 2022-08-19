package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_10974 {
    static int      n;
    static int[]    perm;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        init_perm();
        print_perm();
        while (find_next_perm()) {
            print_perm();
        }
        bw.flush();
    }

    public static void init_perm() {
        perm = new int[n];
        for (int i = 1; i<= n; i++) {
            perm[i - 1] = i;
        }
    }

    public static void print_perm() throws IOException {
        for (int num : perm) bw.write(num + " ");
        bw.newLine();
    }

    public static boolean find_next_perm() throws IOException {
        int idx = -1;

        for (int i = n - 2; i>= 0; i--) {
            if (perm[i] < perm[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) return false;
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
            return true;
        }
    }
}
