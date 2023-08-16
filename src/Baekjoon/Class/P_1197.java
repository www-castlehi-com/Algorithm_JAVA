package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1197 {

    static int v, e;
    static ArrayList<Edge>[] graph;
    static int ans = 0;

    static public class Edge {
        int end;
        int weigh;

        public Edge(int end, int weigh) {
            this.end = end;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        v = line[0]; e = line[1];
        graph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Edge(line[1], line[2]));
            graph[line[1]].add(new Edge(line[0], line[2]));
        }

        prim(1);

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void prim(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));
        queue.add(new Edge(start, 0));
        boolean[] visited = new boolean[v + 1];

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (!visited[poll.end]) {
                visited[poll.end] = true;
                ans += poll.weigh;

                for (Edge edge : graph[poll.end]) {
                    if (!visited[edge.end]) {
                        queue.add(edge);
                    }
                }
            }
        }
    }
}

