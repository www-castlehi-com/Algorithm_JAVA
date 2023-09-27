package Baekjoon.Class;

import java.io.*;

public class P_10775 {

    static int g, p;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());
        parents = new int[g + 1];
        for (int i = 0; i <= g; i++) parents[i] = i;

        int cnt = 0;
        for (int i = 0; i < p; i++) {
            int airplane = Integer.parseInt(br.readLine());

            int docking = find(airplane);
            if (docking == 0) break;
            union(docking, docking - 1);
            cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void union(int cur, int target) {
        cur = find(cur);
        target = find(target);

        if (cur != target) parents[cur] = target;
    }

    private static int find(int airplane) {
        if (parents[airplane] == airplane) return airplane;
        return parents[airplane] = find(parents[airplane]);
    }
}
