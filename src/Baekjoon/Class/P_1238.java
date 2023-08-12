package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1238 {

    static int n;

    static class Node {
        int edge;
        int time;

        public Node(int edge, int time) {
            this.edge = edge;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; int m = line[1], x = line[2];
        ArrayList<Node>[] graph = new ArrayList[n + 1];
        ArrayList<Node>[] reverseGraph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Node(line[1], line[2]));
            reverseGraph[line[1]].add(new Node(line[0], line[2]));
        }

        int[] time1 = dijkstra(x, graph);
        int[] time2 = dijkstra(x, reverseGraph);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, time1[i] + time2[i]);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }

    private static int[] dijkstra(int start, ArrayList<Node>[] targetGraph) {
        int[] times = new int[n + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            for (Node node : targetGraph[poll.edge]) {
                if (times[node.edge] > poll.time + node.time) {
                    times[node.edge] = poll.time + node.time;
                    queue.add(new Node(node.edge, poll.time + node.time));
                }
            }
        }

        return times;
    }
}
