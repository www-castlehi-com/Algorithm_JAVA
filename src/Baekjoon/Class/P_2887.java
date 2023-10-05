package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_2887 {

    static int n;
    static Planet[] planets;

    static public class Planet {
        int idx;
        int x, y, z;

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static PriorityQueue<Edge> queue = new PriorityQueue<>();

    static public class Edge implements Comparable {
        int v1;
        int v2;
        int weigh;

        public Edge(int v1, int v2, int weigh) {
            this.v1 = v1;
            this.v2 = v2;
            this.weigh = weigh;
        }

        @Override
        public int compareTo(Object o) {
            Edge edge = (Edge) o;

            return this.weigh - edge.weigh;
        }
    }

    static int[] parent;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        planets = new Planet[n];
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            planets[i] = new Planet(i, line[0], line[1], line[2]);
        }

        Arrays.sort(planets, Comparator.comparing(o -> o.x));
        for (int i = 0; i < n - 1; i++) queue.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].x - planets[i + 1].x)));
        Arrays.sort(planets, Comparator.comparing(o -> o.y));
        for (int i = 0; i < n - 1; i++) queue.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].y - planets[i + 1].y)));
        Arrays.sort(planets, Comparator.comparing(o -> o.z));
        for (int i = 0; i < n - 1; i++) queue.add(new Edge(planets[i].idx, planets[i + 1].idx, Math.abs(planets[i].z - planets[i + 1].z)));

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        kruskal();
        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void kruskal() {
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();

            if (!isSameParent(poll.v1, poll.v2)) {
                union(poll.v1, poll.v2);
                ans += poll.weigh;
            }
        }
    }

    private static void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 != p2) parent[p1] = p2;
    }

    private static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    private static boolean isSameParent(int v1, int v2) {
        if (find(v1) != find(v2)) return false;
        return true;
    }
}
