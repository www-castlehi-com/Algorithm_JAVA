package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_20303 {

    static int n, m, k;
    static int[] candy;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static class Candy {
        int kids;
        int cnt;

        public Candy(int kids, int cnt) {
            this.kids = kids;
            this.cnt = cnt;
        }
    }

    static ArrayList<Candy> groupCandy = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1]; k = line[2];
        candy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0] - 1].add(line[1] - 1);
            graph[line[1] - 1].add(line[0] - 1);
        }
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }

        int[] dp = new int[k];
        int ans = 0;
        for (Candy candy1 : groupCandy) {
            for (int i = k - 1; i >= candy1.kids; i--) {
                dp[i] = Math.max(dp[i], dp[i - candy1.kids] + candy1.cnt);
                ans = Math.max(ans, dp[i]);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static void bfs(int target) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(target);

        int kid = 0, candyCnt = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            if (!visited[poll]) {
                visited[poll] = true;
                kid++;
                candyCnt += candy[poll];

                for (Integer integer : graph[poll]) {
                    queue.add(integer);
                }
            }
        }

        groupCandy.add(new Candy(kid, candyCnt));
    }
}
