package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_15591 {
	
	static int n;
	static final int MAX = 1_000_000_001;
	static ArrayList<int[]>[] usado;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		usado = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			usado[i] = new ArrayList<int[]>();
		}
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int qi = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			usado[p].add(new int[] {qi, r});
			usado[qi].add(new int[] {p, r});
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(bfs(k, v)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(int k, int v) {
		PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
		boolean[] visited = new boolean[n + 1];
		queue.add(new int[] {v, MAX});
		visited[v] = true;
		
		int cnt = 0;
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int vertex = poll[0];
			int weigh = poll[1];
			
			if (weigh != MAX && weigh >= k) {
				cnt++;
			}
			
			for (int[] elem : usado[vertex]) {
				if (!visited[elem[0]]) {
					visited[elem[0]] = true;
					queue.add(new int[] {elem[0], Math.min(weigh, elem[1])});
				}
			}
		}
		
		return cnt;
	}
}
