package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P_1766 {

    static int n, m;
    static int[] depth;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        depth = new int[n];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            depth[line[1] - 1]++;
            graph[line[0] - 1].add(line[1] - 1);
        }

        for (Integer integer : topologicalSort()) bw.write(integer + " ");
        bw.flush();
    }

    private static ArrayList<Integer> topologicalSort() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (depth[i] == 0) queue.add(i);
        }
        ArrayList<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            ans.add(poll + 1);

            for (Integer integer : graph[poll]) {
                depth[integer]--;

                if (depth[integer] == 0) queue.add(integer);
            }
        }

        return ans;
    }
}
