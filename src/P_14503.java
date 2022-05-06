import java.io.*;
import java.util.Arrays;

public class P_14503 {

    static int n, m;
    static int[] rv_info;
    static int[][] map;
    static int complete = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] map_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = map_info[0];
        m = map_info[1];
        rv_info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        map = new int[n][m];
        for (int i = 0; i < n; i++) map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ready();

        bw.write(Integer.toString(complete));
        bw.flush();
    }

    public static void ready() {
        int cnt = 0;

        map[rv_info[0]][rv_info[1]] = 2;
        complete++;

        while (true) {

            if (cnt == 4) {
                cnt = 0;
                int result = back_clean();
                if (result == 0) break;
                else if (result == 1) complete++;
            }

            left_navigation();
            if (left_clean() == 0) cnt++;
            else {
                cnt = 0;
                complete++;
            }
        }
    }

    public static void left_navigation() {
        switch (rv_info[2]) {
            case 0:
                rv_info[2] = 3;
                break;
            case 1:
                rv_info[2] = 0;
                break;
            case 2:
                rv_info[2] = 1;
                break;
            case 3:
                rv_info[2] = 2;
                break;
        }
    }

    public static int left_clean() {
        switch (rv_info[2]) {
            case 0:
                if (rv_info[0] - 1 >= 0 && map[rv_info[0] - 1][rv_info[1]] == 0) {
                    map[--rv_info[0]][rv_info[1]] = 2;
                    return 1;
                }
                else return 0;
            case 1:
                if (rv_info[1] + 1 < m && map[rv_info[0]][rv_info[1] + 1] == 0) {
                    map[rv_info[0]][++rv_info[1]] = 2;
                    return 1;
                }
                else return 0;
            case 2:
                if (rv_info[0] + 1 < n && map[rv_info[0] + 1][rv_info[1]] == 0) {
                    map[++rv_info[0]][rv_info[1]] = 2;
                    return 1;
                }
                else return 0;
            case 3:
                if (rv_info[1] - 1 >= 0 && map[rv_info[0]][rv_info[1] - 1] == 0) {
                    map[rv_info[0]][--rv_info[1]] = 2;
                    return 1;
                }
                else return 0;
        }
        return 0;
    }

    public static int back_clean() {
        int flag = 0;

        switch (rv_info[2]) {
            case 0:
                if (rv_info[0] + 1 < n && map[rv_info[0] + 1][rv_info[1]] != 1) {
                    rv_info[0]++;
                    if (map[rv_info[0]][rv_info[1]] == 2) flag = -1;
                    else {
                        map[rv_info[0]][rv_info[1]] = 2;
                        flag = 1;
                    }
                }
                break;
            case 1:
                if (rv_info[1] - 1 >= 0 && map[rv_info[0]][rv_info[1] - 1] != 1) {
                    rv_info[1]--;
                    if (map[rv_info[0]][rv_info[1]] == 2) flag = -1;
                    else {
                        map[rv_info[0]][rv_info[1]] = 2;
                        flag = 1;
                    }
                }
                break;
            case 2:
                if (rv_info[0] - 1 >= 0 && map[rv_info[0] - 1][rv_info[1]] != 1) {
                    rv_info[0]--;
                    if (map[rv_info[0]][rv_info[1]] == 2) flag = -1;
                    else {
                        map[rv_info[0]][rv_info[1]] = 2;
                        flag = 1;
                    }
                }
                break;
            case 3:
                if (rv_info[1] + 1 < m && map[rv_info[0]][rv_info[1] + 1] != 1) {
                    rv_info[1]++;
                    if (map[rv_info[0]][rv_info[1]] == 2) flag = -1;
                    else {
                        map[rv_info[0]][rv_info[1]] = 2;
                        flag = 1;
                    }
                }
                break;
        }
        return flag;
    }
}
