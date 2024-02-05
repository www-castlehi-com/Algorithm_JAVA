package SWEA.Class;

import java.io.*;
import java.util.Arrays;

public class P_1206 {
	
	static int n;
	static int[] arr;
	
	static int[] dx = {-2, -1, 1, 2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int t = 1; t <= 10; t++) {
			n = Integer.parseInt(br.readLine());
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int res = 0;
			for (int i = 0; i < n; i++) {
				res += check(i);
			}
			
			bw.write("#" + t+ " " + res + "\n");
		}
		bw.flush();
	}

	private static int check(int idx) {
		int height = arr[idx];
		int canKeep = height;
		for (int i = 0; i < 4; i++) {
			int nextX = idx + dx[i];
			
			if (nextX >= 0 && nextX < n) {
				if (arr[nextX] < height) {
					canKeep = Math.min(canKeep, height - arr[nextX]);
				}
				else {
					return 0;
				}
			}
		}
		return canKeep;
	}
}
