package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P_1225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Deque<Integer> deque = new ArrayDeque<>();
		for (int test = 1; test <= 10; test++) {
			int tc = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			int reduction = 0;
			while (true) {
				int first = deque.pollFirst() - ((reduction++ % 5) + 1);
				if (first < 0) first = 0;
				
				deque.addLast(first);
				
				if (first == 0) break;
			}
			
			sb.append("#").append(test).append(" ");
			while (!deque.isEmpty()) {
				sb.append(deque.poll()).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
