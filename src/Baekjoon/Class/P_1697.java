package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1697 {

    static final int size = 200001;

    static int[] info;
    static int[] map;
    static boolean[] visited;
    static int[] way;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[size];
        visited = new boolean[size];
        way = new int[size];

        bfs();

        bw.write(map[info[1]] + "\n");
        Deque<Integer> path = new ArrayDeque<>();
        int idx = info[1];
        while (true) {
            path.addFirst(idx);
            if (idx == info[0]) break;
            idx = way[idx];
        }
        for (Integer integer : path) {
            bw.write(integer + " ");
        }
        bw.flush();
    }

    public static void bfs() {
        int[] x_set = {-1, 1, 2};

        Queue<Integer> queue = new LinkedList<>();

        queue.add(info[0]);
        visited[info[0]] = true;
        way[info[0]] = info[0];

        while (!queue.isEmpty()) {
            int prev_loc = queue.remove();
            if (prev_loc == info[1]) return;

            for (int i = 0; i < 3; i++) {
                int cur_loc = prev_loc;
                if (i != 2) cur_loc += x_set[i];
                else cur_loc *= x_set[i];

                if (cur_loc < 0 || cur_loc >= map.length) continue;

                if (!visited[cur_loc]) {
                    queue.add(cur_loc);
                    visited[cur_loc] = true;
                    way[cur_loc] = prev_loc;
                    map[cur_loc] = map[prev_loc] + 1;
                }
            }
        }
    }
}
