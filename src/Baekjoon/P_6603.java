package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_6603 {

    static int              k;
    static int[]            perm;
    static int[]            lotto_num;
    static BufferedWriter   bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            k = info[0];
            if (k == 0) break;

            perm = Arrays.copyOfRange(info, 1, info.length);
            lotto_num = new int[6];

            find_lotto(0, 0);
            bw.newLine();
        }
        bw.flush();
    }

    public static void find_lotto(int pos, int idx) throws IOException {
        if (pos == 6) {
            for (int num : lotto_num) bw.write(num + " ");
            bw.newLine();
        }
        else {
            for (int i = idx; i < k; i++) {
                lotto_num[pos] = perm[i];
                find_lotto(pos + 1, i + 1);
            }
        }
    }
}
