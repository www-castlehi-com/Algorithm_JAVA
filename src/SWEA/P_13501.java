package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P_13501 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			numbers.clear();
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}
			
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				char command = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				
				if (command == 'I') {
					int y = Integer.parseInt(st.nextToken());
					numbers.add(x, y);
				}
				else if (command == 'D') {
					numbers.remove(x);
				}
				else if (command == 'C') {
					int y = Integer.parseInt(st.nextToken());
					numbers.set(x, y);
				}
			}
			
			int result = numbers.size() >= l ? numbers.get(l) : -1;
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
}
