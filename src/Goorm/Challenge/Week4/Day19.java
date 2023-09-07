package Goorm.Challenge.Week4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Day19 {

    static int n, m, s, e;
    static PriorityQueue<Integer>[] graph;
    static boolean[] visited;

    static public class Edge {
        int city;
        int cnt;

        public Edge(int city, int cnt) {
            this.city = city;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1]; s = line[2]; e = line[3];
        graph = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0]].add(line[1]);
            graph[line[1]].add(line[0]);
        }

        for (int i = 1; i <= n; i++) {
            bw.write(bfs(i) + "\n");
        }
        bw.flush();
    }

    private static int bfs(int construction) {
        if (construction == s || construction == e) return -1;

        LinkedList<Edge> queue = new LinkedList<>();
        queue.add(new Edge(s, 1));
        visited = new boolean[n + 1];

        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (poll.city == e) return poll.cnt;

            if (!visited[poll.city]) {
                visited[poll.city] = true;

                for (Integer next : graph[poll.city]) {
                    if (next != construction) queue.add(new Edge(next, poll.cnt + 1));
                }
            }
        }

        return -1;
    }
}
