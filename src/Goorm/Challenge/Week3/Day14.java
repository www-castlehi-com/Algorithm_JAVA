package Goorm.Challenge.Week3;

import java.io.*;
import java.util.*;

public class Day14 {

    static int n, m, k;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static int cnt = 1, last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1]; k = line[2];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[line[0]].add(line[1]);
            graph[line[1]].add(line[0]);
        }
        for (int i = 0; i <= n; i++) Collections.sort(graph[i]);
        visited = new boolean[n + 1];

        visited[k] = true;
        last = k;
        dfs(k);

        bw.write(cnt + " " + last);
        bw.flush();
    }

    private static void dfs(int k) {
        for (Integer integer : graph[k]) {
            if (!visited[integer]) {
                visited[integer] = true;
                cnt++;
                last = integer;
                dfs(integer);
                break;
            }
        }
    }
}
