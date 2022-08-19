package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_13023 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean status;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] friend_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new ArrayList[friend_info[0]];
        for (int i = 0; i < friend_info[0]; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < friend_info[1]; i++) {
            int[] friend = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = friend[0];
            int to = friend[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < friend_info[0]; i++) {
            if (!status) {
                visited = new boolean[friend_info[0]];
                dfs(i, 1);
            }
        }

        if (status) bw.write(Integer.toString(1));
        else bw.write(Integer.toString(0));
        bw.flush();
    }

    public static void dfs(int idx, int depth) {
        if (depth == 5) {
            status = true;
            return ;
        }
        else {
            visited[idx] = true;
            for (int num : graph[idx]) {
                if (!visited[num]) dfs(num, depth + 1);
            }
            visited[idx] = false;
        }
    }
}
