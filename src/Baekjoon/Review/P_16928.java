package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class P_16928 {

    static HashMap<Integer, Integer> items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        items = new HashMap<>();
        for (int i = 0; i < line[0] + line[1]; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            items.put(s[0], s[1]);
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int bfs() {
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        queue.add(new int[] {1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int loc = poll[0];
            int cnt = poll[1];

            if (loc == 100) return cnt;

            for (int i = 1; i <= 6; i++) {
                int next_loc = loc + i;
                int next_cnt = cnt + 1;

                if (next_loc <= 100 && !visited[next_loc]) {
                    queue.add(new int[] {items.getOrDefault(next_loc, next_loc), next_cnt});
                    visited[next_loc] = true;
                }
            }
        }
        return 0;
    }
}
