import java.io.*;
import java.util.*;

public class Main {
    
    static int n;
    static int[] inOrder, postOrder;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        printPreOrder(0, n - 1, 0, n - 1);
	}
	
	private static void printPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
	    if (inStart > inEnd || postStart > postEnd) {
	        return;
	    }
	    
	    int root = postOrder[postEnd];
	    System.out.print(root + " ");
	    int idxDiff = postStart - inStart;
	    for (int i = inStart; i <= inEnd; i++) {
	        if (inOrder[i] == root) {
	            printPreOrder(inStart, i - 1, postStart, i - 1 + idxDiff);
	            printPreOrder(i + 1, inEnd, i + idxDiff, postEnd - 1);
	        }
	    }
	}
}