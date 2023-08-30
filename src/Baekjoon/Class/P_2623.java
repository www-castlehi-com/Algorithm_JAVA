package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_2623 {

    static int n, m;
    static ArrayList<Integer>[] graph;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0];
        m = line[1];
        depth = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < line[0]; j++) {
                graph[line[j]].add(line[j + 1]);
                depth[line[j + 1]]++;
            }
        }

        ArrayList<Integer> list = topologicalSort();
        if (list.size() != n) bw.write(Integer.toString(0));
        else {
            for (Integer integer : list) {
                bw.write(integer + "\n");
            }
        }
        bw.flush();
    }

    private static ArrayList<Integer> topologicalSort(){
        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (depth[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            list.add(poll);

            for (Integer integer : graph[poll]) {
                depth[integer]--;

                if (depth[integer] == 0) queue.add(integer);
            }
        }

        return list;
    }
}
