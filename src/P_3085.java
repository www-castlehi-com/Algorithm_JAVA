import java.io.*;

public class P_3085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[][] arr = new String[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().split("");
        }

        String tmp;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                tmp = arr[i][j];
                arr[i][j] = arr[i][j + 1];
                arr[i][j + 1] = tmp;
                max = (max > count_candy(n, arr)) ? max : count_candy(n, arr);
                tmp = arr[i][j];
                arr[i][j] = arr[i][j + 1];
                arr[i][j + 1] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                tmp = arr[j][i];
                arr[j][i] = arr[j + 1][i];
                arr[j + 1][i] = tmp;
                max = (max > count_candy(n, arr)) ? max : count_candy(n, arr);
                tmp = arr[j][i];
                arr[j][i] = arr[j + 1][i];
                arr[j + 1][i] = tmp;
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static int count_candy(int n, String[][] arr) {
        int count;
        int ans = 0;
        String ch;

        for (int i = 0; i < n; i++) {
            count = 1;
            ch = arr[i][0];
            for (int j = 1; j < n; j++) {
                if (arr[i][j].equals(ch))
                    count++;
                else
                    count = 1;
                ch = arr[i][j];
                ans = (ans > count) ? ans : count;
            }
        }

        for (int i = 0; i < n; i++) {
            count = 1;
            ch = arr[0][i];
            for (int j = 1; j < n; j++) {
                if (arr[j][i].equals(ch))
                    count++;
                else
                    count = 1;
                ch = arr[j][i];
                ans = (ans > count) ? ans : count;
            }
        }

        return ans;
    }
}
