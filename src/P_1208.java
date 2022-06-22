import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P_1208 {

    static int n, s;
    static long result = 0;
    static int[] nums;
    static Map<Integer, Long> subSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr[0]; s = arr[1];
        subSum = new HashMap<>();
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        leftSequence(0, 0);
        rightSequence(n / 2, 0);

        if (s == 0) result--;
        bw.write(Long.toString(result));
        bw.flush();
    }

    private static void rightSequence(int idx, int sum) {
        if (idx == n) {
            result += subSum.getOrDefault(s - sum, 0L);
        }
        else {
            rightSequence(idx + 1, sum + nums[idx]);
            rightSequence(idx + 1, sum);
        }
    }

    private static void leftSequence(int idx, int sum) {
        if (idx == n / 2) {
            subSum.put(sum, subSum.getOrDefault(sum, 0L) + 1);
        }
        else {
            leftSequence(idx + 1, sum + nums[idx]);
            leftSequence(idx + 1, sum);
        }
    }
}
