package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_11725 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) tree[i] = new ArrayList<>();
        for (int i = 0 ; i < n - 1; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree[arr[0]].add(arr[1]);
            tree[arr[1]].add(arr[0]);
        }

        int[] parents = new int[n + 1];

        dfs(tree, parents, 1, 0);

        for (int i = 2; i <= n; i++) bw.write(parents[i] + "\n");
        bw.flush();
    }

    private static void dfs(ArrayList<Integer>[] tree, int[] parents, int start, int parent) {
        parents[start] = parent;
        for (Integer integer : tree[start]) {
            if (integer != parent) dfs(tree, parents, integer, start);
        }
    }
}
