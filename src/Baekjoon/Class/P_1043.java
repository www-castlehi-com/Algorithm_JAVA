package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1043 {

    static int n, m;
    static int[] know;
    static HashSet<Integer> knowTruthPeople;
    static HashSet<Integer>[] graph;
    static ArrayList<Integer>[] parties;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1];
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int know_num = line[0];
        know = new int[know_num];
        knowTruthPeople = new HashSet<>();
        if (know_num != 0) {
            for (int i = 1; i <= know_num; i++) {
                know[i - 1] = line[i];
                knowTruthPeople.add(line[i]);
            }
        }
        graph = new HashSet[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new HashSet<>();
        parties = new ArrayList[m];
        for (int i = 0; i < m; i++) parties[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int num = line[0];
            for (int j = 1; j <= num; j++) {
                parties[i].add(line[j]);
                for (int k = j + 1; k <= num; k++) {
                    graph[line[j]].add(line[k]);
                    graph[line[k]].add(line[j]);
                }
            }
        }

        bfs(know);

        int cnt = 0;
        for (ArrayList<Integer> party : parties) {
            boolean lie = true;
            for (Integer knowTruthPerson : knowTruthPeople) {
                if (party.contains(knowTruthPerson)) {
                    lie = false;
                    break;
                }
            }
            if (lie) cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static void bfs(int[] know) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        for (int i : know) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            knowTruthPeople.add(poll);
            visited[poll] = true;

            for (Integer integer : graph[poll]) {
                if (!visited[integer]) queue.add(integer);
            }
        }
    }
}
