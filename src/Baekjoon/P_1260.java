package Baekjoon;

import java.io.*;
import java.util.*;

public class P_1260 {

    static BufferedWriter bw;
    static ArrayList<Integer>[] graph;
    static boolean[] visitied;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[info[0] + 1];
        for (int i = 0; i <= info[0]; i++) graph[i] = new ArrayList<Integer>();

        for (int i = 0; i < info[1]; i++) {
            int[] vertex = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            graph[vertex[0]].add(vertex[1]);
            graph[vertex[1]].add(vertex[0]);
        }
        for (int i = 0; i <= info[0]; i++) Collections.sort(graph[i]);

        visitied = new boolean[info[0] + 1];
        dfs(info[2]);
        bw.newLine();
        visitied = new boolean[info[0] + 1];
        bfs(info[2]);

        bw.flush();
    }

    public static void dfs(int idx) throws IOException {
        bw.write(idx + " ");
        visitied[idx] = true;
        for (int v : graph[idx]) {
            if (!visitied[v]) {
                dfs(v);
            }
        }
    }

    public static void bfs(int idx) throws IOException {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(idx);
        visitied[idx] = true;
        while (!queue.isEmpty()) {
            idx = queue.remove();
            bw.write(idx + " ");
            for (int v : graph[idx]) {
                if (!visitied[v]) {
                    visitied[v] = true;
                    queue.add(v);
                }
            }
        }
    }
}
