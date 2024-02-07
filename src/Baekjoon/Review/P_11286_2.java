package Baekjoon.Review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P_11286_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int absO1 = Math.abs(o1);
				int absO2 = Math.abs(o2);
				
				if (absO1 == absO2) {
					return o1 - o2;
				}
				return absO1 - absO2;
			}
		});
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(br.readLine());
			
			if (number != 0) {
				minHeap.add(number);
			}
			else {
				if (minHeap.isEmpty()) {
					sb.append(0).append("\n");
				}
				else {
					sb.append(minHeap.poll()).append("\n");
				}
			}
		}
		
		System.out.print(sb);
	}
}
