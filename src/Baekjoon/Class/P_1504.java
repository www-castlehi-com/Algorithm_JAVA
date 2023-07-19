package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_1504 {

    static int n, m;
    static ArrayList<Edge>[] graph;
    static int v1, v2;
    static final int INF = 200000000;

    static class Edge {
        int vertex;
        int weigh;

        public Edge(int vertex, int weigh) {
            this.vertex = vertex;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Edge(line[1], line[2]));
            graph[line[1]].add(new Edge(line[0], line[2]));
        }
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        v1 = line[0]; v2 = line[1];

        long case1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);
        long case2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

        long ans = Math.min(case1, case2);

        if (case1 >= INF && case2 >= INF) bw.write(Integer.toString(-1));
        else bw.write(Long.toString(ans));
        bw.flush();
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));
        queue.add(new Edge(start, 0));
        boolean[] visited = new boolean[n + 1];
        int[] weighs = new int[n + 1];
        Arrays.fill(weighs, INF);
        weighs[start] = 0;

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (poll.vertex == end) return poll.weigh;

            if (!visited[poll.vertex]) {
                visited[poll.vertex] = true;

                for (Edge e : graph[poll.vertex]) {
                    if (weighs[e.vertex] > weighs[poll.vertex] + e.weigh) {
                        weighs[e.vertex] = weighs[poll.vertex] + e.weigh;
                        queue.add(new Edge(e.vertex, weighs[e.vertex]));
                    }
                }
            }
        }
        return weighs[end];
    }
}
