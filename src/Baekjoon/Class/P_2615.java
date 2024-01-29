package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2615 {
	
	static int size = 19;
	static int[][] board = new int[size][size];
	
	static int resultY = 0;
	static int resultX = 0;
	
	static int[] dx = {1, 1, 1, 0};
	static int[] dy = {1, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < size; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int color = 0;
		Loop1:
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] != 0) {
					color = checkWin(i, j);
					if (color > 0) {
						break Loop1;
					}
				}
			}
		}
		
		if (color > 0) {
			sb.append(color).append("\n").append(resultY).append(" ").append(resultX);
		}
		else {
			sb.append(0);
		}
		
		System.out.print(sb);
	}

	private static int checkWin(int y, int x) {
		int curColor = board[y][x];
		for (int i = 0; i < 4; i++) {
			int cnt = 1;
			while (true) {
				int nextY = y + dy[i] * cnt;
				int nextX = x + dx[i] * cnt;
				
				if (isValidLocation(nextY, nextX, curColor)) {
					cnt++;
				}
				else {
					break;
				}
			}
			if (cnt == 5) {
				cnt += checkReverse(y, x, i);
			}
			
			if (cnt == 5) {
				resultY = y + 1;
				resultX = x + 1;
				return curColor;
			}
		}
		return -1;
	}

	private static int checkReverse(int y, int x, int idx) {
		int cnt = 1;
		int curColor = board[y][x];
		while (true) {
			int prevY = y + dy[idx] * cnt * -1;
			int prevX = x + dx[idx] * cnt * -1;
			
			if (isValidLocation(prevY, prevX, curColor)) {
				cnt++;
			}
			else {
				break;
			}
		}
		return cnt - 1;
	}

	private static boolean isValidLocation(int nextY, int nextX, int color) {
		return nextY >= 0 && nextY < size && nextX >= 0 && nextX < size && color == board[nextY][nextX];
	}
	
}
