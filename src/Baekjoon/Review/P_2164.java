package Baekjoon.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class P_2164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for (int i = 1; i <= n; i++) {
			deque.add(i);
		}
		
		while (n-- > 1) {
			deque.pollFirst();
			deque.addLast(deque.pollFirst());
		}
		System.out.print(deque.poll());
	}
}
