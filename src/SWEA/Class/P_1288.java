package SWEA.Class;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1288 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int test = 1; test <= t; test++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int copyN = n;
			
			int res = 0;
			while (true) {
				int a = n;
				while (a != 0) {
					int b = a % 10;
					a /= 10;
					res |= (1 << b);
				}
				
				if (res == (1 << 10) -1) {
					break;
				}
				
				n += copyN;
			}
			
			sb.append("#").append(test).append(" ").append(n).append("\n");
		}
		
		System.out.print(sb);
	}
}
