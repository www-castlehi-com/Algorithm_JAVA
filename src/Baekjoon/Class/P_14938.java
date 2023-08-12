package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_14938 {

    static int n;
    static PriorityQueue<Node>[] graph;
    static int[] items;
    static int ans = 0;

    static class Node {
        int edge;
        int weigh;

        public Node(int edge, int weigh) {
            this.edge = edge;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; int m = line[1], r = line[2];
        items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new PriorityQueue[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));
        for (int i = 0; i < r; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0]].add(new Node(line[1], line[2]));
            graph[line[1]].add(new Node(line[0], line[2]));
        }

        for (int i = 1; i <= n; i++) {
            int[] distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[i] = 0;
            dijkstra(i, distance);
            calc(distance, m);
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void calc(int[] distance, int m) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] <= m) cnt += items[i - 1];
        }

        ans = Math.max(ans, cnt);
    }

    private static void dijkstra(int cur, int[] distance) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));
        queue.add(new Node(cur, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            for (Node node : graph[poll.edge]) {
                if (distance[node.edge] > poll.weigh + node.weigh) {
                    distance[node.edge] = poll.weigh + node.weigh;
                    queue.add(new Node(node.edge, poll.weigh + node.weigh));
                }
            }
        }
    }
}
