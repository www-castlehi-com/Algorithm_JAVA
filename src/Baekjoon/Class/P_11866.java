package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class P_11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], k = arr[1];
        int[] sequence = new int[n];
        for (int i = 1; i <= n; i++) sequence[i - 1] = i;
        boolean[] visited = new boolean[n];
        ArrayList<Integer> removeList = new ArrayList<>();

        int idx = -1;
        while (removeList.size() < n) {
            int cnt = 1;
            while (cnt <= k) {
                idx = (idx + 1) % n;
                if (!visited[idx]) cnt++;
            }
            visited[idx] = true;
            removeList.add(sequence[idx]);
        }

        bw.write("<");
        for (int i = 0; i < removeList.size(); i++) {
            if (i != removeList.size() - 1) bw.write(removeList.get(i) + ", ");
            else bw.write(removeList.get(i) + ">");
        }
        bw.flush();
    }
}
