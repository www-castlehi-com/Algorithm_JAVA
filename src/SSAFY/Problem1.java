package SSAFY;

import java.io.*;
import java.util.Arrays;

class Problem1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());
		for (int curT = 1; curT <= t; curT++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int n = line[0], k = line[1];
			
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(arr);
			
			int res = 1;
			if (k != 1) {
				int start = 0, end = 0;
				int cnt = 0;
				while (end < n) {
					cnt = end - start + 1;
					if (arr[end] - arr[start] <= k) {
						res = Math.max(res, cnt);
						end++;
					}
					else {
						start++;
					}
				}
			}
			
			bw.write("#" + curT + " " + res + "\n");
		}
		bw.flush();
	}
}
