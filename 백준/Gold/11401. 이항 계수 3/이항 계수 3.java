import java.io.*;
import java.util.*;

public class Main {
    
    private static final Integer MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        long num = factorial(n);
        long dem = factorial(k) * factorial(n - k) % MOD;
        
        System.out.print(num * pow(dem, MOD - 2) % MOD);
    }
     
    private static long factorial(int n) {
        long result = 1L;
        while (n > 0) {
            result = result * n-- % MOD;
        }
        return result;
    }
    
    private static long pow(long base, long expo) {
        if (expo == 1) {
            return base % MOD;
        }
        
        long temp = pow(base, expo / 2);
        
        if (expo % 2 == 1) {
            return (temp * temp % MOD) * base % MOD;
        }
        return temp * temp % MOD;
    }
}