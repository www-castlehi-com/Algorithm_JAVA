import java.io.*;
import java.util.*;

public class Main {
    
    private static int MAX_VALUE = 123_456 * 2;
    
    private static boolean[] isPrime = new boolean[MAX_VALUE + 1];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        initializePrime();
        
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break ;
            }
            
            int count = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (isPrime[i]) {
                    count++;
                }
            }
            
            sb.append(count + "\n");
        }
        
        System.out.print(sb);
    }
    
    private static void initializePrime() {
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        
        for (int i = 2; i * i <= MAX_VALUE; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j <= MAX_VALUE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}