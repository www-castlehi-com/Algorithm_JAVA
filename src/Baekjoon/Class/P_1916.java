package Baekjoon.Class;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_1916 {

    static class Bus {
        int target;
        long fee;

        public Bus(int target, long fee) {
            this.target = target;
            this.fee = fee;
        }
    }

    static int n;
    static ArrayList<Bus>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<Bus>();
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[line[0]].add(new Bus(line[1], line[2]));
        }

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(Long.toString(Dijkstra(line[0], line[1])));
        bw.flush();
    }

    private static long Dijkstra(int start, int end) {
        PriorityQueue<Bus> queue = new PriorityQueue<>(Comparator.comparing(o -> o.fee));
        queue.add(new Bus(start, 0));

        long[] dp = new long[n + 1];
        Arrays.fill(dp, 100000000);

        while (!queue.isEmpty()) {
            Bus poll = queue.poll();

            int cur = poll.target;
            long fee = poll.fee;

            if (cur == end) break;

            for (Bus bus : graph[cur]) {
                long nextFee = bus.fee + fee;
                if (nextFee < dp[bus.target]) {
                    dp[bus.target] = nextFee;

                    queue.add(new Bus(bus.target, nextFee));
                }
            }
        }

        return dp[end];
    }
}
