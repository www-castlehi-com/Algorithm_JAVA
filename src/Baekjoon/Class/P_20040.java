package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class P_20040 {

    static int n, m;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parents;

    static public class Edge {
        int v1, v2;

        public Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            edges.add(new Edge(line[0], line[1]));
        }

        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;
        bw.write(Integer.toString(kruskal()));
        bw.flush();
    }

    private static int kruskal() {
        for (int i = 0; i < m; i++) {
            Edge edge = edges.get(i);

            if (!isSameParent(edge.v1, edge.v2))
                union(edge.v1, edge.v2);
            else return i + 1;
        }

        return 0;
    }

    private static void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);

        if (r1 != r2) parents[r2] = r1;
    }

    private static int find(int v) {
        if (parents[v] == v) return v;
        else return parents[v] = find(parents[v]);
    }

    private static boolean isSameParent(int v1, int v2) {
        if (find(v1) == find(v2)) return true;

        return false;
    }
}
