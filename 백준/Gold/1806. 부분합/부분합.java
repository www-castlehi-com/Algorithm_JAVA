import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      
        int start = 0;
        int end = 0;
        int minLen = n + 1;
        int sum = 0;
        while (true) {
            if (sum >= s) {
                minLen = Math.min(minLen, end - start);
                sum -= arr[start++];
            } else if (sum < s) {
                if (end == n) {
                    break;
                }
                sum += arr[end++];
            }
        }
        
        System.out.print(minLen == n + 1 ? 0 : minLen);
    }
}