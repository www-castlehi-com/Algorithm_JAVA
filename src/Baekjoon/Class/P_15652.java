package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_15652 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] cond = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sequence = new int[cond[1]];
        find_sequence(sequence, cond[0], 0);
        bw.flush();
    }

    public static void find_sequence(int[] sequence, int m, int pos) throws IOException {
        int i = 1;

        while (i <= m) {
            sequence[pos % sequence.length] = i;
            if (is_overlap(sequence, pos % sequence.length)) {
                sequence[pos % sequence.length] = 0;
            }
            else {
                if (pos % sequence.length == sequence.length - 1) {
                    for (int n : sequence) bw.write(n + " ");
                    bw.write("\n");
                } else find_sequence(sequence, m, pos + 1);
            }
            i++;
        }
    }

    public static boolean is_overlap(int[] sequence, int pos) {
        for (int i = 0; i < pos ; i++) {
            if (sequence[i] > sequence[pos]) return true;
        }
        return false;
    }
}
