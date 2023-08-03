package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11404 {

    static int n, m;
    static long[][] graph;

    static class Node {
        int end;
        int weigh;

        public Node(int end, int weigh) {
            this.end = end;
            this.weigh = weigh;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new long[n + 1][n + 1];
        for (int i = 0; i <= n;i++) Arrays.fill(graph[i], Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (graph[line[0]][line[1]] > line[2]) graph[line[0]][line[1]] = line[2];
        }

       for (int i = 1; i <= n; i++) {
           for (int j = 1; j <= n; j++) {
               for (int k = 1; k <= n; k++) {
                   if (j == k) graph[j][k] = 0;
                   else graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
               }
           }
       }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == Integer.MAX_VALUE) bw.write(0 + " ");
                else bw.write(graph[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
