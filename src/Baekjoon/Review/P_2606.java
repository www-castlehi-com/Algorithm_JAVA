package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int vertex = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[vertex + 1];
        for (int i = 0; i <= vertex; i++) graph[i] = new ArrayList();
        for (int i = 0; i < edge; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int v1 = line[0], v2 = line[1];

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        bw.write(Integer.toString(bfs(graph)));
        bw.flush();
    }

    private static int bfs(ArrayList<Integer>[] graph) {
        boolean[] visited = new boolean[graph.length];
        LinkedList<Integer> queue = new LinkedList<>();
        int cnt = -1;
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            cnt++;

            for (Integer integer : graph[poll]) {
                if (!visited[integer]) {
                    queue.add(integer);
                    visited[integer] = true;
                }
            }
        }

        return cnt;
    }
}
