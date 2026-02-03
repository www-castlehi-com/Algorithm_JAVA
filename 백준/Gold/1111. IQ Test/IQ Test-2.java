import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		if (n == 1) {
		    System.out.print("A");
		} else if (n == 2) {
		    if (numbers[0] == numbers[1]) {
		        System.out.print(numbers[0]);
		    } else {
		        System.out.print("A");
		    }
		} else {
		    int a = 0;
		    if (numbers[0] - numbers[1] != 0) {
		        a = (numbers[1] - numbers[2]) / (numbers[0] - numbers[1]);
		    }
		    int b = numbers[1] - (numbers[0] * a);
		        
	        boolean isSolved = true;
	        for (int i = 0; i < n - 1; i++) {
	            if (numbers[i] * a + b != numbers[i + 1]) {
	                isSolved = false;
	                break;
	            }
	        }
	        
	        System.out.print(isSolved ? numbers[n - 1] * a + b : "B");
		}
	}
}
