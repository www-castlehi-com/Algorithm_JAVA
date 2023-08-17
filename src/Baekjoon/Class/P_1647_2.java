package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class P_1647_2 {

    static int n, m;
    static ArrayList<Edge> list = new ArrayList<>();
    static int[] parent;

    static public class Edge {
        int start, end;
        int fee;

        public Edge(int start, int end, int fee) {
            this.start = start;
            this.end = end;
            this.fee = fee;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            list.add(new Edge(line[0], line[1], line[2]));
        }
        Collections.sort(list, Comparator.comparing(o -> o.fee));

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        bw.write(Integer.toString(kruskal()));
        bw.flush();

        bw.close();
        br.close();
    }

    private static int kruskal() {
        int total = 0;
        int maxFee = 0;

        for (Edge edge : list) {
            if (!isSameParent(edge.start, edge.end)) {
                total += edge.fee;
                maxFee = edge.fee;

                union(edge.start, edge.end);
            }
        }

        return total - maxFee;
    }

    private static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start != end) parent[end] = start;
    }

    private static boolean isSameParent(int start, int end) {
        if (find(start) == find(end)) return true;

        return false;
    }

    private static int find(int start) {
        if (parent[start] == start) return start;

        return parent[start] = find(parent[start]);
    }
}
