import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] lis = new int[n];
        lis[0] = arr[0];
        int maxLis = 1;
        for (int i = 1; i < n; i++) {
            if (lis[maxLis - 1] >= arr[i]) {
                int index = Arrays.binarySearch(lis, 0, maxLis, arr[i]);
                if (index < 0) {
                    index = -index - 1;
                }
                lis[index] = arr[i];
            } else {
                lis[maxLis++] = arr[i];
            }
        }
        
        System.out.print(maxLis);
    }
}