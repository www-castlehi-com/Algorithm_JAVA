package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_1725
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] height = new int[n + 2];
		for (int i = 1; i <= n; i++) {
		    height[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		int ans = 0;
		for (int i = 1; i <= n + 1; i++) {
		    while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
		        int targetIdx = stack.pop();
		        ans = Math.max(ans, height[targetIdx] * (i - stack.peek() - 1));
		    }
		    stack.push(i);
		}
		
		System.out.println(ans);
	}
}
