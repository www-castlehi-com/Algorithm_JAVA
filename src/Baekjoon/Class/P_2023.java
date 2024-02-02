package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P_2023 {
	
	static int n;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int[] prime = {2, 3, 5, 7};
		for (int i = 0; i < 4; i++) {
			makePrime(prime[i], 1);
		}
		
		System.out.print(sb);
	}

	private static void makePrime(int prime, int digit) {
		if (digit == n) {
			sb.append(prime).append("\n");
		}
		else {
			for (int i = 1; i <= 9; i++) {
				int number = prime * 10 + i;
				if (isPrime(number)) {
					makePrime(number, digit + 1);
				}
			}
		}
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i <= (int) Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
