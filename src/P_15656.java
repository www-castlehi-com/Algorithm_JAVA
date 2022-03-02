import java.io.*;
import java.util.Arrays;

public class P_15656 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] cond = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        int[] sequence = new int[cond[1]];
        find_sequence(sequence, nums, cond[0], 0);
        bw.flush();
    }

    public static void find_sequence(int[] sequence, int[] nums, int m, int pos) throws IOException {
        int i = 0;

        while (i < m) {
            sequence[pos % sequence.length] = nums[i];
            if (pos % sequence.length == sequence.length - 1) {
                for (int n : sequence) bw.write(n + " ");
                bw.write("\n");
            } else find_sequence(sequence, nums, m, pos + 1);
            i++;
        }
    }
}
