package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_16637
{
    static int n;
    static int[] number;
    static char[] operator;
    static int answer = Integer.MIN_VALUE;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		number = new int[n / 2 + 1];
		operator = new char[n / 2];
		String[] line = br.readLine().split("");
		for (int i = 0; i < n; i++) {
		    if (i % 2 == 0) number[i / 2] = Integer.parseInt(line[i]);
		    else operator[i / 2] = line[i].charAt(0);
		}
		
		dfs(0, number[0]);
		
		System.out.print(answer);
	}
	
	public static void dfs(int cur, int result) {
	    if (cur == n / 2) {
	        answer = Math.max(answer, result);
	        return ;
	    }
	    
	    int subResult = calc(operator[cur], result, number[cur + 1]);
	    dfs(cur + 1, subResult);
	    
	    if (cur < n / 2 - 1) {
	        int nextResult = calc(operator[cur + 1], number[cur + 1], number[cur + 2]);
	        subResult = calc(operator[cur], result, nextResult);
	        dfs(cur + 2, subResult);
	    }
	}
	
	public static int calc(char operator, int number1, int number2) {
	    switch(operator) {
	        case '+' :
	            return number1 + number2;
	        case '-' :
	            return number1 - number2;
	        default :
	            return number1 * number2;
	    }
	}
}
