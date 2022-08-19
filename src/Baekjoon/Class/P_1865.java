package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P_1865 {

    static int n;
    static List<int[]>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = split[0];
            int m = split[1], w = split[2];

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int s = split[0], e = split[1], t = split[2];
                graph[s].add(new int[]{e, t});
                graph[e].add(new int[]{s, t});
            }
            for (int i = 0; i < w; i++) {
                split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int s = split[0], e = split[1], t = split[2];
                graph[s].add(new int[]{e, -t});
            }

            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            boolean isMinus = false;
            for (int i = 1; i <= n; i++) {
                if (bellmanford(i)) {
                    isMinus = true;
                    break;
                }
            }

            if (isMinus) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
    }

    private static boolean bellmanford(int idx) {
        dist[idx] = 0;

        boolean isUpdated;
        for (int i = 0; i < n; i++) {
            isUpdated = false;
            for (int j = 1; j <= n; j++) {
                for (int[] ints : graph[j]) {
                    int next = ints[0];
                    int nextWeigh = ints[1];

                    if (dist[j] == Integer.MAX_VALUE || nextWeigh == Integer.MAX_VALUE) continue;

                    if (dist[next] > dist[j] + nextWeigh) {
                        dist[next] = dist[j] + nextWeigh;
                        isUpdated = true;
                        if (i == n - 1) return true;
                    }
                }
            }
            if (!isUpdated) break;
        }
        return false;
    }
}
