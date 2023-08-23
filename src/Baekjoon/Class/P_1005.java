package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1005 {

    static int n, k, w;
    static int[] time;
    static ArrayList<Integer>[] graph;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = line[0]; k = line[1];
            time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            depth = new int[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                graph[line[0]].add(line[1]);
                depth[line[1]]++;
            }
            w = Integer.parseInt(br.readLine());

            bw.write(topologicalSort() + "\n");
        }
        bw.flush();
    }

    private static int topologicalSort() {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = time[i - 1];

            if (depth[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            for (Integer integer : graph[poll]) {
                dp[integer] = Math.max(dp[integer], dp[poll] + time[integer - 1]);
                depth[integer]--;

                if (depth[integer] == 0) queue.add(integer);
            }
        }

        return dp[w];
    }
}
