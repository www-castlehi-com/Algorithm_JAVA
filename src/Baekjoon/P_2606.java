package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2606 {

    static int[][] edges;
    static boolean[] visited;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int vertexCnt = Integer.parseInt(br.readLine());
        int edgeCnt = Integer.parseInt(br.readLine());

        edges = new int[vertexCnt + 1][vertexCnt + 1];
        visited = new boolean[vertexCnt + 1];

        for (int i = 0 ; i < edgeCnt; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            edges[arr[0]][arr[1]] = 1;
            edges[arr[1]][arr[0]] = 1;
        }

        visited[1] = true;
        dfs(1);

        bw.write(Integer.toString(result));
        bw.flush();
    }

    public static void dfs(int idx) {
        if (idx == edges.length) return;
        else {
            for (int i = 1; i < edges[idx].length; i++) {
                if (edges[idx][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    result++;
                    dfs(i);
                }
            }
        }
    }
}
