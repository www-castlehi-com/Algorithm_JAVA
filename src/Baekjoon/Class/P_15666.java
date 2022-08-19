package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_15666 {

    static int n, m;
    static int[] number;
    static int[] sequence;
    static boolean[] visited;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr[0]; m = arr[1];
        number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(number);
        visited = new boolean[n];
        sequence = new int[m];

        MakeSequence(0, 0);

        bw.flush();
    }

    private static void MakeSequence(int cnt, int idx) throws IOException {
        int prevNum = -1;

        if (cnt == m) {
            for (int i : sequence) {
                bw.write(i + " ");
            }
            bw.newLine();
        }
        else {
            for (int i = idx; i < n; i++) {
                if (prevNum != number[i]) {
                    visited[i] = true;
                    sequence[cnt] = number[i];
                    prevNum = number[i];
                    MakeSequence(cnt + 1, i);
                    visited[i] = false;
                }
            }
        }
    }
}
