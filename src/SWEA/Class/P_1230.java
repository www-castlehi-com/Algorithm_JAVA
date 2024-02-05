package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P_1230 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Integer> encryptNumbers = new LinkedList<Integer>();
		LinkedList<Integer> addSequence = new LinkedList<Integer>();
		for (int test = 1; test <= 10; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			encryptNumbers.clear();

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				encryptNumbers.add(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i< m; i++) {
				char command = st.nextToken().charAt(0);
				if (command == 'I') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					addSequence.clear();
					for (int j = 0; j < y; j++) {
						int s = Integer.parseInt(st.nextToken());
						addSequence.addLast(s);
					}
					encryptNumbers.addAll(x, addSequence);
				}
				else if (command == 'D') {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j< y; j++) {
						encryptNumbers.remove(x + 1);
					}
				}
				else if (command == 'A') {
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) {
						int s = Integer.parseInt(st.nextToken());
						encryptNumbers.addLast(s);
					}
				}
			}
			
			sb.append("#").append(test).append(" ");
			for (int i = 0; i < 10; i++) {
				sb.append(encryptNumbers.removeFirst()).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}