package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2252 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] depth;

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        depth = new int[n + 1];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(line[1]);
            depth[line[1]]++;
        }

        topologicalSort();

        bw.flush();
    }

    private static void topologicalSort() throws IOException {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (depth[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            bw.write(poll + " ");

            for (Integer integer : graph[poll]) {
                depth[integer]--;

                if (depth[integer] == 0) queue.add(integer);
            }
        }
    }
}
