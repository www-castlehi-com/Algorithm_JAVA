package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class P_1967 {

    static ArrayList<int []>[] tree;
    static int maxDia = 0;
    static int maxStart = 1;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            tree[s[0]].add(new int[]{s[1], s[2]});
            tree[s[1]].add(new int[]{s[0], s[2]});
        }

        visited = new boolean[n + 1][n + 1];
        dfs(1, 0);

        maxDia = 0;
        visited = new boolean[n + 1][n + 1];
        dfs(maxStart, 0);
        bw.write(Integer.toString(maxDia));
        bw.flush();
    }

    private static void dfs(int root, int diameter) {
        if (maxDia < diameter) {
            maxDia = diameter;
            maxStart = root;
        }

        for (int[] ints : tree[root]) {
            if (!visited[root][ints[0]]) {
                visited[root][ints[0]] = true;
                visited[ints[0]][root] = true;
                dfs(ints[0], diameter + ints[1]);
            }
        }
    }
}
