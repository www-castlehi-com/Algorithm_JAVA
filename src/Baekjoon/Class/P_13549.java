package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class P_13549 {

    static int size = 200001;
    static int[] info;
    static int[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[size];
        Arrays.fill(map, 100000);
        visited = new boolean[size];

        bfs();

        bw.write(Integer.toString(map[info[1]]));
        bw.flush();
    }

    public static void bfs() {
        LinkedList<Integer> queue = new LinkedList<>();

        queue.add(info[0]);
        visited[info[0]] = true;
        map[info[0]] = 0;

        while (!queue.isEmpty()) {
            int loc = queue.remove();
            visited[loc] = true;

            if (loc * 2 < size && !visited[loc * 2] && map[loc * 2] > map[loc]) {
                queue.addFirst(loc * 2);
                map[loc * 2] = map[loc];
            }

            if (loc + 1 <= 100000 && !visited[loc + 1] && map[loc + 1] > map[loc]) {
                queue.add(loc + 1);
                map[loc + 1] = map[loc] + 1;
            }

            if (loc - 1 >= 0 && !visited[loc - 1] && map[loc - 1] > map[loc]) {
                queue.add(loc - 1);
                map[loc - 1] = map[loc] + 1;
            }
        }
    }
}
