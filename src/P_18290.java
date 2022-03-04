import java.io.*;
import java.util.Arrays;

public class P_18290 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] cond;
    static int[][] nums;
    static int[] elem;
    static int[][] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cond = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        nums = new int[cond[0]][cond[1]];
        elem = new int[Math.min(4, cond[0]*cond[1])];
        visited = new int[cond[0]][cond[1]];
        for (int i = 0 ; i < cond[0]; i++) nums[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        find_sequence(0);

        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void find_sequence(int pos) {
        if (pos == cond[2]) {
            int res = 0;
            for (int num : elem) {
                res += num;
            }
            max = (max > res) ? max : res;
        }
        else {
            for (int i = 0; i < cond[0]; i++) {
                for (int j = 0; j < cond[1]; j++) {
                    if (visited[i][j] > 0)
                        continue;
                    check_adjacency(i, j);
                    elem[pos] = nums[i][j];
                    find_sequence(pos + 1);
                    return_adjacency(i, j);
                }
            }
        }
    }

    public static void check_adjacency(int i, int j) {
        visited[i][j]++;
        if (i > 0) visited[i - 1][j]++;
        if (i < cond[0] - 1) visited[i + 1][j]++;
        if (j > 0) visited[i][j - 1]++;
        if (j < cond[1] - 1) visited[i][j + 1]++;
    }

    public static void return_adjacency(int i, int j) {
        visited[i][j]--;
        if (i > 0) visited[i - 1][j]--;
        if (i < cond[0] - 1) visited[i + 1][j]--;
        if (j > 0) visited[i][j - 1]--;
        if (j < cond[1] - 1) visited[i][j + 1]--;
    }
}
