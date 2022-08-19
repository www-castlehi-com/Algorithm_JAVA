package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_14391 {
    static int[]        wl;
    static int[][]      info;
    static int[][]      wid_or_len;
    static boolean[][]  visited;
    static int          max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        wl = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        info = new int[wl[0]][wl[1]];
        for (int i = 0; i < wl[0]; i++) info[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        wid_or_len = new int[wl[0]][wl[1]];
        visited = new boolean[wl[0]][wl[1]];

        piece_of_paper(0);
        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void piece_of_paper(int cnt) {
        if (cnt == wl[0] * wl[1]) {

            for (int i = 0 ; i < wl[0]; i++) Arrays.fill(visited[i], false);

            int result = find_max_score();
            max = (max > result) ? max : result;
        }
        else {
            wid_or_len[cnt / wl[1]][cnt % wl[1]] = 1;
            piece_of_paper(cnt + 1);

            wid_or_len[cnt / wl[1]][cnt % wl[1]] = 2;
            piece_of_paper(cnt + 1);
        }
    }

    public static int find_max_score() {
        int idx_i, idx_j;
        int num;
        int result = 0;

        for (int i = 0; i < wl[0]; i++) {
            for (int j = 0; j < wl[1]; j++) {

                idx_i = i;
                idx_j = j;
                num = 0;

                if (wid_or_len[idx_i][idx_j] == 1) {
                    while (wid_or_len[idx_i][idx_j] == 1 && visited[idx_i][idx_j] == false) {
                        num = num * 10 + info[idx_i][idx_j];
                        visited[idx_i][idx_j] = true;
                        idx_j++;
                        if (idx_j >= wl[1]) break;
                    }
                }
                else {
                    while (wid_or_len[idx_i][idx_j] == 2 && visited[idx_i][idx_j] == false) {
                        num = num * 10 + info[idx_i][idx_j];
                        visited[idx_i][idx_j] = true;
                        idx_i++;
                        if (idx_i >= wl[0]) break;
                    }
                }

                result += num;
            }
        }

        return result;
    }
}
