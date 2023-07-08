package TossNEXT.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_NEXT2023_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] relationships = new int[n][2];
        for (int i = 0; i < relationships.length; i++) relationships[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int target = line[0], limit = line[1];

        ArrayList<Integer>[] graph = new ArrayList[101];
        for (int i = 0; i <= 100; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[relationships[i][0]].add(relationships[i][1]);
            graph[relationships[i][1]].add(relationships[i][0]);
        }

        int amount = 0;
        ArrayList<Integer> newFriends = new ArrayList<>();
        LinkedList<int[]> queue = new LinkedList<>();
        for (Integer integer : graph[target]) {
            boolean isContain = false;
            for (int[] ints : queue) {
                if (ints[0] == integer) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                queue.add(new int[]{integer, 1});
                amount += 5;
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int cur = poll[0];
            int lim = poll[1];

            if (lim >= limit) continue;

            for (Integer integer : graph[cur]) {
                if (integer != target && !graph[target].contains(integer) && !newFriends.contains(integer)) {
                    newFriends.add(integer);
                    queue.add(new int[] {integer, lim + 1});
                }
            }
        }

        amount = amount + (newFriends.size() * 10) + newFriends.size();

        bw.write(Integer.toString(amount));
        bw.flush();
    }
}
