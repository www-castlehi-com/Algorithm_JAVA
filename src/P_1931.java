import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class P_1931 {

    static int[][] meetings;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        meetings = new int[n][2];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            meetings[i][0] = arr[0];
            meetings[i][1] = arr[1];
        }

        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        int time = meetings[0][1];
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (time <= meetings[i][0]) {
                cnt++;
                time = meetings[i][1];
            }
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
