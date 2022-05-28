import java.io.*;
import java.util.Arrays;

public class P_14888 {

    static int n;
    static int[] nums, ops;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        calculate(nums[0], 1, 0, 0, 0, 0);

        bw.write(max + "\n" + min);
        bw.flush();
    }

    public static void calculate(int result, int idx, int sum, int sub, int mul, int div) {
        if (idx == n) {
            if (max < result) max = result;
            if (min > result) min = result;
            return ;
        }

        if (sum < ops[0]) calculate(result + nums[idx], idx + 1, sum + 1, sub, mul, div);
        if (sub < ops[1]) calculate(result - nums[idx], idx + 1, sum, sub + 1, mul, div);
        if (mul < ops[2]) calculate(result * nums[idx], idx + 1, sum, sub, mul + 1, div);
        if (div < ops[3]) calculate(result / nums[idx], idx + 1, sum, sub, mul, div + 1);
    }
}
