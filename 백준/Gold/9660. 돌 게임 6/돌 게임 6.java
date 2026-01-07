import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        
        bw.write(isCYWin(n) ? "CY" : "SK");
        bw.flush();
    }
    
    private static boolean isCYWin(long n) {
        return ((n % 7 == 0) || (n % 7 == 2));
    }
}