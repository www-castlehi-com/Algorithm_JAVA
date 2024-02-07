package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_9229 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for (int test = 1; test <= tc; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] snacks = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(snacks);
			

			int p1 = 0, p2 = n - 1;
			int result = 0;
			while (p1 < p2) {
				int subResult = snacks[p1] + snacks[p2];
				
				if (subResult <= m) {
					result = Math.max(result, subResult);
					p1++;
				}
				else {
					p2--;
				}
			}
			
			sb.append("#").append(test).append(" ").append(result == 0 ? -1 : result).append("\n");
		}
		System.out.print(sb);
	}
}
