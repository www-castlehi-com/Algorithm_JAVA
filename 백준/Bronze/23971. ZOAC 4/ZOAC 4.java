import java.io.*;
import java.util.*;

public class Main {
    
    private static int h, w, n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] classRoom = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        h = classRoom[0];
        w = classRoom[1];
        n = classRoom[2];
        m = classRoom[3];

        long answer = calculate();

        bw.write(String.valueOf(answer));
        bw.flush();
    }
    
    public static long calculate() {
        long hCount = h / (n + 1);
        if (h % (n + 1) != 0) {
            hCount++;
        }
        
        long wCount = w / (m + 1);
        if (w % (m + 1) != 0) {
            wCount++;
        }
        
        return hCount * wCount;
    }
}
