package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P_7465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<int[]> graph = new ArrayList<>();
            int[] root = new int[n + 1];
            for (int i = 1; i<= n; i++) {
                root[i] = i;
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.add(new int[]{a, b});
            }

            for (int i = 0; i < m; i++) {
                int[] relationship = graph.get(i);
                int a = relationship[0], b = relationship[1];

                if (find(root, a) != find(root, b)) union(root, a, b);
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (root[i] == i) cnt++;
            }

            System.out.println("#" + test + " " + cnt);
        }
    }

    private static void union(int[] root, int v1, int v2) {
        int p1 = root[v1];
        int p2 = root[v2];

        if (p1 != p2) root[p2] = p1;
    }

    private static int find(int[] root, int v) {
        if (root[v] == v)
            return v;
        else
            return root[v] = find(root, root[v]);
    }
}
