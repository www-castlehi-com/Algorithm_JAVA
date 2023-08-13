package Baekjoon.Class;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class P_11779 {

    static ArrayList<Bus>[] graph;
    static int[] dist;
    static int[] route;

    static class Bus {
        int end;
        int fee;

        public Bus(int end, int fee) {
            this.end = end;
            this.fee = fee;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        route = new int[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0]].add(new Bus(line[1], line[2]));
        }
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = line[0], end = line[1];

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(start, end);

        int cur = end;
        ArrayList<Integer> routes = new ArrayList<>();
        routes.add(cur);
        while (route[cur] != 0) {
            routes.add(0, route[cur]);
            cur = route[cur];
        }

        bw.write(dist[end] + "\n" + routes.size() + "\n");
        for (Integer integer : routes) {
            bw.write(integer + " ");
        }
        bw.flush();

        bw.close();
        br.close();
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<Bus> queue = new PriorityQueue<>(Comparator.comparing(o -> o.fee));
        queue.add(new Bus(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Bus poll = queue.poll();

            if (poll.end == end) return;

            for (Bus bus : graph[poll.end]) {
                if (dist[bus.end] > dist[poll.end] + bus.fee) {
                    dist[bus.end] = dist[poll.end] + bus.fee;
                    route[bus.end] = poll.end;
                    queue.add(new Bus(bus.end, dist[poll.end] + bus.fee));
                }
            }
        }
    }
}

