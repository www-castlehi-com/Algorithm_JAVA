package Baekjoon.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_16926 {
	
	static int n, m, r;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] copyMap = new int[n][m];
		for (int i = 0; i < r; i++) {
			turnArray(copyMap);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void turnArray(int[][] copyMap) {
		int group = Math.min(n, m) / 2;
		for (int i = 0; i < group; i++) {
			for (int j = i + 1; j < m - i; j++) {
				//왼쪽으로 이동
				copyMap[i][j - 1] = map[i][j];
				//오른쪽으로 이동
				copyMap[n - i - 1][m - j] = map[n - i - 1][m - j - 1];
			}
			
			for (int j = i + 1; j < n - i; j++) {
				//아래로 이동
				copyMap[n - j][i] = map[n - j - 1][i];
				//위로 이동
				copyMap[j - 1][m - i - 1] = map[j][m - i - 1];
			}
		}
		
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.copyOf(copyMap[i], m);
		}
	}
}
