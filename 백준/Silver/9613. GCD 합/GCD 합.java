import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            long sum = 0;
            for (int i = 1; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    sum += gcd(arr[i], arr[j]);
                }
            }
            sb.append(sum + "\n");
        }
        
        System.out.print(sb);
    }
    
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}