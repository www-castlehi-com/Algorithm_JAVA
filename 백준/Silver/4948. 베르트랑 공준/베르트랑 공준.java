import java.util.*;
import java.io.*;

public class Main {
    
    private static int max = 123_456;
    private static boolean[] isComposite = new boolean[2 * max + 1];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        calculatePrime();
        
        while (true) {
            int testCase = Integer.parseInt(br.readLine());
            if (testCase == 0) {
                break;
            }
            
            int cnt = 0;
            for (int i = testCase + 1; i <= 2 * testCase; i++) {
                if (isComposite[i] == false) {
                    cnt++;
                }
            }
            bw.write(cnt + "\n");
        }
        
        bw.flush();
    }
    
    private static void calculatePrime() {
        isComposite[1] = true;
        for (int i = 2; i * i <= 2 * max; i++) {
            if (!isComposite[i]) {
                for (int j = 2 * i; j <= 2 * max; j += i) {
                    isComposite[j] = true;
                }
            }
        }
    }
}