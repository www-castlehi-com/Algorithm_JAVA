package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] liquids = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquids);

		int p1 = 0, p2 = n - 1;
		int result = 2_000_000_000;
		int resultP1 = p1, resultP2 = p2;
		while (p1 < p2) {
			int subResult = liquids[p1] + liquids[p2];
			if (Math.abs(subResult) < Math.abs(result)) {
				result = subResult;
				resultP1 = p1;
				resultP2 = p2;
			}

			if (subResult < 0) {
				p1++;
			} else {
				p2--;
			}
		}

		sb.append(liquids[resultP1]).append(" ").append(liquids[resultP2]);
		System.out.print(sb);
	}
}
