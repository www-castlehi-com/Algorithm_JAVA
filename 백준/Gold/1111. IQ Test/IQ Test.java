import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static int[] numbers;
    private static List<int[]> rules = new LinkedList<>();
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		if (n == 1) {
		    System.out.print("A");
		} else if (n >= 2) {
		    initializeRules();
		    System.out.print(getNextNumber());
		}
	}
	
	private static void initializeRules() {
	    for (int i = -200; i <= 200; i++) {
	        for (int j = -20100; j <= 20100; j++) {
	            if (numbers[0] * i + j == numbers[1]) {
	                rules.add(new int[]{i, j});
	            }
	        }
	    }
	}
	
	private static String getNextNumber() {
	    for (int i = 0; i < n - 1; i++) {
	        int curNumber = numbers[i];
	        int nextNumber = numbers[i + 1];
	        rules.removeIf(rule -> curNumber * rule[0] + rule[1] != nextNumber);
	    }
	    
	    return calculateNextNumber();
	}
	
	private static String calculateNextNumber() {
	    String nextNumber;
	    if (rules.isEmpty()) {
	        nextNumber = "B";
	    } else if (isAllResultSame()) {
	        int[] rule = rules.get(0);
	        nextNumber = String.valueOf(numbers[n - 1] * rule[0] + rule[1]);
	    } else {
	        nextNumber = "A";
	    }
	    
	    return nextNumber;
	}
	
	private static boolean isAllResultSame() {
	    boolean isSame = true;
	    int nextNumber = 0;
	    for (int[] rule : rules) {
	        if (nextNumber == 0) {
	            nextNumber = numbers[n - 1] * rule[0] + rule[1];
	        } else {
	            if (numbers[n - 1] * rule[0] + rule[1] != nextNumber) {
	                isSame = false;
	                break;
	            }
	        }
	    }
	    
	    return isSame;
	}
}