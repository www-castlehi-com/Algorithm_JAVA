package Baekjoon.Review;

import java.io.*;
import java.util.*;

public class P_1753 {

    static int v, e;
    static PriorityQueue<Node>[] graph;

    static class Node
    {
        int vertex;
        int weigh;

        public Node(int vertex, int weigh) {
            this.vertex = vertex;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        v = line[0]; e = line[1];

        int start = Integer.parseInt(br.readLine());

        graph = new PriorityQueue[v + 1];
        for (int i = 0; i <= v; i++) graph[i] = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));

        for (int i = 0; i < e; i++)
        {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Node(line[1], line[2]));
        }

        int[] weighs = dijkstra(start);
        for (int i = 1; i <= v; i++) {
            if (weighs[i] != Integer.MAX_VALUE) bw.write(weighs[i] + "\n");
            else bw.write("INF\n");
        }
        bw.flush();
    }

    private static int[] dijkstra(int start) {
        int[] weighs = new int[v + 1];
        Arrays.fill(weighs, Integer.MAX_VALUE);
        weighs[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.weigh));
        queue.add(new Node(start, 0));
        boolean[] visited = new boolean[v + 1];

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (!visited[poll.vertex]) {
                visited[poll.vertex] = true;

                for (Node node : graph[poll.vertex]) {
                    if (weighs[node.vertex] > weighs[poll.vertex] + node.weigh) {
                        weighs[node.vertex] = weighs[poll.vertex] + node.weigh;
                        queue.add(new Node(node.vertex, weighs[node.vertex]));
                    }
                }
            }
        }

        return weighs;
    }
}
