package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1890 {
	
	static int n;
	static int[][] map;
	
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long result = findLoad();
		System.out.print(result);
	}

	private static long findLoad() {
		if (map[0][0] == 0) return 0L;
		
		long[][] visited = new long[n][n];
		visited[0][0] = 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = map[i][j];
				
				if (cnt == 0) continue;
				
				for (int k = 0; k < 2; k++) {
					int nextY = i + dy[k] * cnt;
					int nextX = j + dx[k] * cnt;
					
					if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
						visited[nextY][nextX] += visited[i][j];
					}
				}
			}
		}
		
		return visited[n - 1][n - 1];
	}
}
