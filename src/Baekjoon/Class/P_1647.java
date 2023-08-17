package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_1647 {

    static int n, m;
    static ArrayList<Node>[] graph;

    static public class Node {
        int end;
        int fee;

        public Node(int end, int fee) {
            this.end = end;
            this.fee = fee;
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

            graph[line[0]].add(new Node(line[1], line[2]));
            graph[line[1]].add(new Node(line[0], line[2]));
        }

        bw.write(Integer.toString(prim(1)));
        bw.flush();

        bw.close();
        br.close();
    }

    private static int prim(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.fee));
        queue.add(new Node(start, 0));
        boolean[] visited = new boolean[n + 1];
        int total = 0;
        int maxFee = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (!visited[poll.end]) {
                visited[poll.end] = true;

                total += poll.fee;
                maxFee = Math.max(maxFee, poll.fee);

                for (Node node : graph[poll.end]) {
                    if (!visited[node.end]) {
                        queue.add(node);
                    }
                }
            }
        }

        return total - maxFee;
    }
}
