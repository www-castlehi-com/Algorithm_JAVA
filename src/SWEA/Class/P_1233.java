package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1233 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int test = 1; test <= 10; test++) {
			int n = Integer.parseInt(br.readLine());
			
			boolean canCaculate = true;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				char elem = st.nextToken().charAt(0);
				int leftIdx = 0;
				int rightIdx = 0;
				if (st.hasMoreTokens()) {
					leftIdx = Integer.parseInt(st.nextToken());
				}
				if (st.hasMoreTokens()) {
					rightIdx = Integer.parseInt(st.nextToken());
				}
				
				if ((elem < '0' || elem > '9') && leftIdx == 0 && rightIdx == 0) {
					canCaculate = false;
				}
			}
			
			sb.append("#").append(test).append(" ").append(canCaculate ? "1" : "0").append("\n");
		}
		
		System.out.print(sb);
	}
}
