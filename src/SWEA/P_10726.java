package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_10726 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());
		for (int test = 1; test <= tc; test++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int answerBit = (1 << n) - 1;
			
			boolean isAnswer = false;
			if ((m & answerBit) == answerBit) {
				isAnswer = true;
			}
			
			sb.append("#").append(test).append(" ").append(isAnswer ? "ON" : "OFF").append("\n");
		}
		
		System.out.print(sb);
	}
}
