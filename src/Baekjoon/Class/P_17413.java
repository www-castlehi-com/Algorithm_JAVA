package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P_17413 {
	
	static StringBuilder sb = new StringBuilder();
	static ArrayDeque<Character> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		boolean isInBracket = false;
		for (int i = 0; i < s.length(); i++) {
			char target = s.charAt(i);
			if (target == '<') {
				clearDeque();
				isInBracket = true;
				deque.add(target);
			}
			else if (target == '>') {
				deque.add(target);
				while (deque.peek() != target) {
					sb.append(deque.pollFirst());
				}
				sb.append(deque.poll());
				isInBracket = false;
			}
			else if (!isInBracket && s.charAt(i) == ' ') {
				clearDeque();
				sb.append(target);
			}
			else {
				deque.add(target);
			}
		}
		
		clearDeque();
		
		System.out.print(sb);
	}

	private static void clearDeque() {
		while (!deque.isEmpty()) {
			sb.append(deque.pollLast());
		}
	}
}
