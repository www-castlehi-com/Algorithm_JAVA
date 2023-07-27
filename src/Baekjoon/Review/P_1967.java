package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1967 {

    static int n;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int maxDia = 0;
    static int maxPnt = 1;

    public static class Node {
        int end;
        int weigh;

        public Node(int end, int weigh) {
            this.end = end;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Node(line[1], line[2]));
            graph[line[1]].add(new Node(line[0], line[2]));
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        maxDia = 0;
        visited = new boolean[n + 1];
        dfs(maxPnt, 0);

        bw.write(Integer.toString(maxDia));
        bw.flush();
    }

    private static void dfs(int start, int diameter) {
        if (maxDia < diameter)
        {
            maxDia = diameter;
            maxPnt = start;
        }

        if (!visited[start]) {
            visited[start] = true;
            for (Node node : graph[start]) {
                if (!visited[node.end]) {
                    dfs(node.end, diameter + node.weigh);
                }
            }
        }
    }
}
