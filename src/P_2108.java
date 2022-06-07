import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            sum += nums[i];
        }
        Arrays.sort(nums);

        int cnt = 1;
        int cntMax = 1;
        int prevNum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == prevNum) cnt++;
            else {
                prevNum = nums[i];
                if (cntMax < cnt) cntMax = cnt;
                cnt = 1;
            }
        }
        if (cntMax < cnt) cntMax = cnt;
        cnt = 1;
        prevNum = nums[0];
        ArrayList<Integer> modes = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (nums[i] == prevNum) cnt++;
            else {
                if (cntMax == cnt) modes.add(prevNum);
                prevNum = nums[i];
                cnt = 1;
            }
        }
        if (n == 1) modes.add(prevNum);
        else {
            if (cntMax == cnt) modes.add(prevNum);
        }

        int average = sum / n;
        if (sum % n * 10 / n >= 5) average++;
        else if (sum % n * 10 / n <= -5) average--;
        bw.write(average + "\n");
        bw.write(nums[n / 2] + "\n");
        if (modes.size() == 1) bw.write(modes.get(0) + "\n");
        else bw.write(modes.get(1) + "\n");
        bw.write(nums[n - 1] - nums[0] + "\n");
        bw.flush();
    }
}
