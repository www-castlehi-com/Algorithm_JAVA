package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_16566 {

    static int n, m, k;
    static int[] cards;
    static int[] targets;
    static int[] parents;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; m = line[1]; k = line[2];
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(cards);
        targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        parents = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) parents[i] = binarySearch(i);

        kruskal();

        bw.flush();
    }

    private static int binarySearch(int target) {
        int start = 0, end = m - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (cards[mid] <= target) start = mid + 1;
            else end = mid;
        }

        return cards[end];
    }

    private static void kruskal() throws IOException {
        for (int i = 0; i < k; i++) {
            int res = find(targets[i]);
            visited[parents[targets[i]]] = true;
            bw.write(res + "\n");
//            System.out.println("targets[i] = " + res);
//            for (int parent : parents) {
//                System.out.print(parent+ " ");
//            }
//            System.out.println();
//            System.out.println();
        }
    }

    private static int find(int target) {
//        System.out.println("parents[target] = " + parents[target] + "(" + visited[parents[target]]+")");
        if (!visited[parents[target]]) return parents[target];
        else return parents[target] = find(parents[target]);
    }
}
