package Baekjoon.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_16935 {
	
	static int n, m;
	static int[][] map = new int[100][100];
	static int[][] copyMap = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		for (int i = 0; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int command = Integer.parseInt(st.nextToken());
			
			switch(command) {
			case 1:
				upDownInversion();
				break;
			case 2:
				leftRightInversion();
				break;
			case 3:
				clockwiseRotation();
				break;
			case 4:
				counterClockwiseRotation();
				break;
			case 5:
				clockwiseGroupRotation();
				break;
			case 6:
				counterClockwiseGroupRotation();
				break;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	private static void upDownInversion() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n / 2; j++) {
				swap(j, i, n - j - 1, i);
			}
		}
	}
	
	private static void leftRightInversion() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m / 2; j++) {
				swap(i, j, i, m - j - 1);
			}
		}
	}
	
	private static void clockwiseRotation() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap[j][n - 1 - i] = map[i][j];
			}
		}
		
		copyArrayAndChangeDirection();
	}
	
	private static void counterClockwiseRotation() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copyMap[m - 1 - j][i] = map[i][j];
			}
		}
		
		copyArrayAndChangeDirection();
	}
	
	private static void clockwiseGroupRotation() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i < n / 2 && j < m / 2) {
					copyMap[i][m / 2 + j] = map[i][j];
				}
				else if (i < n / 2) {
					copyMap[i + n / 2][j] = map[i][j];
				}
				else if (j >= m / 2) {
					copyMap[i][j - m / 2] = map[i][j];
				}
				else {
					copyMap[i - n / 2][j] = map[i][j];
				}
			}
		}
		
		copyArray();
	}
	
	private static void counterClockwiseGroupRotation() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i < n / 2 && j < m / 2) {
					copyMap[n / 2 + i][j] = map[i][j];
				}
				else if (i < n / 2) {
					copyMap[i][j - m / 2] = map[i][j];
				}
				else if (j >= m / 2) {
					copyMap[i - n / 2][j] = map[i][j];
				}
				else {
					copyMap[i][j + m / 2] = map[i][j];
				}
			}
		}
		
		copyArray();
	}
	
	private static void swap(int y1, int x1, int y2, int x2) {
		int temp = map[y1][x1];
		map[y1][x1] = map[y2][x2];
		map[y2][x2] = temp;
	}
	
	private static void copyArrayAndChangeDirection() {
		for (int i = 0; i < m; i++) {
			map[i] = Arrays.copyOf(copyMap[i], n);
		}
		
		int temp = n;
		n = m;
		m = temp;
	}
	
	private static void copyArray() {
		for (int i = 0; i < n; i++) {
			map[i] = Arrays.copyOf(copyMap[i], m);
		}
	}
}
