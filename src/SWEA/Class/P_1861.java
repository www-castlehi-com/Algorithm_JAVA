package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_1861 {
	
	static int n;
	static int[][] room = new int[1000][1000];
	static boolean[][] visited;
	static ArrayList<int[]> rooms = new ArrayList<>();
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			n = Integer.parseInt(br.readLine());
			visited = new boolean[n][n];
			rooms.clear();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
					rooms.add(new int[] {i, j});
				}
			}

			int result = 0;
			int resultIdx = Integer.MAX_VALUE;
			Collections.sort(rooms, Comparator.comparing(o -> room[o[0]][o[1]]));		
			for (int i = 0; i < rooms.size(); i++) {
				int[] poll = rooms.get(i);
				int curY = poll[0];
				int curX = poll[1];
				
				if (!visited[curY][curX]) {
					int cnt = bfs(curY, curX);
					
					if ((result < cnt) || (result == cnt && resultIdx > room[curY][curX])) {
						resultIdx = room[curY][curX];
						result = cnt;
					}
				}
			}
			
			sb.append("#").append(test).append(" ").append(resultIdx).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static int bfs(int y, int x) {
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] {y, x});
		
		int cnt = 0;
		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int curY = poll[0];
			int curX = poll[1];
			
			if (!visited[curY][curX]) {
				visited[curY][curX] = true;
				cnt++;
				for (int i = 0; i < 4; i++) {
					int nextY = curY + dy[i];
					int nextX = curX + dx[i];
					
					if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && room[nextY][nextX] == room[curY][curX] + 1) {
						queue.add(new int[] {nextY, nextX});
					}
				}
			}
		}
		
		return cnt;
	}
}
