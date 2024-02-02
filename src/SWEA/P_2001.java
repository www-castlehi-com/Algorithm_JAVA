package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2001 {
	
	static int n;
	static int m;
	static int[][] area = new int[16][16];
	
	static int[] start = {0, 0};
	static int[] end = {0, 0};
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					area[i][j] = area[i - 1][j] + area[i][j - 1] - area[i - 1][j - 1] + Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			for (int i = 1; i <= n - m + 1; i++) {
				for (int j = 1; j <= n - m + 1; j++) {
					start[0] = i; start[1] = j;
					end[0] = i + m - 1; end[1] = j + m - 1;
					
					result = Math.max(result, area[end[0]][end[1]] - area[end[0]][start[1] - 1] - area[start[0] - 1][end[1]] + area[start[0] - 1][start[1] - 1]);
				}
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
