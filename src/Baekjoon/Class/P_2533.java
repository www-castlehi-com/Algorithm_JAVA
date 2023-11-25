package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class P_2533 {

  static int n;
  static ArrayList<Integer>[] graph;
  static boolean[] isEarlyAdopter;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());
    graph = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
    isEarlyAdopter = new boolean[n + 1];

    for (int i = 0; i < n; i++) {
      int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      graph[line[0]].add(line[1]);
      graph[line[1]].add(line[0]);
    }

  }
}
