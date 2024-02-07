package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_2563 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] visited = new int[101][101];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = startX + 10;
			int endY = startY + 10;
			
			for (int j = startY; j < endY; j++) {
				for (int k = startX; k < endX; k++) {
					visited[k][j] = 1;
				}
			}
		}
		
		int area = 0;
		for (int i = 0; i <= 100; i++) {
			area += Arrays.stream(visited[i]).sum();
		}
		
		System.out.println(area);
	}
}
