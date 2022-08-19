package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1753 {

    static int[] shortWay;
    static PriorityQueue<int[]>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int v = s[0], e = s[1];
        int k = Integer.parseInt(br.readLine());

        graph = new PriorityQueue[v + 1];
        for (int i = 0; i <= v; i++) graph[i] = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < e; i++) {
            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[s[0]].add(new int[]{s[1], s[2]});
        }

        shortWay = new int[v + 1];
        Arrays.fill(shortWay, Integer.MAX_VALUE);
        visited = new boolean[v + 1];
        djikstra(k);

        for (int i = 1; i <= v; i++) {
            if (shortWay[i] == Integer.MAX_VALUE) bw.write("INF\n");
            else bw.write(shortWay[i] + "\n");
        }
        bw.flush();
    }

    private static void djikstra(int start) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        queue.add(new int[] {start, 0});
        shortWay[start] = 0;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int cur = poll[0];
            int weigh = poll[1];

            if (shortWay[cur] < weigh) continue;
            for (int[] ints : graph[cur]) {
                int next = ints[0];
                int nextWeigh = weigh + ints[1];

                if (nextWeigh < shortWay[next]){
                    shortWay[next] = nextWeigh;
                    queue.add(new int[]{next, nextWeigh});
                }
            }
        }
    }
}
