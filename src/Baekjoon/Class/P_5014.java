package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class P_5014 {
	
	static int f, s, g, u, d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		int result = bfs();
		System.out.println(result == -1 ? "use the stairs" : result);
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[f + 1];
		queue.add(new int[] {s, 0});
		visited[s] = true;
		
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int floor = poll[0];
			int cnt = poll[1];
			
			if (floor == g) {
				return cnt;
			}
			
			if (floor + u <= f && !visited[floor + u]) {
				visited[floor + u] = true;
				queue.add(new int[] {floor + u, cnt + 1});
			}
			if (floor - d >= 1 && !visited[floor - d]) {
				visited[floor - d] = true;
				queue.add(new int[] {floor - d, cnt + 1});
			}
		}
		
		return -1;
	}
}
