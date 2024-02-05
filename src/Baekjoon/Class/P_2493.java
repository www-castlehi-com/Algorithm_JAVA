package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P_2493 {
	
	static class Top {
		int idx;
		int height;
		
		public Top(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		Stack<Top> stack = new Stack<>();
		for (int i = 1; i <= n; i++) {
			Top top = new Top(i, Integer.parseInt(st.nextToken()));
			
			while (true) {
				if (stack.isEmpty()) {
					sb.append(0).append(" ");
					stack.add(top);
					break;
				}
				else {
					Top target = stack.peek();
					if (top.height > target.height) {
						stack.pop();
					}
					else {
						sb.append(target.idx).append(" ");
						stack.add(top);
						break;
					}
				}
			}
		}
		
		System.out.print(sb);
	}
}
