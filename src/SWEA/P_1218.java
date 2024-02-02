package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P_1218 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> stack = new Stack<>();
		int result = 1;
		for (int test = 1; test <= 10; test++) {
			int n = Integer.parseInt(br.readLine());
			
			String brackets = br.readLine();
			result = 1;
			for (int j = 0; j < n; j++) {
				char bracket = brackets.charAt(j);
				if (bracket == '(' || bracket == '[' || bracket == '{' || bracket == '<') {
					stack.add(bracket);
				}
				else if (!stack.isEmpty()) {
					char target = stack.pop();
					if ((bracket == ')' && target == '(') || (bracket == ']' && target == '[') || (bracket == '}' && target == '{') || (bracket == '>' && target == '<')) {
						continue;
					}
					else {
						result = 0;
						break;
					}
				}
				else {
					result = 0;
					break;
				}
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
