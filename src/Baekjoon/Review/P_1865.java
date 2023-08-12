package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1865 {

    static int n;
    static ArrayList<Node>[] graph;
    static int[] dist;

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

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = line[0]; int  m = line[1], w = line[2];
            graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < m + w; i++) {
                line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                if (i < m) {
                    graph[line[0]].add(new Node(line[1], line[2]));
                    graph[line[1]].add(new Node(line[0], line[2]));
                }
                else
                    graph[line[0]].add(new Node(line[1], -line[2]));
            }

            dist = new int[n + 1];
            if (bellmanFord()) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private static boolean bellmanFord() {
        Arrays.fill(dist, 5000000);
        dist[1] = 0;

        for (int i = 0; i < n; i++) {
            boolean update = false;
            for (int j = 1; j <= n; j++) {
                for (Node node : graph[j]) {
                    if (dist[node.edge] > dist[j] + node.weigh) {
                        dist[node.edge] = dist[j] + node.weigh;
                        update = true;
                        if (i == n - 1) return true;
                    }
                }
            }

            if (!update) break;
        }

        return false;
    }
}
