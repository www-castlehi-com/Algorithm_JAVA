package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_10021 {

    static int n;
    static PriorityQueue<Pipe> pipes = new PriorityQueue<>(Comparator.comparing(o -> o.edge));
    static int[] parent;

    public static class Pipe {
        int v1, v2;
        int edge;

        public Pipe(int v1, int v2, int edge) {
            this.v1 = v1;
            this.v2 = v2;
            this.edge = edge;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = (int) (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));

                if (distance >= c) {
                    pipes.add(new Pipe(i, j, distance));
                }
            }
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        System.out.println(kruskal());
    }

    private static long kruskal() {
        long result = 0L;
        int count = 0;
        while (!pipes.isEmpty()) {
            Pipe pipe = pipes.poll();

            if (!isSameParent(pipe.v1, pipe.v2)) {
                union(pipe.v1, pipe.v2);
                result += pipe.edge;
                count++;
            }

            if (count == n - 1)
                break;
        }

        return count == n - 1 ? result : -1;
    }

    private static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);

        if (v1 != v2) parent[v2] = v1;
    }

    private static boolean isSameParent(int v1, int v2) {
        return find(v1) == find(v2);
    }

    private static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }
}
