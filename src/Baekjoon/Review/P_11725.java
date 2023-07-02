package Baekjoon.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_11725 {

    static ArrayList<Integer>[] tree;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree[line[0]].add(line[1]);
            tree[line[1]].add(line[0]);
        }

        parents = new int[n + 1];

        dfs(1, 0);

        for (int i = 2; i < n + 1; i++) bw.write(parents[i] + "\n");
        bw.flush();
    }

    private static void dfs(int cur, int par) {
        parents[cur] = par;
        for (Integer integer : tree[cur]) {
            if (integer != par) dfs(integer, cur);
        }
    }
}
