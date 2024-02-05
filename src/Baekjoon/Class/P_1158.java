package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		sb.append("<");
		int curIdx = 0;
		while (list.size() > 1) {
			int nextIdx;
			for (nextIdx = curIdx; nextIdx < curIdx + k - 1; nextIdx++) {
				list.add(list.size() - 1, list.remove(0));
			}
			sb.append(list.remove(0)).append(", ");
			curIdx = nextIdx % n;
		}
		
		sb.append(list.remove(0)).append(">");
		System.out.println(sb);
	}
}
