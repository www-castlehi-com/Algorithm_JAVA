package SWEA.Class;

import java.io.*;
import java.util.Arrays;

public class P_1859 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());
		int idx = 1;
		while (idx <= t) {
			int n = Integer.parseInt(br.readLine());
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			long res = 0;
			int i = n - 1;
			while (i > 0) {
				long max = line[i];
				int j = i - 1;
				
				long buy = 0;
				long cnt = 0;
				while (j >= 0 && line[j] < max) {
					buy += line[j];
					j--;
					cnt++;
				}
				res += (cnt * max - buy);
				
				i = j;
			}
			
			bw.write("#" + idx++ + " " + res + "\n");
		}
		bw.flush();
	}
}
